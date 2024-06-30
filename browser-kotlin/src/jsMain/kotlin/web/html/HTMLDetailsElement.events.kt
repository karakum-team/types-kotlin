// Automatically generated - do not modify!

package web.html

import web.events.EventInstance
import web.events.EventTarget
import web.events.EventType
import web.uievents.ToggleEvent

inline val <C : HTMLDetailsElement> C.toggleEvent: EventInstance<ToggleEvent, C, EventTarget>
    get() = EventInstance(this, EventType("toggle"))
