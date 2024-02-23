// Automatically generated - do not modify!

@file:JsModule("@tanstack/query-core")

package tanstack.query.core

abstract external class Removable  {
open var gcTime: JsDuration
open fun  destroy()
open protected fun  scheduleGc()
open protected fun  updateGcTime(newGcTime: JsDuration? )
open protected fun  clearGcTimeout()
abstract protected fun  optionalRemove()
}