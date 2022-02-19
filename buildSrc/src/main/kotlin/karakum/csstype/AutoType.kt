package karakum.csstype

internal const val AUTO_TYPE = "AutoType"

internal fun AutoType(
    parentProvider: ParentProvider,
): ConversionResult {
    val parentTypes = parentProvider.parentTypes
        .sorted()
        .joinToString(",\n")

    val body = """
        sealed external interface $AUTO_TYPE:
        $parentTypes
    """.trimIndent()

    return ConversionResult(AUTO_TYPE, body)
}
