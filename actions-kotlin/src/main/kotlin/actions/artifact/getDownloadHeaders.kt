package actions.artifact

external fun getDownloadHeaders(
    contentType: String,
    isKeepAlive: Boolean = definedExternally,
    acceptGzip: Boolean = definedExternally,
): OutgoingHttpHeaders
