package com.github.turansky.csstype

internal fun tryToAlias(
    name: String,
    body: String,
): ConversionResult? =
    when (body) {
        "Globals | DataType.LineWidth | DataType.LineStyle | DataType.Color | (string & {})",
        -> if (name != "Border") {
            ConversionResult(name, "typealias $name = Border")
        } else null

        "Globals | DataType.Color",
        "Globals | DataType.Color | (string & {})",
        -> if (name != "ColorProperty") {
            ConversionResult(name, "typealias $name = ColorProperty")
        } else null

        "Globals | TLength",
        "Globals | TLength | (string & {})",
        -> ConversionResult(name, "typealias $name = $LENGTH_PROPERTY")

        """Globals | TLength | "auto"""",
        """Globals | TLength | "auto" | (string & {})""",
        -> ConversionResult(name, "typealias $name = $AUTO_LENGTH_PROPERTY")

        "Globals | TTime | (string & {})",
        -> ConversionResult(name, "typealias $name = $TIME_PROPERTY")

        "Globals | DataType.GridLine",
        "Globals | DataType.GridLine | (string & {})",
        -> ConversionResult(name, "typealias $name = $GRID_LINE_PROPERTY")

        "Globals | DataType.LineStyle",
        "Globals | DataType.LineStyle | (string & {})",
        -> ConversionResult(name, "typealias $name = $LINE_STYLE_PROPERTY")

        "Globals | DataType.LineWidth",
        "Globals | DataType.LineWidth | (string & {})",
        -> ConversionResult(name, "typealias $name = $LINE_WIDTH_PROPERTY")

        else -> null
    }
