package karakum.events

import kotlinx.serialization.json.Json
import java.io.File

private data class EventInstance(
    val target: String,
    val type: String,
)

object EventDataRegistry {
    lateinit var sourceFile: File

    private val json = Json {
        explicitNulls = false
    }

    private val dataList: List<EventData> by lazy {
        json.decodeFromString(sourceFile.readText())
    }

    private fun Target.targetWithAliases(
        eventType: String,
    ): Sequence<String> {
        return sequenceOf(target)
            .plus(
                when {
                    target == "HTMLSlotElement"
                    -> sequenceOf("ShadowRoot", "GlobalEventHandlers")

                    target == "Element"
                            || (target.startsWith("HTML") && target.endsWith("Element"))
                    -> sequenceOf("GlobalEventHandlers")

                    target == "Window"
                    -> sequenceOf("WindowEventHandlers")

                    target == "Worker"
                    -> sequenceOf("AbstractWorker")

                    // TEMP?
                    eventType == "securitypolicyviolation"
                    -> sequenceOf("GlobalEventHandlers")

                    else -> emptySequence()
                }
            )
            .plus(bubblingPath ?: emptyList())
            .distinct()
    }

    private val targetMap: Map<EventInstance, String> by lazy {
        dataList.asSequence()
            .flatMap { data ->
                val type = data.type
                data.targets.asSequence().flatMap {
                    val defaultEventTarget = if (it.bubbles) {
                        it.bubblingPath!!.first()
                    } else null

                    it.targetWithAliases(type).map { target ->
                        val eventTarget = defaultEventTarget ?: target

                        EventInstance(target, type) to eventTarget
                    }
                }
            }
            .toMap()
    }

    fun getTarget(
        thisType: String,
        eventType: String,
    ): String {
        val className = thisType.substringBefore("<")
        return when (val targetType = targetMap.getValue(EventInstance(className, eventType))) {
            "IDBRequest" -> targetType + "<T>"
            else -> targetType
        }
    }
}
