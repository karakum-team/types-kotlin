package karakum.csstype

import karakum.common.unionBody

private const val AUTO_REPEAT = "AutoRepeat"
private const val GRID_TEMPLATE_STRIPES = "GridTemplateStripes"

internal fun AutoRepeat(): ConversionResult =
    ConversionResult(
        AUTO_REPEAT,
        unionBody(AUTO_REPEAT, listOf("auto-fill", "auto-fit")),
    )

internal fun GridFunction(): ConversionResult {
    val declarations = sequenceOf(
        "times" to "Int",
        "repeat" to AUTO_REPEAT,
    ).map { firstParameter ->
        factory(
            name = "repeat",
            returnType = GRID_TEMPLATE_STRIPES,
            parameters = arrayOf(
                firstParameter,
                "value" to GRID_TEMPLATE_STRIPES,
            )
        )
    }

    return ConversionResult(
        name = "Grid.functions",
        body = declarations.joinToString("\n\n"),
    )
}
