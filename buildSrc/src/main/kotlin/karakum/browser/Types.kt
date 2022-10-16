package karakum.browser

import karakum.common.unionBody
import java.io.File

private val PKG_MAP = mapOf(
    "BinaryType" to "websockets",
    "SelectionMode" to "dom.html",
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
    val (name, body) = source
        .substringBefore(";")
        .split(" = ")

    if (!body.startsWith("\""))
        return null

    val pkg = when {
        name.startsWith("RTC") -> "webrtc"
        else -> PKG_MAP[name] ?: return null
    }

    val values = body
        .splitToSequence(" | ")
        .map { it.removeSurrounding("\"") }
        .toList()

    return ConversionResult(
        name = name,
        body = unionBody(name, values),
        pkg = pkg
    )
}
