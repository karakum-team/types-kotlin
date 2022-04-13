package karakum.browser

private const val ANIMATION_EVENT = "AnimationEvent"
private const val ANIMATION_EVENT_INIT = "AnimationEventInit"

private val BODY = """
import kotlinx.js.HighResTimeStamp
import org.w3c.dom.EventInit
    
external interface $ANIMATION_EVENT_INIT : EventInit {
    var animationName: String?
    var elapsedTime: HighResTimeStamp?
    var pseudoElement: String?
}    
    
external class $ANIMATION_EVENT(
    type: String,
    init: $ANIMATION_EVENT_INIT = definedExternally,
) : Event {
    val animationName: String
    val elapsedTime: HighResTimeStamp
    val pseudoElement: String
    
    companion object
}
""".trimIndent()

internal fun AnimationEvent(): ConversionResult =
    ConversionResult(
        name = ANIMATION_EVENT,
        body = BODY,
    )
