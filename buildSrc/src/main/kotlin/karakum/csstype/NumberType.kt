package karakum.csstype

internal const val NUMBER = "Number"
internal const val NUMBER_TYPE = "NumberType"

internal fun NumberType(
    parentProvider: ParentProvider,
): ConversionResult {
    val parentTypes = parentProvider
        .parentTypes.sorted()
        .joinToString(",\n")

    val body = """
        sealed external interface $NUMBER_TYPE:
        $parentTypes
        
        inline fun number(value: Double): $NUMBER_TYPE =
            value.unsafeCast<$NUMBER_TYPE>()
    """.trimIndent()

    return ConversionResult(NUMBER, body)
}
