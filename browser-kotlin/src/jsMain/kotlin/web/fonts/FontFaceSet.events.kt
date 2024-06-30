// Automatically generated - do not modify!

package web.fonts

import web.events.EventInstance
import web.events.EventTarget
import web.events.EventType

inline val <C : FontFaceSet> C.loadingEvent: EventInstance<FontFaceSetLoadEvent, C, EventTarget>
    get() = EventInstance(this, EventType("loading"))

inline val <C : FontFaceSet> C.loadingDoneEvent: EventInstance<FontFaceSetLoadEvent, C, EventTarget>
    get() = EventInstance(this, EventType("loadingdone"))

inline val <C : FontFaceSet> C.loadingErrorEvent: EventInstance<FontFaceSetLoadEvent, C, EventTarget>
    get() = EventInstance(this, EventType("loadingerror"))
