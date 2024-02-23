// Automatically generated - do not modify!

package web.locks

import js.array.ReadonlyArray
import js.objects.JsPlainObject
import web.locks.LockManager

@JsPlainObject
sealed external interface LockManagerSnapshot {
var held: ReadonlyArray<LockInfo>?
var pending: ReadonlyArray<LockInfo>?
}
