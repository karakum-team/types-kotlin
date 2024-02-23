// Automatically generated - do not modify!

@file:Suppress(
"NESTED_CLASS_IN_EXTERNAL_INTERFACE",
)

package web.media.streams

import seskar.js.JsValue
import seskar.js.JsVirtual
import web.media.streams.MediaStream
import web.media.streams.MediaStreamTrack

        @JsVirtual
        sealed external interface MediaStreamTrackState {
            companion object {
                @JsValue("ended")    
val ended: MediaStreamTrackState
@JsValue("live")    
val live: MediaStreamTrackState
            }
        }
