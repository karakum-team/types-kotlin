// Automatically generated - do not modify!

package web.abort

import web.abort.AbortSignal
import web.abort.Abortable

@JsExternalInheritorsOnly
external interface Abortable {
    /**
     * When provided the corresponding `AbortController` can be used to cancel an asynchronous action.
     */
    var signal: AbortSignal?
}
