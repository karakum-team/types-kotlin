// Automatically generated - do not modify!

package web.media.recorder

import web.errors.ErrorEvent
import web.events.Event
import web.events.EventInstance
import web.events.EventTarget
import web.events.EventType

inline val <C : MediaRecorder> C.dataAvailableEvent: EventInstance<BlobEvent, C, EventTarget>
    get() = EventInstance(this, EventType("dataavailable"))

inline val <C : MediaRecorder> C.errorEvent: EventInstance<ErrorEvent, C, EventTarget>
    get() = EventInstance(this, EventType("error"))

inline val <C : MediaRecorder> C.pauseEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("pause"))

inline val <C : MediaRecorder> C.resumeEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("resume"))

inline val <C : MediaRecorder> C.startEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("start"))

inline val <C : MediaRecorder> C.stopEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("stop"))
