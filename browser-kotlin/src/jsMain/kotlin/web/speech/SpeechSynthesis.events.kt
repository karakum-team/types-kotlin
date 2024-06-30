// Automatically generated - do not modify!

package web.speech

import web.events.Event
import web.events.EventInstance
import web.events.EventTarget
import web.events.EventType

inline val <C : SpeechSynthesis> C.voicesChangedEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("voiceschanged"))
