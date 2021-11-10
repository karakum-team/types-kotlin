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
        .replace("\n/**\n", "\n$DELIMITER\n/**\n")
        .replace("\nexport ", "\n$DELIMITER\nexport ")
        .replace("\n */\n$DELIMITER\n", "\n */\n")
        .splitToSequence("\n$DELIMITER\n")
        .map { content ->
            val name = content.substringAfter(" */\n")
                .substringAfter("export ")
                .substringBefore(" extends ")
                .substringBefore(": ")
                .substringBefore("(")
                .substringBefore("<")
                .substringBefore(" {")
                .substringBefore(" = ")
                .substringAfterLast(" ")

            ConversionResult(name, content)
        }
}
