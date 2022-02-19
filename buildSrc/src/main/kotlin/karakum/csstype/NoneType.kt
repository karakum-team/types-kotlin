package karakum.csstype

internal const val NONE_TYPE = "NoneType"

internal fun NoneType(): ConversionResult {
    return ConversionResult(
        name = NONE_TYPE,
        body = "sealed external interface $NONE_TYPE",
    )
}
