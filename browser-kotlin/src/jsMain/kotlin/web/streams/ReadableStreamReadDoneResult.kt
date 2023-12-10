// Automatically generated - do not modify!

package web.streams

import seskar.js.JsTypeGuard

@JsTypeGuard(
    property = "done",
    value = "true",
)
sealed external interface ReadableStreamReadDoneResult<T> :
    ReadableStreamReadResult<T> {
    var value: T?
}
