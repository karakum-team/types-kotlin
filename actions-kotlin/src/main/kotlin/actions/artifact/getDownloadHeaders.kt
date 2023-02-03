package actions.artifact

import node.http.OutgoingHttpHeaders

external fun getDownloadHeaders(
    contentType: String,
    isKeepAlive: Boolean = definedExternally,
    acceptGzip: Boolean = definedExternally,
): OutgoingHttpHeaders
