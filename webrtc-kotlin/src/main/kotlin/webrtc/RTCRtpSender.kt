// Automatically generated - do not modify!

package webrtc

sealed external interface RTCRtpSender {
    //readonly track?: MediaStreamTrack;
    //readonly transport?: RTCDtlsTransport;
    //readonly rtcpTransport?: RTCDtlsTransport;
    fun setParameters(parameters: RTCRtpParameters = definedExternally): kotlin.js.Promise<Unit>
    fun getParameters(): RTCRtpParameters
    fun replaceTrack(withTrack: MediaStreamTrack): kotlin.js.Promise<Unit>
}
