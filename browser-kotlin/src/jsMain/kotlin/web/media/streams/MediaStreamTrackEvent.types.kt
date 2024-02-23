// Automatically generated - do not modify!

@file:Suppress(
"NON_ABSTRACT_MEMBER_OF_EXTERNAL_INTERFACE",
)

package web.media.streams

import seskar.js.JsValue
import web.events.Event
import web.events.EventType
import web.media.streams.MediaStream
import web.media.streams.MediaStreamTrack

    sealed external interface MediaStreamTrackEventTypes {
        @JsValue("addtrack")
val ADD_TRACK : EventType<MediaStreamTrackEvent>
    get() = definedExternally

@JsValue("removetrack")
val REMOVE_TRACK : EventType<MediaStreamTrackEvent>
    get() = definedExternally
    }
