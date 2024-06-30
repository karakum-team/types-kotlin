// Automatically generated - do not modify!

package web.rtc

import web.events.Event
import web.events.EventInstance
import web.events.EventTarget
import web.events.EventType

inline val <C : RTCIceTransport> C.gatheringStateChangeEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("gatheringstatechange"))

inline val <C : RTCIceTransport> C.iceCandidateEvent: EventInstance<RTCPeerConnectionIceEvent, C, EventTarget>
    get() = EventInstance(this, EventType("icecandidate"))

inline val <C : RTCIceTransport> C.iceCandidateErrorEvent: EventInstance<RTCPeerConnectionIceErrorEvent, C, EventTarget>
    get() = EventInstance(this, EventType("icecandidateerror"))

inline val <C : RTCIceTransport> C.selectedCandidatePairChangeEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("selectedcandidatepairchange"))

inline val <C : RTCIceTransport> C.stateChangeEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("statechange"))
