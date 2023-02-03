package actions.http.client

import kotlin.js.Promise

external interface HttpClient {
    fun options(
        requestUrl: String,
        additionalHeaders: node.http.OutgoingHttpHeaders = definedExternally,
    ): Promise<HttpClientResponse>

    fun get(
        requestUrl: String,
        additionalHeaders: node.http.OutgoingHttpHeaders = definedExternally,
    ): Promise<HttpClientResponse>

    fun del(
        requestUrl: String,
        additionalHeaders: node.http.OutgoingHttpHeaders = definedExternally,
    ): Promise<HttpClientResponse>

    fun post(
        requestUrl: String,
        data: String,
        additionalHeaders: node.http.OutgoingHttpHeaders = definedExternally,
    ): Promise<HttpClientResponse>

    fun patch(
        requestUrl: String,
        data: String,
        additionalHeaders: node.http.OutgoingHttpHeaders = definedExternally,
    ): Promise<HttpClientResponse>

    fun put(
        requestUrl: String,
        data: String,
        additionalHeaders: node.http.OutgoingHttpHeaders = definedExternally,
    ): Promise<HttpClientResponse>

    fun sendStream(
        verb: String,
        requestUrl: String,
        stream: node.ReadableStream,
        additionalHeaders: node.http.OutgoingHttpHeaders = definedExternally,
    ): Promise<HttpClientResponse>

    fun request(
        verb: String,
        requestUrl: String,
        data: node.string | NodeJS.ReadableStream,
    headers: node.http.OutgoingHttpHeaders): Promise<HttpClientResponse>
    fun requestRaw(
        info: RequestInfo,
        data: node.string | NodeJS.ReadableStream): Promise<HttpClientResponse>
    fun requestRawWithCallback(
        info: RequestInfo,
        data: node.string | NodeJS.ReadableStream,
    onResult: (err?: Error,
    res: HttpClientResponse) -> Unit = definedExternally)
}
