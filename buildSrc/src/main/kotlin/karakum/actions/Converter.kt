package karakum.actions

internal fun convert(
    content: String,
    // TEMP
    fileName: String,
): Sequence<ConversionResult> {
    return sequenceOf(
        ConversionResult(
            name = fileName,
            body = cleanup(content),
        )
    )
}

private fun cleanup(
    content: String,
): String =
    content.splitToSequence("\n")
        .filter { line ->
            !(line.startsWith("import ") && " from '." in line)
        }
        .joinToString("\n")
