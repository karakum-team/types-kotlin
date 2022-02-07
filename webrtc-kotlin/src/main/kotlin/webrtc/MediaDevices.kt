// Automatically generated - do not modify!

package webrtc

import kotlinext.js.ReadonlyArray

sealed external interface MediaDevices {
    fun getSupportedConstraints(): MediaTrackSupportedConstraints
    fun getUserMedia(constraints: MediaStreamConstraints): kotlin.js.Promise<MediaStream>
    fun enumerateDevices(): kotlin.js.Promise<ReadonlyArray<MediaDeviceInfo>>
}
