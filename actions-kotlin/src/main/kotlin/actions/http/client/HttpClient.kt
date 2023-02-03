// Automatically generated - do not modify!

package actions.http.client

import js.errors.JsError
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
        data: String,
        headers: node.http.OutgoingHttpHeaders,
    ): Promise<HttpClientResponse>

    fun request(
        verb: String,
        requestUrl: String,
        data: node.ReadableStream,
        headers: node.http.OutgoingHttpHeaders,
    ): Promise<HttpClientResponse>

    fun requestRaw(
        info: RequestInfo,
        data: String,
    ): Promise<HttpClientResponse>

    fun requestRaw(
        info: RequestInfo,
        data: node.ReadableStream,
    ): Promise<HttpClientResponse>

    fun requestRawWithCallback(
        info: RequestInfo,
        data: String,
        onResult: (err: JsError?, res: HttpClientResponse?) -> Unit,
    )

    fun requestRawWithCallback(
        info: RequestInfo,
        data: node.ReadableStream,
        onResult: (err: JsError?, res: HttpClientResponse?) -> Unit,
    )
}
