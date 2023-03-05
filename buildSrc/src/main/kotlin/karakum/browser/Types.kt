package karakum.browser

import karakum.common.objectUnionBody
import karakum.common.unionBody
import karakum.common.unionConstant

private val PKG_MAP = mapOf(
    "NavigationTimingType" to "web.performance",

    "ColorSpaceConversion" to "web.canvas",
    "GlobalCompositeOperation" to "web.canvas",
    "ImageOrientation" to "web.canvas",
    "ImageSmoothingQuality" to "web.canvas",
    "OffscreenRenderingContextId" to "web.canvas",
    "PredefinedColorSpace" to "web.canvas",
    "PremultiplyAlpha" to "web.canvas",
    "ResizeQuality" to "web.canvas",

    "CSSMathOperator" to "web.cssom",
    "CSSNumericBaseType" to "web.cssom",

    "InsertPosition" to "web.dom",
    "MutationRecordType" to "web.dom.observers",
    "ResizeObserverBoxOptions" to "web.dom.observers",
    "CanPlayTypeResult" to "web.html",
    "SelectionMode" to "web.html",
    "ShadowRootMode" to "web.html",
    "SlotAssignmentMode" to "web.html",
    "TouchType" to "web.uievents",
    "DOMParserSupportedType" to "web.dom.parsing",

    "ColorGamut" to "web.media.capabilities",
    "HdrMetadataType" to "web.media.capabilities",
    "MediaDecodingType" to "web.media.capabilities",
    "MediaEncodingType" to "web.media.capabilities",
    "TransferFunction" to "web.media.capabilities",

    "RecordingState" to "web.media.recorder",

    "AppendMode" to "web.media.source",
    "EndOfStreamError" to "web.media.source",
    "ReadyState" to "web.media.source",

    "RemotePlaybackState" to "web.remoteplayback",
    "ClientTypes" to "web.serviceworker",

    "AttestationConveyancePreference" to "web.authentication",
    "AuthenticatorAttachment" to "web.authentication",
    "AuthenticatorTransport" to "web.authentication",
    "PublicKeyCredentialType" to "web.authentication",
    "ResidentKeyRequirement" to "web.authentication",
    "UserVerificationRequirement" to "web.authentication",

    "CompositeOperation" to "web.animations",
    "CompositeOperationOrAuto" to "web.animations",
    "FillMode" to "web.animations",
    "IterationCompositeOperation" to "web.animations",
    "PlaybackDirection" to "web.animations",

    "AutomationRate" to "web.audio",
    "BiquadFilterType" to "web.audio",
    "DistanceModelType" to "web.audio",
    "OscillatorType" to "web.audio",
    "OverSampleType" to "web.audio",
    "PanningModelType" to "web.audio",

    "PresentationStyle" to "web.clipboard",

    "AlphaOption" to "web.codecs",
    "AvcBitstreamFormat" to "web.codecs",
    "BitrateMode" to "web.codecs",
    "CodecState" to "web.codecs",
    "EncodedVideoChunkType" to "web.codecs",
    "HardwareAcceleration" to "web.codecs",
    "LatencyMode" to "web.codecs",
    "VideoMatrixCoefficients" to "web.codecs",
    "VideoColorPrimaries" to "web.codecs",
    "VideoTransferCharacteristics" to "web.codecs",
    "VideoPixelFormat" to "web.codecs",

    "ScrollRestoration" to "web.history",

    "PushEncryptionKeyName" to "web.push",

    "SpeechSynthesisErrorCode" to "web.speech",

    "CredentialMediationRequirement" to "web.credentials",
    "SecurityPolicyViolationEventDisposition" to "web.csp",

    "WakeLockType" to "web.wakelock",

    "BinaryType" to "websockets",

    "AutoKeyword" to "webvtt",

    "KeyFormat" to "web.crypto",
    "KeyType" to "web.crypto",
    "KeyUsage" to "web.crypto",
    "NamedCurve" to "web.crypto",
)

private val EXCLUDED_TYPES = setOf(
    // buffer
    "EndingType",

    // webstreams
    "ReadableStreamType",
    "ReadableStreamReaderMode",

    "DisplayCaptureSurfaceType",
    "VideoFacingModeEnum",
)

internal fun browserTypes(
    content: String,
): Sequence<ConversionResult> =
    convertTypes(content, ::getTypePkg)

internal fun convertTypes(
    content: String,
    getPkg: (name: String) -> String?,
): Sequence<ConversionResult> =
    content
        .splitToSequence("\ntype ")
        .drop(1)
        .map { it.substringBefore(";\n") }
        .mapNotNull { convertType(it, getPkg) }

private fun convertType(
    source: String,
    getPkg: (name: String) -> String?,
): ConversionResult? {
    if (" = " !in source)
        return null

    val (name, bodySource) = source
        .substringBefore(";")
        .split(" = ")

    if (bodySource == "string") {
        val pkg = getPkg(name)
            ?: return null

        return ConversionResult(
            name = name,
            body = "typealias $name = String",
            pkg = pkg
        )
    }

    if (!bodySource.startsWith("\"")) {
        val pkg = when (name) {
            "PerformanceEntryList" -> "web.performance"

            "CanvasImageSource" -> "web.canvas"
            "ImageBitmapSource" -> "web.canvas"

            "HTMLOrSVGImageElement" -> "web.dom"
            "HTMLOrSVGScriptElement" -> "web.dom"

            "MediaProvider" -> "web.html"
            "WindowProxy" -> "web.window"

            "VibratePattern" -> "web.vibration"

            "ClipboardItems" -> "web.clipboard"

            "IDBValidKey" -> "web.idb"

            "BodyInit" -> "web.http"
            "HeadersInit" -> "web.http"
            "FormDataEntryValue" -> "web.http"

            "XMLHttpRequestBodyInit" -> "web.xhr"

            "TexImageSource" -> "webgl"

            "BigInteger" -> "web.crypto"
            "HashAlgorithmIdentifier" -> "web.crypto"

            else -> if (name.startsWith("Constrain")) {
                "web.media.streams"
            } else return null
        }

        val body = when {
            bodySource == "ClipboardItem[]"
            -> "ReadonlyArray<ClipboardItem>"

            bodySource == "PerformanceEntry[]"
            -> "ReadonlyArray<PerformanceEntry>"

            name == "VibratePattern" && bodySource == "number | number[]"
            -> "ReadonlyArray<Int> /* | Int */"

            " | " in bodySource || bodySource == "AlgorithmIdentifier"
            -> "Any /* $bodySource */"

            else -> bodySource
        }

        return ConversionResult(
            name = name,
            body = "typealias $name = $body",
            pkg = pkg
        )
    }

    val pkg = getPkg(name)
        ?: return null

    val values = bodySource
        .splitToSequence(" | ")
        .map { it.removeSurrounding("\"") }
        .toList()

    val body = when (name) {
        "KeyFormat",
        -> objectUnionBody(
            name = name,
            constants = values.map(::unionConstant),
        )

        else -> unionBody(name, values)
    }

    return ConversionResult(
        name = name,
        body = body,
        pkg = pkg
    )
}

private fun getTypePkg(
    name: String,
): String? =
    when {
        name in EXCLUDED_TYPES -> null

        PKG_MAP.containsKey(name) -> PKG_MAP.getValue(name)

        name.endsWith("Setting") -> "webvtt"

        name.startsWith("Document") -> "web.dom"
        name.startsWith("Fullscreen") -> "web.fullscreen"
        name.startsWith("Scroll") -> "web.scroll"

        name == "FontDisplay" -> "web.fonts"
        name.startsWith("FontFace") -> "web.fonts"

        name.startsWith("Animation") -> "web.animations"
        name.startsWith("Audio") -> "web.audio"
        name.startsWith("Channel") -> "web.audio"

        name.startsWith("Canvas") -> "web.canvas"

        name.startsWith("Gamepad") -> "web.gamepad"
        name.startsWith("IDB") -> "web.idb"

        name.startsWith("MIDI") -> "web.midi"

        name.startsWith("FileSystem") -> "web.filesystem"
        name.startsWith("Lock") -> "web.locks"

        name.startsWith("MediaDevice") -> "web.media.devices"
        name.startsWith("MediaKey") -> "web.media.key"
        name.startsWith("MediaSession") -> "web.media.session"
        name.startsWith("MediaStream") -> "web.media.streams"

        name.startsWith("Notification") -> "web.notifications"
        name.startsWith("Orientation") -> "web.screen"
        name.startsWith("Payment") -> "web.payment"
        name.startsWith("Permission") -> "web.permissions"

        name.startsWith("Referrer") -> "web.http"
        name.startsWith("Request") -> "web.http"
        name.startsWith("Response") -> "web.http"

        name.startsWith("RTC") -> "webrtc"
        name.startsWith("ServiceWorker") -> "web.serviceworker"
        name.startsWith("TextTrack") -> "webvtt"
        name.startsWith("Worker") -> "web.workers"

        name.startsWith("WebGL") -> "webgl"
        name.startsWith("XMLHttp") -> "web.xhr"

        else -> TODO("Unable to find package for `$name` union")
    }
