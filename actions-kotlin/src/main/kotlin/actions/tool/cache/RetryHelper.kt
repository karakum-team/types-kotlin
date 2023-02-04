// Automatically generated - do not modify!

@file:JsModule("@actions/tool-cache")

package actions.tool.cache

import kotlin.js.Promise

external class RetryHelper {
    // constructor(maxAttempts: number, minSeconds: number, maxSeconds: number)
    fun <T> execute(
        action: () -> Promise<T>,
        isRetryable: (e: Error) -> Boolean = definedExternally,
    ): Promise<T>
}
