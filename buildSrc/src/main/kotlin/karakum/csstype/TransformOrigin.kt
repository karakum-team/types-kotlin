package karakum.csstype

internal const val TRANSFORM_ORIGIN = "TransformOrigin"

internal fun String.addTransformOriginFactories(): String =
    replace(": $TRANSFORM_ORIGIN", ": OffsetKeyword")
        .replace("companion object {", "\ninterface OffsetKeyword : $TRANSFORM_ORIGIN\n\ncompanion object {")
