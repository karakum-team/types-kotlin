// Automatically generated - do not modify!

package web.midi

import seskar.js.JsAlias
import seskar.js.JsAlias.Companion.THIS
import seskar.js.JsValue
import web.events.Event
import web.events.EventType
import kotlin.js.definedExternally

/**
 * Available only in secure contexts.
 *
 * [MDN Reference](https://developer.mozilla.org/docs/Web/API/MIDIConnectionEvent)
 */
open external class MIDIConnectionEvent(
    override val type: EventType<MIDIConnectionEvent>,
    init: MIDIConnectionEventInit = definedExternally,
) : Event {
    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/MIDIConnectionEvent/port)
     */
    val port: MIDIPort?

    @JsAlias(THIS)
    override fun asInit(): MIDIConnectionEventInit

    companion object {
        @JsValue("statechange")
        val STATE_CHANGE: EventType<MIDIConnectionEvent>
    }
}
