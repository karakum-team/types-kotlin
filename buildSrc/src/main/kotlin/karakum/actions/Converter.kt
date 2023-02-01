package karakum.actions

internal fun convert(
    content: String,
    // TEMP
    fileName: String,
): Sequence<ConversionResult> {
    return sequenceOf(
        ConversionResult(
            name = fileName,
            body = content,
        )
    )
}
