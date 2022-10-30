package karakum.browser

private val FLAG_NAMES = setOf(
    "networkState",
    "readyState",
    "unitType",
    "type",

    "align",
    "meetOrSlice",
)

private val INT_NAMES = setOf(
    "cellIndex",
    "colSpan",
    "cols",
    "hardwareConcurrency",
    "index",
    "length",
    "maxLength",
    "minLength",
    "naturalHeight",
    "naturalWidth",
    "rowIndex",
    "rowSpan",
    "rows",
    "sectionRowIndex",
    "selectedIndex",
    "selectionEnd",
    "selectionStart",
    "size",
    "span",
    "start",
    "textLength",

    "videoHeight",
    "videoWidth",

    "numberOfItems",

    "maxTouchPoints",
)

private val DOUBLE_NAMES = setOf(
    // DOMMatrix2DInit
    "a",
    "b",
    "c",
    "d",
    "e",
    "f",
    "m11",
    "m12",
    "m21",
    "m22",
    "m41",
    "m42",

    "height",
    "high",
    "low",
    "max",
    "min",
    "optimum",
    "position",
    "quality",
    "value",
    "valueAsNumber",
    "width",
    "x",
    "y",

    // Media elements
    "mediaTime",
    "processingDuration",
    "rtpTimestamp",
    "currentTime",
    "defaultPlaybackRate",
    "duration",
    "playbackRate",
    "volume",

    "angle",
    "currentScale",
    "valueInSpecifiedUnits",

    // animation
    "currentIteration",
    "delay",
    "duration",
    "endDelay",
    "iterationStart",
    "iterations",
    "progress",
)

internal class TypeProvider(
    private val parentType: String,
) {
    fun numberType(
        propertyName: String,
    ): String {
        // flags
        if (propertyName == propertyName.toUpperCase()
            || parentType == "SVGAnimatedEnumeration"
            || propertyName in FLAG_NAMES
        ) return "Short"

        if (parentType == "HTMLCanvasElement") {
            when (propertyName) {
                "width",
                "height",
                -> return "Int"
            }
        }

        return when {
            propertyName == "quota" || propertyName == "usage"
            -> "Double /* Int64 */"

            parentType == "SVGAnimatedInteger" -> "Int"
            parentType == "SVGAnimatedNumber" -> "Double"

            propertyName.endsWith("Frames") -> "Int"

            propertyName in INT_NAMES -> "Int"
            propertyName in DOUBLE_NAMES -> "Double"
            else -> {
                println("$parentType.$propertyName")

                // TODO("No numberability configuration for property '$propertyName'")
                "Number"
            }
        }
    }
}
