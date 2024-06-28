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

    private val targetMap: Map<EventInstance, String> by lazy {
        dataList.asSequence()
            .flatMap { data ->
                val type = data.type
                data.targets.asSequence().map {
                    val eventTarget = if (it.bubbles) {
                        it.bubblingPath?.first() ?: "EventTarget"
                    } else it.target

                    EventInstance(it.target, type) to eventTarget
                }
            }
            .toMap()
    }

    fun getTarget(
        className: String,
        eventType: String,
    ): String? {
        return targetMap[EventInstance(className, eventType)]
    }
}
