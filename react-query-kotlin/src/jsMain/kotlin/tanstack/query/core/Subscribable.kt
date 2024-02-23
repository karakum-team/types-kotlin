// Automatically generated - do not modify!

@file:JsModule("@tanstack/query-core")

package tanstack.query.core

import js.collections.JsSet

open external class Subscribable <TListener: Function<*>>() {
open protected var listeners: JsSet<TListener>
open fun  subscribe(listener: TListener ): () -> Unit
open fun  hasListeners(): Boolean
open protected fun  onSubscribe()
open protected fun  onUnsubscribe()
}