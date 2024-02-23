// Automatically generated - do not modify!

package web.midi

import web.events.Event
import web.events.EventHandler
import web.messaging.MessageEvent

/**
 * Available only in secure contexts.
 *
 * [MDN Reference](https://developer.mozilla.org/docs/Web/API/MIDIInput)
 */
sealed external class MIDIInput :
MIDIPort {
/**
 * [MDN Reference](https://developer.mozilla.org/docs/Web/API/MIDIInput/midimessage_event)
 */
var onmidimessage: EventHandler<MIDIMessageEvent>?
}
