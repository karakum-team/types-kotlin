// Automatically generated - do not modify!

package web.html

import web.events.Event
import web.events.EventInstance
import web.events.EventTarget
import web.events.EventType
import web.gl.WebGLContextEvent

inline val <C : HTMLCanvasElement> C.contextLostEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("contextlost"))

inline val <C : HTMLCanvasElement> C.contextRestoredEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("contextrestored"))

inline val <C : HTMLCanvasElement> C.webglcontextcreationerrorEvent: EventInstance<WebGLContextEvent, C, EventTarget>
    get() = EventInstance(this, EventType("webglcontextcreationerror"))

inline val <C : HTMLCanvasElement> C.webglcontextlostEvent: EventInstance<WebGLContextEvent, C, EventTarget>
    get() = EventInstance(this, EventType("webglcontextlost"))

inline val <C : HTMLCanvasElement> C.webglcontextrestoredEvent: EventInstance<WebGLContextEvent, C, EventTarget>
    get() = EventInstance(this, EventType("webglcontextrestored"))
