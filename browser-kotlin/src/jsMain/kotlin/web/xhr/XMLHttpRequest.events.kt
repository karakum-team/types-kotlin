// Automatically generated - do not modify!

package web.xhr

import web.events.*

inline val <C : XMLHttpRequest> C.abortEvent: EventInstance<ProgressEvent, C, EventTarget>
    get() = EventInstance(this, EventType("abort"))

inline val <C : XMLHttpRequest> C.errorEvent: EventInstance<ProgressEvent, C, EventTarget>
    get() = EventInstance(this, EventType("error"))

inline val <C : XMLHttpRequest> C.loadEvent: EventInstance<ProgressEvent, C, EventTarget>
    get() = EventInstance(this, EventType("load"))

inline val <C : XMLHttpRequest> C.loadEndEvent: EventInstance<ProgressEvent, C, EventTarget>
    get() = EventInstance(this, EventType("loadend"))

inline val <C : XMLHttpRequest> C.loadStartEvent: EventInstance<ProgressEvent, C, EventTarget>
    get() = EventInstance(this, EventType("loadstart"))

inline val <C : XMLHttpRequest> C.progressEvent: EventInstance<ProgressEvent, C, EventTarget>
    get() = EventInstance(this, EventType("progress"))

inline val <C : XMLHttpRequest> C.readyStateChangeEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("readystatechange"))

inline val <C : XMLHttpRequest> C.timeoutEvent: EventInstance<ProgressEvent, C, EventTarget>
    get() = EventInstance(this, EventType("timeout"))
