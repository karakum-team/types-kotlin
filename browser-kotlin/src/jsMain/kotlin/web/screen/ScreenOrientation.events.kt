// Automatically generated - do not modify!

package web.screen

import web.events.Event
import web.events.EventInstance
import web.events.EventTarget
import web.events.EventType

inline val <C : ScreenOrientation> C.changeEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("change"))
