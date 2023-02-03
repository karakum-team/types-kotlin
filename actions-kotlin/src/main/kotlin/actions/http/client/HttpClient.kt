package actions.http.client

external interface HttpClient {
    // options(requestUrl: string, additionalHeaders?: http.OutgoingHttpHeaders): Promise<HttpClientResponse>
    // get(requestUrl: string, additionalHeaders?: http.OutgoingHttpHeaders): Promise<HttpClientResponse>
    // del(requestUrl: string, additionalHeaders?: http.OutgoingHttpHeaders): Promise<HttpClientResponse>
    // post(requestUrl: string, data: string, additionalHeaders?: http.OutgoingHttpHeaders): Promise<HttpClientResponse>
    // patch(requestUrl: string, data: string, additionalHeaders?: http.OutgoingHttpHeaders): Promise<HttpClientResponse>
    // put(requestUrl: string, data: string, additionalHeaders?: http.OutgoingHttpHeaders): Promise<HttpClientResponse>
    // sendStream(verb: string, requestUrl: string, stream: NodeJS.ReadableStream, additionalHeaders?: http.OutgoingHttpHeaders): Promise<HttpClientResponse>
    // request(verb: string, requestUrl: string, data: string | NodeJS.ReadableStream, headers: http.OutgoingHttpHeaders): Promise<HttpClientResponse>
    // requestRaw(info: RequestInfo, data: string | NodeJS.ReadableStream): Promise<HttpClientResponse>
    // requestRawWithCallback(info: RequestInfo, data: string | NodeJS.ReadableStream, onResult: (err?: Error, res?: HttpClientResponse) => void): void
}
