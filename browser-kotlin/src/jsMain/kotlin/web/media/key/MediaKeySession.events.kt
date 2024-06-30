// Automatically generated - do not modify!

package web.media.key

import web.events.Event
import web.events.EventInstance
import web.events.EventTarget
import web.events.EventType

inline val <C : MediaKeySession> C.keyStatusesChangeEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("keystatuseschange"))

inline val <C : MediaKeySession> C.messageEvent: EventInstance<MediaKeyMessageEvent, C, EventTarget>
    get() = EventInstance(this, EventType("message"))
