package karakum.csstype

internal const val INTEGER = "Integer"
internal const val INTEGER_TYPE = "IntegerType"

private val PARENT_TYPES = listOf(
    "GridLine",
)

internal fun IntegerType(
    parentProvider: ParentProvider,
): ConversionResult {
    val parentTypes = parentProvider.parentTypes
        .plus(PARENT_TYPES)
        .sorted()
        .joinToString(",\n")

    val body = """
        sealed external interface $INTEGER_TYPE:
        $parentTypes
        
        ${factory("integer", INTEGER_TYPE, arrayOf("value" to "Int"))}
    """.trimIndent()

    return ConversionResult(INTEGER, body)
}
