package karakum.csstype

internal const val GRADIENT = "Gradient"

// source - https://developer.mozilla.org/en-US/docs/Web/CSS/gradient
private val PARENT_TYPES = setOf(
    IMAGE,
)

internal fun Gradient(): ConversionResult =
    ConversionResult(
        name = GRADIENT,
        body = "sealed external interface $GRADIENT:\n" +
                PARENT_TYPES.joinToString(",\n")
    )
