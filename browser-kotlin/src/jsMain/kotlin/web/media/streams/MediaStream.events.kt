// Automatically generated - do not modify!

package web.media.streams

import web.events.EventInstance
import web.events.EventTarget
import web.events.EventType

inline val <C : MediaStream> C.addTrackEvent: EventInstance<MediaStreamTrackEvent, C, EventTarget>
    get() = EventInstance(this, EventType("addtrack"))

inline val <C : MediaStream> C.removeTrackEvent: EventInstance<MediaStreamTrackEvent, C, EventTarget>
    get() = EventInstance(this, EventType("removetrack"))
