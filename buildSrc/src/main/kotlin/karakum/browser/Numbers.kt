package karakum.browser

private val FLAG_NAMES = setOf(
    "networkState",
    "readyState",
    "resultType",
    "unitType",
    "type",

    "align",
    "meetOrSlice",
)

private val LONG_NAMES = setOf(
    "quota",
    "usage",

    "newVersion",
    "oldVersion",
)

private val INT_NAMES = setOf(
    "cellIndex",
    "colSpan",
    "cols",
    "hardwareConcurrency",
    "index",
    "length",
    "snapshotLength",
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

    // window
    "innerWidth",
    "innerHeight",
    "screenLeft",
    "screenTop",
    "screenX",
    "screenY",
    "outerWidth",
    "outerHeight",

    "endOffset",
    "startOffset",

    "charIndex",
    "charLength",

    "code",

    // RTC
    "errorCode",
    "port",

    // error
    "colno",
    "lineno",
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
    "numberValue",
    "width",
    "x",
    "y",
    "z",

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

    // speech
    "pitch",
    "rate",
    "confidence",

    // window
    "scrollX",
    "scrollY",
    "pageXOffset",
    "pageYOffset",

    // event
    "elapsedTime",
    "interval",

    "alpha",
    "beta",
    "gamma",
)

private val WINDOW_EXCLUDED = setOf(
    // properties
    "customElements",
    "document",
    "devicePixelRatio",
    "history",
    "locationbar",
    "menubar",
    "navigator",
    "personalbar",
    "screen",
    "scrollbars",
    "self",
    "speechSynthesis",
    "statusbar",
    "toolbar",
    "visualViewport",
    "window",

    // methods
    "alert",
    "cancelIdleCallback",
    "confirm",
    "getComputedStyle",
    "getSelection",
    "matchMedia",
    "prompt",
    "requestIdleCallback",
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
            parentType == "GeolocationPositionError" -> "Short"
            parentType == "GeolocationCoordinates" -> "Double"
            parentType == "PositionOptions" -> "JsLong"

            parentType == "Screen" -> "Int"

            parentType == "SVGAnimatedInteger" -> "Int"
            parentType == "SVGAnimatedNumber" -> "Double"

            propertyName.endsWith("Frames") -> "Int"

            propertyName in LONG_NAMES -> "JsLong"
            propertyName in INT_NAMES -> "Int"
            propertyName in DOUBLE_NAMES -> "Double"
            else -> {
                println("$parentType.$propertyName")

                // TODO("No numberability configuration for property '$propertyName'")
                "Number"
            }
        }
    }

    fun accepted(
        name: String,
    ): Boolean {
        if (parentType == "Window")
            return name !in WINDOW_EXCLUDED

        return true
    }
}
