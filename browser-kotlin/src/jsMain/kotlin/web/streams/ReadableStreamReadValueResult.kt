// Automatically generated - do not modify!

package web.streams

import seskar.js.JsTypeGuard

@JsTypeGuard(
    property = "done",
    value = "false",
)
sealed external interface ReadableStreamReadValueResult<T> :
    ReadableStreamReadResult<T> {
    var value: T
}
