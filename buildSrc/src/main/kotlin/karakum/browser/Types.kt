package karakum.browser

import karakum.common.unionBody

private val PKG_MAP = mapOf(
    "ColorSpaceConversion" to "canvas",
    "GlobalCompositeOperation" to "canvas",
    "ImageOrientation" to "canvas",
    "ImageSmoothingQuality" to "canvas",
    "OffscreenRenderingContextId" to "canvas",
    "PredefinedColorSpace" to "canvas",
    "PremultiplyAlpha" to "canvas",
    "ResizeQuality" to "canvas",

    "InsertPosition" to "dom",
    "MutationRecordType" to "dom.observers",
    "ResizeObserverBoxOptions" to "dom.observers",
    "CanPlayTypeResult" to "dom.html",
    "SelectionMode" to "dom.html",
    "ShadowRootMode" to "dom.html",
    "SlotAssignmentMode" to "dom.html",
    "TouchType" to "dom.events",
    "DOMParserSupportedType" to "dom.parsing",

    "ColorGamut" to "media.capabilities",
    "HdrMetadataType" to "media.capabilities",
    "MediaDecodingType" to "media.capabilities",
    "MediaEncodingType" to "media.capabilities",
    "TransferFunction" to "media.capabilities",

    "RecordingState" to "media.capture",

    "AppendMode" to "media.source",
    "EndOfStreamError" to "media.source",
    "ReadyState" to "media.source",

    "RemotePlaybackState" to "remoteplayback",

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

    "VideoMatrixCoefficients" to "web.codecs",
    "VideoColorPrimaries" to "web.codecs",
    "VideoTransferCharacteristics" to "web.codecs",

    "ScrollRestoration" to "web.history",

    "PushEncryptionKeyName" to "web.push",

    "SpeechSynthesisErrorCode" to "web.speech",

    "CredentialMediationRequirement" to "web.credentials",
    "SecurityPolicyViolationEventDisposition" to "web.csp",

    "BinaryType" to "websockets",

    "AutoKeyword" to "webvtt",
)

private val EXCLUDED_TYPES = setOf(
    // buffer
    "EndingType",

    // webcrypto
    "KeyFormat",
    "KeyType",
    "KeyUsage",
    "NamedCurve",

    // webstreams
    "ReadableStreamType",
    "ReadableStreamReaderMode",

    // deprecated?
    "NavigationTimingType",

    // not used?
    "ClientTypes",
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
            "CanvasImageSource" -> "canvas"
            "ImageBitmapSource" -> "canvas"

            "MediaProvider" -> "dom.html"
            "WindowProxy" -> "dom.html"

            "IDBValidKey" -> "web.idb"

            "BodyInit" -> "web.http"
            "HeadersInit" -> "web.http"
            "FormDataEntryValue" -> "web.http"

            "XMLHttpRequestBodyInit" -> "web.xhr"

            else -> return null
        }

        val body = if (" | " in bodySource) "Any /* $bodySource */" else bodySource

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

    return ConversionResult(
        name = name,
        body = unionBody(name, values),
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

        name.startsWith("Document") -> "dom"
        name.startsWith("Fullscreen") -> "dom"
        name.startsWith("Scroll") -> "dom"
        name.startsWith("FontFace") -> "cssom.fonts"

        name.startsWith("Animation") -> "web.animations"
        name.startsWith("Audio") -> "web.audio"
        name.startsWith("Channel") -> "web.audio"

        name.startsWith("Canvas") -> "canvas"

        name.startsWith("Gamepad") -> "web.gamepad"
        name.startsWith("IDB") -> "web.idb"

        name.startsWith("FileSystem") -> "web.filesystem"
        name.startsWith("Lock") -> "web.locks"

        name.startsWith("MediaDevice") -> "media.devices"
        name.startsWith("MediaKey") -> "media.key"
        name.startsWith("MediaSession") -> "media.session"
        name.startsWith("MediaStream") -> "media.stream"

        name.startsWith("Notification") -> "web.notifications"
        name.startsWith("Orientation") -> "web.screen"
        name.startsWith("Payment") -> "web.payment"
        name.startsWith("Permission") -> "web.permissions"

        name.startsWith("Referrer") -> "web.http"
        name.startsWith("Request") -> "web.http"
        name.startsWith("Response") -> "web.http"

        name.startsWith("RTC") -> "webrtc"
        name.startsWith("ServiceWorker") -> "serviceworkers"
        name.startsWith("TextTrack") -> "webvtt"
        name.startsWith("Worker") -> "web.workers"

        name.startsWith("WebGL") -> "webgl"
        name.startsWith("XMLHttp") -> "web.xhr"

        else -> TODO("Unable to find package for `$name` union")
    }
