// Automatically generated - do not modify!

package actions.cache

import kotlin.js.Promise

external fun <T> retryTypedResponse(
    name: String,
    method: () -> Promise<ITypedResponseWithError<T>>,
    maxAttempts: Number = definedExternally,
    delay: Number = definedExternally,
): Promise<ITypedResponseWithError<T>>
