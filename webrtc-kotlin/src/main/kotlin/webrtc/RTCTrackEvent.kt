// Automatically generated - do not modify!

package webrtc

import kotlinext.js.ReadonlyArray

external class RTCTrackEvent : org.w3c.dom.events.Event {
    val receiver: RTCRtpReceiver
    val track: MediaStreamTrack
    val streams: ReadonlyArray<MediaStream>
    val transceiver: RTCRtpTransceiver
}
