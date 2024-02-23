// Automatically generated - do not modify!

package web.crypto

import js.buffer.BufferSource
import js.objects.JsPlainObject
import web.crypto.Algorithm

@JsPlainObject
sealed external interface Pbkdf2Params :
Algorithm {
var hash: HashAlgorithmIdentifier
var iterations: Int
var salt: BufferSource
}
