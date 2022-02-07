// Automatically generated - do not modify!

package webrtc

import kotlinext.js.ReadonlyArray

sealed external interface RTCRtpTransceiverInit {
    var direction: RTCRtpTransceiverDirection? // default = 'sendrecv'
    var streams: ReadonlyArray<MediaStream>?
    var sendEncodings: ReadonlyArray<RTCRtpEncodingParameters>?
}
