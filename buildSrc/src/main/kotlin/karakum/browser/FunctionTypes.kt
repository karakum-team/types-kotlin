package karakum.browser

private val STREAMS_FUNCTION_TYPES = setOf(
    "QueuingStrategySize",
    "ReadableStreamErrorCallback",
    "TransformerFlushCallback",
    "TransformerStartCallback",
    "TransformerTransformCallback",
    "UnderlyingSinkAbortCallback",
    "UnderlyingSinkCloseCallback",
    "UnderlyingSinkStartCallback",
    "UnderlyingSinkWriteCallback",
    "UnderlyingSourceCancelCallback",
    "UnderlyingSourcePullCallback",
    "UnderlyingSourceStartCallback",
)

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
    val declaration = source
        .substringAfter(" ")
        .substringBefore(" {")
        .replace(" = any>", ">")

    val name = declaration.substringBefore("<")

    val pkg = when {
        name == "FrameRequestCallback" -> "web.timers"
        name == "IdleRequestCallback" -> "web.timers"

        name in STREAMS_FUNCTION_TYPES -> "web.streams"

        name == "PerformanceObserverCallback" -> "web.performance"

        name == "FunctionStringCallback" -> "web.data"

        name == "WebCodecsErrorCallback" -> "web.codecs"
        name == "VideoFrameOutputCallback" -> "web.codecs"
        name == "EncodedVideoChunkOutputCallback" -> "web.codecs"

        name.startsWith("IntersectionObserver") -> "web.dom.observers"
        name == "MutationCallback" -> "web.dom.observers"
        name.startsWith("ResizeObserver") -> "web.dom.observers"

        name == "BlobCallback" -> "web.html"
        name == "VideoFrameRequestCallback" -> "web.html"

        name == "MediaSessionActionHandler" -> "web.media.session"

        name == "RemotePlaybackAvailabilityCallback" -> "web.remoteplayback"

        name == "DecodeSuccessCallback" -> "web.audio"
        name == "DecodeErrorCallback" -> "web.audio"

        name == "ErrorCallback" -> "web.filesystem"
        name == "FileCallback" -> "web.filesystem"
        name.startsWith("FileSystem") -> "web.filesystem"

        name.startsWith("Position") -> "web.geolocation"

        name == "LockGrantedCallback" -> "web.locks"

        name == "NotificationPermissionCallback" -> "web.notifications"

        name == "ReportingObserverCallback" -> "web.reporting"

        name.startsWith("RTC") -> "webrtc"
        name == "VoidFunction" -> "webrtc"

        else -> return null
    }

    var bodySource = source.substringAfter("\n    ")
        .substringBefore(";")
        .replace(": boolean", ": Boolean")
        .replace(": string", ": String")
        .replace(": FileSystemEntry[]", ": ReadonlyArray<FileSystemEntry>")
        .replace(": IntersectionObserverEntry[]", ": ReadonlyArray<IntersectionObserverEntry>")
        .replace(": MutationRecord[]", ": ReadonlyArray<MutationRecord>")
        .replace(": ResizeObserverEntry[]", ": ReadonlyArray<ResizeObserverEntry>")
        .replace(": Report[]", ": ReadonlyArray<Report>")
        .replace("?: EncodedVideoChunkMetadata", ": EncodedVideoChunkMetadata?")
        .replace("): void | PromiseLike<void>", ") -> PromiseLike<Void>?")
        // QueuingStrategySize
        .replace("): number", ") -> Int")
        .replace("): void", ") -> Unit")
        .replace("): any", ") -> Unit")
        .replace("?: any", ": Any?")
        .replace(" | null", "?")

    if ("()" !in bodySource)
        bodySource = bodySource
            .replaceFirst("(", "(\n")
            .replace(", ", ",\n")

    val body = "typealias $declaration = $bodySource"

    return ConversionResult(
        name = name,
        body = body,
        pkg = pkg
    )
}
