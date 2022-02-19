package karakum.csstype

import karakum.common.unionBody

private const val AUTO_REPEAT = "AutoRepeat"
private const val GRID_TEMPLATE_TRACKS = "GridTemplateTracks"

internal fun AutoRepeat(): ConversionResult =
    ConversionResult(
        AUTO_REPEAT,
        unionBody(AUTO_REPEAT, listOf("auto-fill", "auto-fit")),
    )

internal fun gridFunctions(): ConversionResult {
    val declarations = sequenceOf(
        "times" to "Int",
        "repeat" to AUTO_REPEAT,
    ).map { firstParameter ->
        function(
            name = "repeat",
            returnType = GRID_TEMPLATE_TRACKS,
            parameters = arrayOf(
                firstParameter,
                "value" to GRID_TEMPLATE_TRACKS,
            )
        )
    }

    return ConversionResult(
        name = "Grid.functions",
        body = declarations.joinToString("\n\n"),
    )
}
