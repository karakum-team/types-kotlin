package karakum.browser

internal const val ANIMATION_EVENT = "AnimationEvent"

private val BODY = """
external class $ANIMATION_EVENT(
    // TODO: parameters
) : Event {
    val animationName: String
    val elapsedTime: Number
    val pseudoElement: String
    
    companion object
}
""".trimIndent()

internal fun AnimationEvent(): ConversionResult =
    ConversionResult(
        name = ANIMATION_EVENT,
        body = BODY,
    )
