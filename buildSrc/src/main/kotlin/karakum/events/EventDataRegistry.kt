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

    private fun Target.targetWithAliases(): Sequence<String> {
        val alias = when {
            target == "Element"
                    || (target.startsWith("HTML") && target.endsWith("Element"))
            -> "GlobalEventHandlers"

            target == "Window"
            -> "WindowEventHandlers"

            else -> return sequenceOf(target)
        }

        return sequenceOf(target, alias)
    }

    private val targetMap: Map<EventInstance, String> by lazy {
        dataList.asSequence()
            .flatMap { data ->
                val type = data.type
                data.targets.asSequence().flatMap {
                    val defaultEventTarget = if (it.bubbles) {
                        it.bubblingPath?.first() ?: "EventTarget"
                    } else null

                    it.targetWithAliases().map { target ->
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
    ): String? {
        val className = thisType.substringBefore("<")
        val targetType = targetMap[EventInstance(className, eventType)]
            ?: run {
                println("C: $className, T: $eventType")
                return null
            }

        return when (targetType) {
            "IDBRequest" -> targetType + "<T>"
            else -> targetType
        }
    }
}
