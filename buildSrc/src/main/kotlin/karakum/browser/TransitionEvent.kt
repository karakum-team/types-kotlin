package karakum.browser

private const val TRANSITION_EVENT = "TransitionEvent"
private const val TRANSITION_EVENT_INIT = "TransitionEventInit"

private val BODY = """
import kotlinx.js.HighResTimeStamp
import org.w3c.dom.EventInit

external interface $TRANSITION_EVENT_INIT : EventInit {
    var elapsedTime: HighResTimeStamp?
    var propertyName: String?
    var pseudoElement: String?
}
    
external class $TRANSITION_EVENT(
    type: String,
    init: $TRANSITION_EVENT_INIT = definedExternally,
) : Event {
    val elapsedTime: HighResTimeStamp
    val propertyName: String
    val pseudoElement: String

    companion object
}
""".trimIndent()

internal fun TransitionEvent(): ConversionResult =
    ConversionResult(
        name = TRANSITION_EVENT,
        body = BODY,
    )
