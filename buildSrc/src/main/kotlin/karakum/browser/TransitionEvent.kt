package karakum.browser

private const val TRANSITION_EVENT = "TransitionEvent"

internal fun TransitionEvent(): ConversionResult =
    event(
        TRANSITION_EVENT,
        EventProperty("elapsedTime", "HighResTimeStamp"),
        EventProperty("propertyName", "String"),
        EventProperty("pseudoElement", "String"),
    )
