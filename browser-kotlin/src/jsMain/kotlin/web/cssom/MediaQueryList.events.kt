// Automatically generated - do not modify!

package web.cssom

import web.events.EventInstance
import web.events.EventTarget
import web.events.EventType

inline val <C : MediaQueryList> C.changeEvent: EventInstance<MediaQueryListEvent, C, EventTarget>
    get() = EventInstance(this, EventType("change"))
