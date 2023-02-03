package actions.artifact

import kotlin.js.Promise

external fun retry(
    name: String,
    operation: () -> Promise<HttpClientResponse>,
    customErrorMessages: Map<number,
            string>
    : string>,
maxAttempts: Number): Promise<HttpClientResponse>
