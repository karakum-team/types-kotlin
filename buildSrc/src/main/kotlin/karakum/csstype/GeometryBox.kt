package karakum.csstype

import karakum.common.unionBody

internal const val GEOMETRY_BOX = "GeometryBox"
private val GEOMETRY_BOX_BODY = unionBody(
    GEOMETRY_BOX,
    listOf(
        "border-box",
        "padding-box",
        "content-box",
        "margin-box",
    )
).replaceFirst(GEOMETRY_BOX, "$GEOMETRY_BOX : $BASIC_SHAPE")

internal fun GeometryBox(): ConversionResult =
    ConversionResult(
        name = GEOMETRY_BOX,
        body = GEOMETRY_BOX_BODY,
    )
