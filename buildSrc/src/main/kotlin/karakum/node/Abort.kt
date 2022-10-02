package karakum.node

internal const val ABORT_CONTROLLER = "AbortController"

// language=kotlin
private val ABORT_CONTROLLER_BODY = """
external class AbortController : web.AbortController {
    override val signal: AbortSignal
    override fun abort()
}
""".trimIndent()

internal const val ABORT_SIGNAL = "AbortSignal"

// language=kotlin
private val ABORT_SIGNAL_BODY = """
external class AbortSignal : web.AbortSignal {
    override val aborted: Boolean
    override var onabort: Function<Unit>?
}
""".trimIndent()

internal const val ABORTABLE = "Abortable"

// language=kotlin
private val ABORTABLE_BODY = """
external interface Abortable {
    /**
     * When provided the corresponding `AbortController` can be used to cancel an asynchronous action.
     */
    var signal: AbortSignal?
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

internal fun Abortable(): ConversionResult =
    ConversionResult(
        ABORTABLE,
        ABORTABLE_BODY,
    )
