// Automatically generated - do not modify!

@file:JsModule("react-query")
@file:JsNonModule

package tanstack.query.core

open external class Removable {
    open var cacheTime: JsDuration
    open fun destroy()
    protected open fun scheduleGc()
    protected open fun updateCacheTime(newCacheTime: JsDuration?)
    protected open fun clearGcTimeout()
    protected open fun abstract optionalRemove()
}
