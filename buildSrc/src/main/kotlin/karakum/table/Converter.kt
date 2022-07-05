package karakum.table

import java.io.File

internal data class ConversionResult(
    val name: String,
    val body: String,
)

internal fun convertDefinitions(
    definitionFile: File,
): Sequence<ConversionResult> =
    definitionFile.readText()
        .splitToSequence("\ndeclare ")
        .drop(1)
        .map { it.removeSuffix(";") }
        .map { convertDefinition(it) }

private fun convertDefinition(
    source: String,
): ConversionResult {
    val name = source
        .removePrefix("const ")
        .removePrefix("function ")
        .removePrefix("type ")
        .substringBefore(" ")
        .substringBefore(":")
        .substringBefore("<")
        .substringBefore("(")

    return ConversionResult(name, source)
}
