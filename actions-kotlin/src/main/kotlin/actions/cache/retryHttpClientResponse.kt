package actions.cache

import kotlin.js.Promise

external fun retryHttpClientResponse(
    name: String,
    method: () -> Promise<HttpClientResponse>,
    maxAttempts: Number = definedExternally,
    delay: Number = definedExternally,
): Promise<HttpClientResponse>
