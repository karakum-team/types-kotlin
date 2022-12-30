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
        name == "FunctionStringCallback" -> "dom.data"

        name.startsWith("IntersectionObserver") -> "dom.observers"
        name == "MutationCallback" -> "dom.observers"
        name.startsWith("ResizeObserver") -> "dom.observers"

        name == "BlobCallback" -> "dom.html"
        name == "VideoFrameRequestCallback" -> "dom.html"

        name == "MediaSessionActionHandler" -> "media.session"

        name == "RemotePlaybackAvailabilityCallback" -> "web.remoteplayback"

        name == "DecodeSuccessCallback" -> "web.audio"
        name == "DecodeErrorCallback" -> "web.audio"

        name == "ErrorCallback" -> "web.filesystem"
        name == "FileCallback" -> "web.filesystem"
        name.startsWith("FileSystem") -> "web.filesystem"

        name.startsWith("Position") -> "web.geolocation"

        name == "LockGrantedCallback" -> "web.locks"

        name == "NotificationPermissionCallback" -> "web.notifications"

        name.startsWith("RTC") -> "webrtc"
        name == "VoidFunction" -> "webrtc"

        else -> return null
    }

    var bodySource = source.substringAfter("\n    ")
        .substringBefore(";")
        .replace(": boolean", ": Boolean")
        .replace(": string", ": String")
        .replace(": DOMHighResTimeStamp", ": HighResTimeStamp")
        .replace(": FileSystemEntry[]", ": ReadonlyArray<FileSystemEntry>")
        .replace(": IntersectionObserverEntry[]", ": ReadonlyArray<IntersectionObserverEntry>")
        .replace(": MutationRecord[]", ": ReadonlyArray<MutationRecord>")
        .replace(": ResizeObserverEntry[]", ": ReadonlyArray<ResizeObserverEntry>")
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
