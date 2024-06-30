package karakum.events

import kotlinx.serialization.json.Json
import org.gradle.kotlin.dsl.provideDelegate
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
            .plus(bubblingPath)
            .distinct()
    }

    private val EXCLUDED_EVENTS = setOf(
        // legacy
        "MutationEvent",
        "TimeEvent",
        "TextEvent", // ?

        // TODO - use `Event` as alias instead
        // new
        "XRSessionEvent",
        "BackgroundFetchEvent",
        "BackgroundFetchUpdateUIEvent",
        "BeforeInstallPromptEvent",
        "NavigationEvent",
        "PageRevealEvent",
        "PageSwapEvent",
        "PortalActivateEvent",

        "CanMakePaymentEvent",
        "ContentIndexEvent",
        "ExtendableCookieChangeEvent",
        "InstallEvent",
        "PaymentRequestEvent",
        "PeriodicSyncEvent",
        "PushSubscriptionChangeEvent",
        "SyncEvent",
        "KeyFrameRequestEvent",
        "CaptureActionEvent",
        "DeviceChangeEvent",
    )

    private val dataMap: Map<String, List<EventData>> by lazy {
        dataList.asSequence()
            .filter { it.`interface` !in EXCLUDED_EVENTS }
            .flatMap { data ->
                data.targets.asSequence()
                    .flatMap { sequenceOf(it.target) + it.bubblingPath }
                    .filter { it != "Node" }
                    .filter { it != "Screen" } // not EventTarget
                    .map { it to data }
            }
            .distinctBy { (target, data) -> target to data.type }
            .groupBy({ it.first }, { it.second })
    }

    private val targetMap: Map<EventInstance, String> by lazy {
        dataList.asSequence()
            .flatMap { data ->
                val type = data.type
                data.targets.asSequence().flatMap {
                    val defaultEventTarget = if (it.bubbles) {
                        it.bubblingPath.first()
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

    fun getDataList(className: String): List<EventData>? =
        dataMap[className]
}
