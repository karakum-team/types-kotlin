package karakum.csstype

internal const val INTEGER = "Integer"
internal const val INTEGER_TYPE = "IntegerType"

internal fun IntegerType(
    parentProvider: ParentProvider,
): ConversionResult {
    val parentTypes = parentProvider
        .parentTypes.sorted()
        .joinToString(",\n")

    val body = """
        sealed external interface $INTEGER_TYPE:
        $parentTypes
        
        inline fun integer(value: Double): $INTEGER_TYPE =
            value.unsafeCast<$INTEGER_TYPE>()
    """.trimIndent()

    return ConversionResult(INTEGER, body)
}
