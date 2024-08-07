// Automatically generated - do not modify!

@file:JsModule("@actions/cache")

package actions.cache

import js.errors.JsError
import js.promise.Promise
import seskar.js.JsAsync

@JsAsync
external suspend fun <T> retry(
    name: String,
    method: () -> Promise<T>,
    getStatusCode: (arg0: T) -> Number?,
    maxAttempts: Number = definedExternally,
    delay: Number = definedExternally,
    onError: ((arg0: JsError) -> T?)? = definedExternally,
): T

@JsName("retry")
external fun <T> retryAsync(
    name: String,
    method: () -> Promise<T>,
    getStatusCode: (arg0: T) -> Number?,
    maxAttempts: Number = definedExternally,
    delay: Number = definedExternally,
    onError: ((arg0: JsError) -> T?)? = definedExternally,
): Promise<T>
