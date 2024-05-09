// Automatically generated - do not modify!

package web.cache

import js.array.ReadonlyArray
import js.core.Void
import js.promise.Promise
import web.http.Request
import web.http.Response
import web.url.URL

/**
 * Provides a storage mechanism for Request / Response object pairs that are cached, for example as part of the ServiceWorker life cycle. Note that the Cache interface is exposed to windowed scopes as well as workers. You don't have to use it in conjunction with service workers, even though it is defined in the service worker spec.
 * Available only in secure contexts.
 *
 * [MDN Reference](https://developer.mozilla.org/docs/Web/API/Cache)
 */
sealed external class Cache {
    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/Cache/add)
     */
    @JsName("add")
    fun addAsync(request: Request): Promise<Void>

    @JsName("add")
    fun addAsync(request: String): Promise<Void>

    @JsName("add")
    fun addAsync(request: URL): Promise<Void>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/Cache/addAll)
     */
    @JsName("addAll")
    fun addAllAsync(requests: ReadonlyArray<Request>): Promise<Void>

    @JsName("addAll")
    fun addAllAsync(requests: ReadonlyArray<String>): Promise<Void>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/Cache/delete)
     */
    @JsName("delete")
    fun deleteAsync(
        request: Request,
        options: CacheQueryOptions = definedExternally,
    ): Promise<Boolean>

    @JsName("delete")
    fun deleteAsync(
        request: String,
        options: CacheQueryOptions = definedExternally,
    ): Promise<Boolean>

    @JsName("delete")
    fun deleteAsync(
        request: URL,
        options: CacheQueryOptions = definedExternally,
    ): Promise<Boolean>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/Cache/keys)
     */
    @JsName("keys")
    fun keysAsync(
        request: Request = definedExternally,
        options: CacheQueryOptions = definedExternally,
    ): Promise<ReadonlyArray<Request>>

    @JsName("keys")
    fun keysAsync(
        request: String,
        options: CacheQueryOptions = definedExternally,
    ): Promise<ReadonlyArray<Request>>

    @JsName("keys")
    fun keysAsync(
        request: URL,
        options: CacheQueryOptions = definedExternally,
    ): Promise<ReadonlyArray<Request>>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/Cache/match)
     */
    @JsName("match")
    fun matchAsync(
        request: Request,
        options: CacheQueryOptions = definedExternally,
    ): Promise<Response?>

    @JsName("match")
    fun matchAsync(
        request: String,
        options: CacheQueryOptions = definedExternally,
    ): Promise<Response?>

    @JsName("match")
    fun matchAsync(
        request: URL,
        options: CacheQueryOptions = definedExternally,
    ): Promise<Response?>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/Cache/matchAll)
     */
    @JsName("matchAll")
    fun matchAllAsync(
        request: Request = definedExternally,
        options: CacheQueryOptions = definedExternally,
    ): Promise<ReadonlyArray<Response>>

    @JsName("matchAll")
    fun matchAllAsync(
        request: String,
        options: CacheQueryOptions = definedExternally,
    ): Promise<ReadonlyArray<Response>>

    @JsName("matchAll")
    fun matchAllAsync(
        request: URL,
        options: CacheQueryOptions = definedExternally,
    ): Promise<ReadonlyArray<Response>>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/Cache/put)
     */
    @JsName("put")
    fun putAsync(
        request: Request,
        response: Response,
    ): Promise<Void>

    @JsName("put")
    fun putAsync(
        request: String,
        response: Response,
    ): Promise<Void>

    @JsName("put")
    fun putAsync(
        request: URL,
        response: Response,
    ): Promise<Void>
}
