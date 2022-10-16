package karakum.browser

import karakum.common.unionBody
import java.io.File

private val PKG_MAP = mapOf(
    "ResizeObserverBoxOptions" to "dom.observers",
    "CanPlayTypeResult" to "dom.html",
    "SelectionMode" to "dom.html",

    "AppendMode" to "media.source",
    "ReadyState" to "media.source",

    "AutomationRate" to "web.audio",

    "ScrollRestoration" to "web.history",

    "BinaryType" to "websockets",

    "AlignSetting" to "webvtt",
    "AutoKeyword" to "webvtt",
    "ScrollSetting" to "webvtt",
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
        name.startsWith("Animation") -> "web.animations"
        name.startsWith("Audio") -> "web.audio"
        name.startsWith("Channel") -> "web.audio"
        name.startsWith("Canvas") -> "canvas"
        name.startsWith("Gamepad") -> "web.gamepad"
        name.startsWith("IDB") -> "web.idb"
        name.startsWith("Orientation") -> "web.screen"
        name.startsWith("Notification") -> "web.notifications"
        name.startsWith("Payment") -> "web.payment"
        name.startsWith("Permission") -> "web.permissions"
        name.startsWith("RTC") -> "webrtc"
        name.startsWith("Scroll") -> "dom"
        name.startsWith("TextTrack") -> "webvtt"
        else -> return null
    }

    val parentPkg = when {
        name == "CanvasFontKerning" ||
                name == "CanvasFontStretch" ||
                name == "CanvasFontVariantCaps" ||
                name == "CanvasTextRendering"
        -> null

        name == "ScrollSetting"
        -> null

        name.startsWith("Canvas") ||
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
