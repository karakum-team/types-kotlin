// Automatically generated - do not modify!

package web.vtt

import web.events.Event
import web.events.EventInstance
import web.events.EventTarget
import web.events.EventType

inline val <C : TextTrackCue> C.enterEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("enter"))

inline val <C : TextTrackCue> C.exitEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("exit"))
