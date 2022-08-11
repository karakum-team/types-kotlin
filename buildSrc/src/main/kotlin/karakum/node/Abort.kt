package karakum.node

internal const val ABORT_CONTROLLER = "AbortController"

// language=kotlin
private val ABORT_CONTROLLER_BODY = """
external class AbortController : kotlinx.js.AbortController {
    override val signal: AbortSignal
    override fun abort()
}
""".trimIndent()

internal const val ABORT_SIGNAL = "AbortSignal"

// language=kotlin
private val ABORT_SIGNAL_BODY = """
external class AbortSignal : kotlinx.js.AbortSignal {
    override val aborted: Boolean
    override var onabort: Function<Unit>?
}
""".trimIndent()

internal fun abortClasses(): Sequence<ConversionResult> =
    sequenceOf(
        ConversionResult(
            ABORT_CONTROLLER,
            ABORT_CONTROLLER_BODY,
        ),
        ConversionResult(
            ABORT_SIGNAL,
            ABORT_SIGNAL_BODY,
        ),
    )
