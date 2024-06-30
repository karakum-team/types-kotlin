// Automatically generated - do not modify!

package web.media.streams

import web.events.Event
import web.events.EventInstance
import web.events.EventTarget
import web.events.EventType

inline val <C : MediaStreamTrack> C.endedEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("ended"))

inline val <C : MediaStreamTrack> C.muteEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("mute"))

inline val <C : MediaStreamTrack> C.unmuteEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("unmute"))
