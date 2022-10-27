package karakum.browser

import java.io.File

internal fun browserFunctionTypes(
    definitionsFile: File,
): Sequence<ConversionResult> {
    val content = definitionsFile.readText()

    return Regex("""interface .+? \{\n    \([\s\S]+?\}""")
        .findAll(content)
        .map { it.value }
        .mapNotNull { convertFunctionType(it) }
}

private fun convertFunctionType(
    source: String,
): ConversionResult? {
    val name = source.substringAfter(" ")
        .substringBefore(" ")

    val pkg = when {
        name.startsWith("RTC") -> "webrtc"
        name == "VideoFrameRequestCallback" -> "dom.html"

        else -> return null
    }

    val bodySource = source.substringAfter("\n    ")
        .substringBefore(";")
        .replace(": DOMException", ": Throwable")
        .replace(": DOMHighResTimeStamp", ": HighResTimeStamp")
        .replace("): void", ") -> Unit")
        .replaceFirst("(", "(\n")
        .replace(", ", ",\n")

    var body = "typealias $name = $bodySource"

    return ConversionResult(
        name = name,
        body = body,
        pkg = pkg
    )
}
