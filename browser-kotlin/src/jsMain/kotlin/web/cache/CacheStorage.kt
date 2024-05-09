// Automatically generated - do not modify!

package web.cache

import js.array.ReadonlyArray
import js.promise.Promise
import web.http.Request
import web.http.Response
import web.url.URL

/**
 * The storage for Cache objects.
 * Available only in secure contexts.
 *
 * [MDN Reference](https://developer.mozilla.org/docs/Web/API/CacheStorage)
 */
sealed external class CacheStorage {
    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/CacheStorage/delete)
     */
    suspend fun delete(cacheName: String): Boolean

    @JsName("delete")
    fun deleteAsync(cacheName: String): Promise<Boolean>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/CacheStorage/has)
     */
    suspend fun has(cacheName: String): Boolean

    @JsName("has")
    fun hasAsync(cacheName: String): Promise<Boolean>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/CacheStorage/keys)
     */
    suspend fun keys(): ReadonlyArray<String>

    @JsName("keys")
    fun keysAsync(): Promise<ReadonlyArray<String>>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/CacheStorage/match)
     */
    suspend fun match(
        request: Request,
        options: MultiCacheQueryOptions = definedExternally,
    ): Response?

    @JsName("match")
    fun matchAsync(
        request: Request,
        options: MultiCacheQueryOptions = definedExternally,
    ): Promise<Response?>

    suspend fun match(
        request: String,
        options: MultiCacheQueryOptions = definedExternally,
    ): Response?

    @JsName("match")
    fun matchAsync(
        request: String,
        options: MultiCacheQueryOptions = definedExternally,
    ): Promise<Response?>

    suspend fun match(
        request: URL,
        options: MultiCacheQueryOptions = definedExternally,
    ): Response?

    @JsName("match")
    fun matchAsync(
        request: URL,
        options: MultiCacheQueryOptions = definedExternally,
    ): Promise<Response?>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/CacheStorage/open)
     */
    suspend fun open(cacheName: String): Cache

    @JsName("open")
    fun openAsync(cacheName: String): Promise<Cache>
}
