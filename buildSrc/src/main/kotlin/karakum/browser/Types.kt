package karakum.browser

import karakum.common.unionBody
import java.io.File

private val PKG_MAP = mapOf(
    "SelectionMode" to "dom.html",

    "BinaryType" to "websockets",
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
        name.startsWith("Canvas") -> "canvas"
        name.startsWith("Gamepad") -> "web.gamepad"
        name.startsWith("IDB") -> "web.idb"
        name.startsWith("Orientation") -> "web.screen"
        name.startsWith("Payment") -> "web.payment"
        name.startsWith("Permission") -> "web.permissions"
        name.startsWith("RTC") -> "webrtc"
        name.startsWith("TextTrack") -> "webvtt"
        else -> PKG_MAP[name] ?: return null
    }

    val parentPkg = when {
        name == "CanvasFontKerning" ||
                name == "CanvasFontStretch" ||
                name == "CanvasFontVariantCaps" ||
                name == "CanvasTextRendering"
        -> null

        name.startsWith("Canvas")
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
