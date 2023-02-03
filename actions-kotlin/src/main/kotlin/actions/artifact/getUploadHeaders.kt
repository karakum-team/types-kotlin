package actions.artifact

external fun getUploadHeaders(
    contentType: String,
    isKeepAlive: Boolean = definedExternally,
    isGzip: Boolean = definedExternally,
    uncompressedLength: Number = definedExternally,
    contentLength: Number = definedExternally,
    contentRange: String = definedExternally,
    digest: StreamDigest = definedExternally,
): OutgoingHttpHeaders
