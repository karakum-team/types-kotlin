// Automatically generated - do not modify!

package web.crypto

import js.buffer.BufferSource
import js.objects.JsPlainObject
import web.crypto.Algorithm

@JsPlainObject
sealed external interface AesCtrParams :
Algorithm {
var counter: BufferSource
var length: Short /* unsigned byte */
}
