// Automatically generated - do not modify!

package web.speech

import web.events.EventInstance
import web.events.EventTarget
import web.events.EventType

inline val <C : SpeechSynthesisUtterance> C.boundaryEvent: EventInstance<SpeechSynthesisEvent, C, EventTarget>
    get() = EventInstance(this, EventType("boundary"))

inline val <C : SpeechSynthesisUtterance> C.endEvent: EventInstance<SpeechSynthesisEvent, C, EventTarget>
    get() = EventInstance(this, EventType("end"))

inline val <C : SpeechSynthesisUtterance> C.errorEvent: EventInstance<SpeechSynthesisErrorEvent, C, EventTarget>
    get() = EventInstance(this, EventType("error"))

inline val <C : SpeechSynthesisUtterance> C.markEvent: EventInstance<SpeechSynthesisEvent, C, EventTarget>
    get() = EventInstance(this, EventType("mark"))

inline val <C : SpeechSynthesisUtterance> C.pauseEvent: EventInstance<SpeechSynthesisEvent, C, EventTarget>
    get() = EventInstance(this, EventType("pause"))

inline val <C : SpeechSynthesisUtterance> C.resumeEvent: EventInstance<SpeechSynthesisEvent, C, EventTarget>
    get() = EventInstance(this, EventType("resume"))

inline val <C : SpeechSynthesisUtterance> C.startEvent: EventInstance<SpeechSynthesisEvent, C, EventTarget>
    get() = EventInstance(this, EventType("start"))
