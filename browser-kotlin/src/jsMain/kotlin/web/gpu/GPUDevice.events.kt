// Automatically generated - do not modify!

package web.gpu

import web.events.EventInstance
import web.events.EventTarget
import web.events.EventType

inline val <C : GPUDevice> C.uncapturedErrorEvent: EventInstance<GPUUncapturedErrorEvent, C, EventTarget>
    get() = EventInstance(this, EventType("uncapturederror"))
