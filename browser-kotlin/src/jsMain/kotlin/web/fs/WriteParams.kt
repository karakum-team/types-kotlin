// Automatically generated - do not modify!

package web.fs

import js.buffer.BufferSource
import js.core.JsLong
import js.objects.JsPlainObject
import web.blob.Blob

@JsPlainObject
sealed external interface WriteParams {
var data: Any /* BufferSource | Blob | string */?
var position: JsLong?
var size: JsLong?
var type: WriteCommandType
}
