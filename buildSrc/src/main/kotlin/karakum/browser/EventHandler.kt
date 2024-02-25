package karakum.browser

internal const val EVENT_HANDLER = "EventHandler"

private val EVENT_HANDLER_BODY: String = """
typealias EventHandler<E /* : Event */> = (
    event: E,
) -> Unit
""".trimIndent()

internal fun EventHandler(): ConversionResult =
    ConversionResult(
        name = EVENT_HANDLER,
        body = EVENT_HANDLER_BODY,
        pkg = "web.events",
    )
