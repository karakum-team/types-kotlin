package com.github.turansky.router

private const val DELIMITER = "//--delimiter--//"

internal data class ConversionResult(
    val name: String,
    val body: String,
)

internal fun convertDefinitions(
    source: String,
): Sequence<ConversionResult> {
    return source
        .replace("/**\n", "$DELIMITER\n/**\n")
        .replace("\nexport ", "\n$DELIMITER\nexport ")
        .replace("\n */\n$DELIMITER\n", "\n */\n")
        .splitToSequence("\n$DELIMITER\n")
        .mapIndexed { index, content ->
            ConversionResult((index + 1).toString(), content)
        }
}
