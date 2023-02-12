package karakum.node

import karakum.common.ConversionResult

internal const val ABORT_CONTROLLER = "AbortController"
internal const val ABORT_SIGNAL = "AbortSignal"
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

internal fun Abortable(): ConversionResult =
    ConversionResult(
        ABORTABLE,
        ABORTABLE_BODY,
    )
