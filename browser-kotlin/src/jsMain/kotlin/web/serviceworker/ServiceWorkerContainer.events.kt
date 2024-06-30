// Automatically generated - do not modify!

package web.serviceworker

import web.events.Event
import web.events.EventInstance

inline val <C : ServiceWorkerContainer> C.controllerChangeEvent: EventInstance<Event, C, C>
    get() = EventInstance(this, Event.controllerChange())

inline val <C : ServiceWorkerContainer> C.messageEvent: EventInstance<Event, C, C>
    get() = EventInstance(this, Event.message())

inline val <C : ServiceWorkerContainer> C.messageErrorEvent: EventInstance<Event, C, C>
    get() = EventInstance(this, Event.messageError())
