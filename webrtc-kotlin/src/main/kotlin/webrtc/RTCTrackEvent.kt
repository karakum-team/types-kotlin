// Automatically generated - do not modify!

package webrtc

import kotlinext.js.ReadonlyArray

sealed external interface RTCTrackEvent {
    val receiver: RTCRtpReceiver
    val track: MediaStreamTrack
    val streams: ReadonlyArray<MediaStream>
    val transceiver: RTCRtpTransceiver
}
