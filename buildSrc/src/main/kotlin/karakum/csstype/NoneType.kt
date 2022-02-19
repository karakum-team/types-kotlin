package karakum.csstype

internal const val NONE_TYPE = "NoneType"

internal fun NoneType(
    parentProvider: ParentProvider,
): ConversionResult {
    val parentTypes = parentProvider.parentTypes
        .sorted()
        .joinToString(",\n")

    val body = """
        sealed external interface $NONE_TYPE:
        $parentTypes
    """.trimIndent()

    return ConversionResult(NONE_TYPE, body)
}
