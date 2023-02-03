package actions.artifact

import kotlin.js.Promise

external fun retryHttpClientRequest(
    name: String,
    method: () -> Promise<HttpClientResponse>,
    customErrorMessages: Map<number = definedExternally,
    string>: string>,
maxAttempts: Number = definedExternally): Promise<HttpClientResponse>
