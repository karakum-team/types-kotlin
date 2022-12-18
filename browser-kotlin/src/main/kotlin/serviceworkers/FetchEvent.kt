// Automatically generated - do not modify!

package serviceworkers

import web.events.*
import web.http.Request
import web.http.Response
import kotlin.js.Promise

external interface FetchEventInit : ExtendableEventInit {
    var clientId: String?
    var handled: Promise<undefined>?
    var preloadResponse: Promise<*>?
    var replacesClientId: String?
    var request: Request
    var resultingClientId: String?
}

open external class FetchEvent(
    type: String,
    init: FetchEventInit,
) : ExtendableEvent {
    val clientId: String
    val handled: Promise<undefined>
    val preloadResponse: Promise<*>
    val request: Request
    val resultingClientId: String
    fun respondWith(r: Response)
    fun respondWith(r: PromiseLike<Response>)

    companion object
}
