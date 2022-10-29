package karakum.browser

internal fun browserFunctionTypes(
    content: String,
): Sequence<ConversionResult> =
    Regex("""interface .+? \{\n    \([\s\S]+?\}""")
        .findAll(content)
        .map { it.value }
        .mapNotNull { convertFunctionType(it) }

private fun convertFunctionType(
    source: String,
): ConversionResult? {
    val name = source.substringAfter(" ")
        .substringBefore(" ")

    val pkg = when {
        name == "BlobCallback" -> "dom.html"
        name == "VideoFrameRequestCallback" -> "dom.html"

        name == "RemotePlaybackAvailabilityCallback" -> "remoteplayback"

        name == "ErrorCallback" -> "web.filesystem"
        name == "FileCallback" -> "web.filesystem"

        name == "LockGrantedCallback" -> "web.locks"

        name.startsWith("RTC") -> "webrtc"
        name == "VoidFunction" -> "webrtc"

        else -> return null
    }

    var bodySource = source.substringAfter("\n    ")
        .substringBefore(";")
        .replace(": boolean", ": Boolean")
        .replace(": DOMException", ": Throwable /* DOMException */")
        .replace(": DOMHighResTimeStamp", ": HighResTimeStamp")
        .replace("): void", ") -> Unit")
        .replace(" | null", "?")

    if ("()" !in bodySource)
        bodySource = bodySource
            .replaceFirst("(", "(\n")
            .replace(", ", ",\n")

    var body = "typealias $name = $bodySource"

    return ConversionResult(
        name = name,
        body = body,
        pkg = pkg
    )
}
