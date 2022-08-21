package karakum.node

internal const val PIPE_OPTIONS = "PipeOptions"

// language=kotlin
private val PIPE_OPTIONS_BODY = """
sealed external interface $PIPE_OPTIONS {
    var end: Boolean?
}
""".trimIndent()

internal fun PipeOptions(): ConversionResult =
    ConversionResult(
        PIPE_OPTIONS,
        PIPE_OPTIONS_BODY,
    )
