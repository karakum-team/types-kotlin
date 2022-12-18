// Automatically generated - do not modify!

package serviceworkers

import web.events.*
import kotlin.js.Promise

external interface ExtendableEventInit : EventInit

open external class ExtendableEvent(
    type: String,
    init: ExtendableEventInit = definedExternally,
) : Event {
    fun waitUntil(f: Promise<any>)

    companion object
}
