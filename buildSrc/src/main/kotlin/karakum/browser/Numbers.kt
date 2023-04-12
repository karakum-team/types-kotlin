package karakum.browser

private val FLAG_NAMES = setOf(
    "networkState",
    "readyState",

    "nodeType",
    "resultType",
    "unitType",
    "type",

    "align",
    "meetOrSlice",

    // mouse event
    "button",
    "buttons",

    "location",
    "deltaMode",
)

private val LONG_NAMES = setOf(
    "quota",
    "usage",

    "version",
    "newVersion",
    "oldVersion",

    // ProgressEvent
    "loaded",
    "total",

    "whatToShow",

    // Codecs
    "decodeQueueSize",
    "encodeQueueSize",
    "timestamp",
    "stride",
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
    "childElementCount",

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

    "clientX",
    "clientY",

    "tiltX",
    "tiltY",

    "detail",
    "twist",
    "pointerId",

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

    // Element
    "tabIndex",

    // HTMLElement
    "offsetWidth",
    "offsetHeight",
    "offsetTop",
    "offsetLeft",

    // Element
    "clientHeight",
    "clientLeft",
    "clientTop",
    "clientWidth",

    "scrollHeight",
    "scrollWidth",

    "columnNumber",
    "lineNumber",
    "statusCode",

    // Selection
    "anchorOffset",
    "focusOffset",
    "rangeCount",

    // ImageBitmapOptions
    "resizeWidth",
    "resizeHeight",

    // XMLHttpRequest
    "status",
    "timeout",

    // MediaTrackSettings
    "sampleRate",
    "sampleSize",

    // AudioConfiguration
    "bitrate",
    "samplerate",

    // MediaRecorderOptions
    "audioBitsPerSecond",
    "bitsPerSecond",
    "videoBitsPerSecond",

    // Audio
    "fftSize",
    "offset",

    // Crypto
    "tagLength",
    "modulusLength",
    "saltLength",

    // PerformanceResourceTiming
    "decodedBodySize",
    "encodedBodySize",
    "transferSize",

    // WebSocket
    "bufferedAmount",

    // Video
    "codedHeight",
    "codedWidth",
    "displayAspectHeight",
    "displayAspectWidth",
    "displayHeight",
    "displayWidth",

    "byteLength"
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

    "pageX",
    "pageY",
    "offsetX",
    "offsetY",
    "movementX",
    "movementY",

    "deltaX",
    "deltaY",
    "deltaZ",

    "pressure",
    "tangentialPressure",

    // Element
    "scrollLeft",
    "scrollTop",

    // Scroll
    "left",
    "top",

    // TextTrackCue
    "endTime",
    "startTime",

    // MediaTrackSettings
    "aspectRatio",
    "frameRate",

    // MediaSessionActionDetails
    "seekOffset",
    "seekTime",

    // observers
    "intersectionRatio",
    "blockSize",
    "inlineSize",

    // VideoConfiguration
    "framerate",

    // SourceBuffer
    "appendWindowEnd",
    "appendWindowStart",
    "timestampOffset",

    // Audio
    "attack",
    "baseLatency",
    "coneInnerAngle",
    "coneOuterAngle",
    "coneOuterGain",
    "contextTime",
    "defaultValue",
    "delayTime",
    "detune",
    "frequency",
    "gain",
    "knee",
    "loopEnd",
    "loopStart",
    "maxDecibels",
    "maxDelayTime",
    "maxDistance",
    "maxValue",
    "minDecibels",
    "minValue",
    "outputLatency",
    "pan",
    "ratio",
    "reduction",
    "refDistance",
    "release",
    "rolloffFactor",
    "smoothingTimeConstant",
    "threshold",

    // VisualViewport
    "pageLeft",
    "pageTop",
    "scale",
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
    "matchMedia",
    "prompt",
    "requestIdleCallback",
)

internal class TypeProvider(
    private val parentType: String,
    private val arrayType: String? = null,
) {
    fun numberType(
        propertyName: String,
    ): String {
        // flags
        if (propertyName == propertyName.uppercase()
            || parentType == "SVGAnimatedEnumeration"
            || propertyName in FLAG_NAMES
        ) return "Short"

        if (parentType == "HTMLCanvasElement" || parentType == "ImageBitmap" || parentType == "ImageData") {
            when (propertyName) {
                "width",
                "height",
                -> return "Int"
            }
        }

        return when {
            propertyName == "lastModified" -> "EpochTimeStamp"
            propertyName == "expiration" -> "EpochTimeStamp"

            parentType.endsWith("DoubleRange") -> "Double"
            parentType.endsWith("ULongRange") -> "Int"

            parentType in DOM_GEOMETRY_TYPES -> "Double"
            parentType.startsWith("Touch") -> "Double"

            parentType == "CSSNumericType" -> "Int"
            parentType == "GamepadEffectParameters" -> "Double"

            parentType == "MediaError" -> "Short"
            parentType == "GeolocationPositionError" -> "Short"
            parentType == "GeolocationCoordinates" -> "Double"
            parentType == "PositionOptions" -> "JsLong"

            parentType == "PannerOptions" -> "Double"

            parentType == "Screen" -> "Int"
            parentType == "TextEncoderEncodeIntoResult" -> "Int"

            parentType == "SVGAnimatedInteger" -> "Int"
            parentType == "SVGAnimatedNumber" -> "Double"

            propertyName.startsWith("numberOf") -> "Int"
            propertyName.endsWith("Count") -> "Int"
            propertyName.endsWith("Digits") -> "Int"
            propertyName.endsWith("Frames") -> "Int"

            propertyName in LONG_NAMES -> "JsLong"
            propertyName in INT_NAMES -> "Int"
            propertyName in DOUBLE_NAMES -> "Double"

            parentType == "TextMetrics" -> "Double"
            parentType.startsWith("Canvas") -> "Double"

            // TEMP
            parentType.startsWith("RTC") -> "Number"

            else -> {
                println("$parentType.$propertyName")

                // TODO("No numberability configuration for property '$propertyName'")
                "Number"
            }
        }
    }

    fun isDefined(): Boolean =
        parentType == "LocaleOptions" || parentType == "AbstractWorker"

    fun isArrayLike(): Boolean =
        arrayType != null

    fun accepted(
        name: String,
    ): Boolean {
        if (name == "length" && isArrayLike())
            return false

        if (parentType == "Window")
            return name !in WINDOW_EXCLUDED

        if (parentType == "WorkerGlobalScope")
            return name != "self"


        return true
    }
}
