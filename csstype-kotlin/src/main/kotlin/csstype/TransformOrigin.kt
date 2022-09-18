// Automatically generated - do not modify!

@file:Suppress(
    "NAME_CONTAINS_ILLEGAL_CHARS",
    "NESTED_CLASS_IN_EXTERNAL_INTERFACE",
    "NOTHING_TO_INLINE",
)

package csstype

// language=JavaScript
@JsName("""(/*union*/{bottom: 'bottom', center: 'center', left: 'left', right: 'right', top: 'top'}/*union*/)""")
sealed external interface TransformOrigin {
    companion object {
        val bottom: TransformOrigin
        val center: TransformOrigin
        val left: TransformOrigin
        val right: TransformOrigin
        val top: TransformOrigin
    }
}

inline fun TransformOrigin(
    xOffset: Length,
    yOffset: Length,
): TransformOrigin =
    "$xOffset $yOffset".unsafeCast<TransformOrigin>()

inline fun TransformOrigin(
    xOffsetKeyword: GeometryPosition,
    yOffset: Length,
): TransformOrigin =
    "$xOffsetKeyword $yOffset".unsafeCast<TransformOrigin>()

inline fun TransformOrigin(
    xOffsetKeyword: GeometryPosition,
    yOffsetKeyword: GeometryPosition,
): TransformOrigin =
    "$xOffsetKeyword $yOffsetKeyword".unsafeCast<TransformOrigin>()

inline fun TransformOrigin(
    xOffset: Length,
    yOffset: Length,
    zOffset: Length,
): TransformOrigin =
    "$xOffset $yOffset $zOffset".unsafeCast<TransformOrigin>()

inline fun TransformOrigin(
    xOffsetKeyword: GeometryPosition,
    yOffset: Length,
    zOffset: Length,
): TransformOrigin =
    "$xOffsetKeyword $yOffset $zOffset".unsafeCast<TransformOrigin>()

inline fun TransformOrigin(
    xOffsetKeyword: GeometryPosition,
    yOffsetKeyword: GeometryPosition,
    zOffset: Length,
): TransformOrigin =
    "$xOffsetKeyword $yOffsetKeyword $zOffset".unsafeCast<TransformOrigin>()
