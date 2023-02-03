package actions.http.client

external interface RequestHandler {
    fun prepareRequest(options: node.http.RequestOptions)
    fun canHandleAuthentication(response: HttpClientResponse): Boolean
    fun handleAuthentication(
        httpClient: HttpClient,
        requestInfo: RequestInfo,
        data: node.string | NodeJS.ReadableStream | null): Promise<HttpClientResponse>
}
