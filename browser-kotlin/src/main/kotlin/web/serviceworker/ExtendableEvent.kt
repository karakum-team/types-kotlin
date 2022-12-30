// Automatically generated - do not modify!

package web.serviceworker

import web.events.Event
import web.events.EventInit
import kotlin.js.Promise

external interface ExtendableEventInit : EventInit

open external class ExtendableEvent(
    type: String,
    init: ExtendableEventInit = definedExternally,
) : Event {
    fun waitUntil(f: Promise<*>)

    companion object
}
