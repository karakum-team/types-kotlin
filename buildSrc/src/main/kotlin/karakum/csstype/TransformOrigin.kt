package karakum.csstype

internal const val TRANSFORM_ORIGIN = "TransformOrigin"
internal const val OFFSET_KEYWORD = "$TRANSFORM_ORIGIN.OffsetKeyword"

private val X_OFFSET = "xOffset" to LENGTH
private val Y_OFFSET = "yOffset" to LENGTH
private val Z_OFFSET = "zOffset" to LENGTH

private val X_OFFSET_KEYWORD = "xOffsetKeyword" to OFFSET_KEYWORD
private val Y_OFFSET_KEYWORD = "yOffsetKeyword" to OFFSET_KEYWORD

internal fun String.addTransformOriginFactories(): String {
    val newBody = replace(": $TRANSFORM_ORIGIN", ": OffsetKeyword")
        .replace("companion object {", "\ninterface OffsetKeyword : $TRANSFORM_ORIGIN\n\ncompanion object {")

    val factories = sequenceOf(
        arrayOf(X_OFFSET, Y_OFFSET),
        arrayOf(X_OFFSET_KEYWORD, Y_OFFSET),
        arrayOf(X_OFFSET_KEYWORD, Y_OFFSET_KEYWORD),
        arrayOf(X_OFFSET, Y_OFFSET, Z_OFFSET),
        arrayOf(X_OFFSET_KEYWORD, Y_OFFSET, Z_OFFSET),
        arrayOf(X_OFFSET_KEYWORD, Y_OFFSET_KEYWORD, Z_OFFSET),
    ).joinToString("\n\n") { parameters ->
        factory(TRANSFORM_ORIGIN, parameters)
    }

    return "$newBody\n\n$factories"
}
