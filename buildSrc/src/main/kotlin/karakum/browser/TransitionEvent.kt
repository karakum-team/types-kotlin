package karakum.browser

internal const val TRANSITION_EVENT = "TransitionEvent"

private val BODY = """
external class $TRANSITION_EVENT(
    // TODO: parameters
) : Event {
    companion object
}
""".trimIndent()

internal fun TransitionEvent(): ConversionResult =
    ConversionResult(
        name = TRANSITION_EVENT,
        body = BODY,
    )
