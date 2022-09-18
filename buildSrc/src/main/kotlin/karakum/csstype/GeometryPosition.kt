package karakum.csstype

import karakum.common.unionBody

internal const val GEOMETRY_POSITION = "GeometryPosition"
private val GEOMETRY_POSITION_BODY = unionBody(
    GEOMETRY_POSITION,
    listOf(
        "bottom",
        "center",
        "left",
        "right",
        "top",
        )
)

internal fun GeometryPosition(
    parentProvider: ParentProvider,
): ConversionResult =
    ConversionResult(
        name = GEOMETRY_POSITION,
        body = GEOMETRY_POSITION_BODY,
    )
