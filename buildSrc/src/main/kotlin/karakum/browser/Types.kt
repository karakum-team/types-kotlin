package karakum.browser

import karakum.common.unionBody
import java.io.File

private val PKG_MAP = mapOf(
    "ResizeObserverBoxOptions" to "dom.observers",
    "CanPlayTypeResult" to "dom.html",
    "SelectionMode" to "dom.html",
    "TouchType" to "dom.events",

    "MediaDecodingType" to "media.capabilities",
    "MediaEncodingType" to "media.capabilities",
    "TransferFunction" to "media.capabilities",

    "RecordingState" to "media.capture",

    "AppendMode" to "media.source",
    "ReadyState" to "media.source",

    "AutomationRate" to "web.audio",

    "ScrollRestoration" to "web.history",

    "SpeechSynthesisErrorCode" to "web.speech",

    "BinaryType" to "websockets",

    "AutoKeyword" to "webvtt",
)

internal fun browserTypes(
    definitionsFile: File,
): Sequence<ConversionResult> {
    val content = definitionsFile.readText()

    return content
        .splitToSequence("\ntype ")
        .drop(1)
        .map { it.substringBefore(";\n") }
        .mapNotNull { convertType(it) }
}

private fun convertType(
    source: String,
): ConversionResult? {
    if (" = \"" !in source)
        return null

    val (name, bodySource) = source
        .substringBefore(";")
        .split(" = ")

    val pkg = when {
        PKG_MAP.containsKey(name) -> PKG_MAP.getValue(name)

        name.endsWith("Setting") -> "webvtt"

        name.startsWith("Document") -> "dom"
        name.startsWith("Scroll") -> "dom"

        name.startsWith("Animation") -> "web.animations"
        name.startsWith("Audio") -> "web.audio"
        name.startsWith("Channel") -> "web.audio"
        name.startsWith("Canvas") -> "canvas"
        name.startsWith("Gamepad") -> "web.gamepad"
        name.startsWith("IDB") -> "web.idb"

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
        name.startsWith("ServiceWorker") -> "serviceworkers"
        name.startsWith("TextTrack") -> "webvtt"
        name.startsWith("Worker") -> "web.workers"

        else -> {
            println(name)
            return null
        }
    }

    val parentPkg = when {
        name == "CanvasFontKerning" ||
                name == "CanvasFontStretch" ||
                name == "CanvasFontVariantCaps" ||
                name == "CanvasTextRendering"
        -> null

        name == "ScrollSetting"
        -> null

        name.startsWith("Request") ||
                name.startsWith("Response")
        -> "org.w3c.fetch"

        name.startsWith("Canvas") ||
        name == "DocumentReadyState" ||
                name == "CanPlayTypeResult" ||
                name.startsWith("Scroll")
        -> "org.w3c.dom"

        else -> null
    }

    val values = bodySource
        .splitToSequence(" | ")
        .map { it.removeSurrounding("\"") }
        .toList()

    var body = unionBody(name, values)
    if (parentPkg != null) {
        body = body.replaceFirst("class $name", "class $name :\n/* legacy adapter */ $parentPkg.$name")
    }

    return ConversionResult(
        name = name,
        body = body,
        pkg = pkg
    )
}
