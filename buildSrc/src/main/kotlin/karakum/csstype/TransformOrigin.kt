package karakum.csstype

internal const val TRANSFORM_ORIGIN = "TransformOrigin"

private val X_OFFSET = "xOffset" to LENGTH
private val Y_OFFSET = "yOffset" to LENGTH
private val Z_OFFSET = "zOffset" to LENGTH

private val X_OFFSET_KEYWORD = "xOffsetKeyword" to GEOMETRY_POSITION
private val Y_OFFSET_KEYWORD = "yOffsetKeyword" to GEOMETRY_POSITION

internal fun transformOriginFactories(): String =
    sequenceOf(
        arrayOf(X_OFFSET, Y_OFFSET),
        arrayOf(X_OFFSET_KEYWORD, Y_OFFSET),
        arrayOf(X_OFFSET_KEYWORD, Y_OFFSET_KEYWORD),
        arrayOf(X_OFFSET, Y_OFFSET, Z_OFFSET),
        arrayOf(X_OFFSET_KEYWORD, Y_OFFSET, Z_OFFSET),
        arrayOf(X_OFFSET_KEYWORD, Y_OFFSET_KEYWORD, Z_OFFSET),
    ).joinToString("\n\n") { parameters ->
        factory(TRANSFORM_ORIGIN, parameters)
    }
