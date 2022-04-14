package karakum.browser

private const val ANIMATION_EVENT = "AnimationEvent"

internal fun AnimationEvent(): ConversionResult =
    event(
        ANIMATION_EVENT,
        EventProperty("animationName", "String"),
        EventProperty("elapsedTime", "HighResTimeStamp"),
        EventProperty("pseudoElement", "String"),
    )
