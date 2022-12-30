package karakum.browser

import karakum.common.objectUnionBody
import karakum.common.unionBody
import karakum.common.unionConstant

private val PKG_MAP = mapOf(
    "ColorSpaceConversion" to "web.canvas",
    "GlobalCompositeOperation" to "web.canvas",
    "ImageOrientation" to "web.canvas",
    "ImageSmoothingQuality" to "web.canvas",
    "OffscreenRenderingContextId" to "web.canvas",
    "PredefinedColorSpace" to "web.canvas",
    "PremultiplyAlpha" to "web.canvas",
    "ResizeQuality" to "web.canvas",

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

    "RecordingState" to "media.recorder",

    "AppendMode" to "media.source",
    "EndOfStreamError" to "media.source",
    "ReadyState" to "media.source",

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

    // deprecated?
    "NavigationTimingType",

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
            "CanvasImageSource" -> "web.canvas"
            "ImageBitmapSource" -> "web.canvas"

            "HTMLOrSVGImageElement" -> "dom"
            "HTMLOrSVGScriptElement" -> "dom"

            "MediaProvider" -> "dom.html"
            "WindowProxy" -> "dom.html"

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
                "media.streams"
            } else return null
        }

        val body = when {
            bodySource == "ClipboardItem[]"
            -> "ReadonlyArray<ClipboardItem>"

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

        name.startsWith("Document") -> "dom"
        name.startsWith("Fullscreen") -> "dom"
        name.startsWith("Scroll") -> "dom"
        name.startsWith("FontFace") -> "web.fonts"

        name.startsWith("Animation") -> "web.animations"
        name.startsWith("Audio") -> "web.audio"
        name.startsWith("Channel") -> "web.audio"

        name.startsWith("Canvas") -> "web.canvas"

        name.startsWith("Gamepad") -> "web.gamepad"
        name.startsWith("IDB") -> "web.idb"

        name.startsWith("FileSystem") -> "web.filesystem"
        name.startsWith("Lock") -> "web.locks"

        name.startsWith("MediaDevice") -> "media.devices"
        name.startsWith("MediaKey") -> "media.key"
        name.startsWith("MediaSession") -> "media.session"
        name.startsWith("MediaStream") -> "media.streams"

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
