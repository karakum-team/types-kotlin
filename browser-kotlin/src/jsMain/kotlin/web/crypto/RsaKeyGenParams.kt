// Automatically generated - do not modify!

package web.crypto

import js.core.BigInt
import js.objects.JsPlainObject
import web.crypto.Algorithm

@JsPlainObject
sealed external interface RsaKeyGenParams :
Algorithm {
var modulusLength: Int
var publicExponent: BigInteger
}
