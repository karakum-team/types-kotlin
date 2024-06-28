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
        if (!bubbles)
            return sequenceOf(target)

        val alias = when (target) {
            "HTMLElement" -> "GlobalEventHandlers"
            "Window" -> "WindowEventHandlers"
            else -> return sequenceOf(target)
        }

        return sequenceOf(target, alias)
    }

    private val targetMap: Map<EventInstance, String> by lazy {
        dataList.asSequence()
            .flatMap { data ->
                val type = data.type
                data.targets.asSequence().flatMap {
                    val eventTarget = if (it.bubbles) {
                        it.bubblingPath?.first() ?: "EventTarget"
                    } else it.target

                    it.targetWithAliases()
                        .map { target -> EventInstance(target, type) to eventTarget }
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
            ?: return null

        return when (targetType) {
            "IDBRequest" -> targetType + "<T>"
            else -> targetType
        }
    }
}
