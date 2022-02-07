// Automatically generated - do not modify!

package webrtc

import kotlinext.js.ReadonlyArray

external class RTCPeerConnection : org.w3c.dom.events.EventTarget {
    fun createOffer(options: RTCOfferOptions = definedExternally): kotlin.js.Promise<RTCSessionDescriptionInit>
    fun createAnswer(options: RTCAnswerOptions = definedExternally): kotlin.js.Promise<RTCSessionDescriptionInit>
    fun setLocalDescription(description: RTCSessionDescriptionInit): kotlin.js.Promise<Unit>
    val localDescription: RTCSessionDescription?
    val currentLocalDescription: RTCSessionDescription?
    val pendingLocalDescription: RTCSessionDescription?
    fun setRemoteDescription(description: RTCSessionDescriptionInit): kotlin.js.Promise<Unit>
    val remoteDescription: RTCSessionDescription?
    val currentRemoteDescription: RTCSessionDescription?
    val pendingRemoteDescription: RTCSessionDescription?
    fun addIceCandidate(candidate: dynamic /* RTCIceCandidateInit | RTCIceCandidate */ = definedExternally): kotlin.js.Promise<Unit>
    val signalingState: RTCSignalingState
    val connectionState: RTCPeerConnectionState
    fun getConfiguration(): RTCConfiguration
    fun setConfiguration(configuration: RTCConfiguration)
    fun close()
    var onicecandidateerror: PeerConnectionEventHandler<org.w3c.dom.events.Event>
    var onconnectionstatechange: PeerConnectionEventHandler<org.w3c.dom.events.Event>

    // Extension: https://www.w3.org/TR/webrtc/#h-rtcpeerconnection-interface-extensions
    fun getSenders(): ReadonlyArray<RTCRtpSender>
    fun getReceivers(): ReadonlyArray<RTCRtpReceiver>
    fun getTransceivers(): ReadonlyArray<RTCRtpTransceiver>
    fun addTrack(
        track: org.w3c.dom.mediacapture.MediaStreamTrack,
        vararg streams: ReadonlyArray<org.w3c.dom.mediacapture.MediaStream>,
    ): RTCRtpSender

    fun removeTrack(sender: RTCRtpSender)
    fun addTransceiver(
        trackOrKind: dynamic, /* MediaStreamTrack | string */
        init: RTCRtpTransceiverInit = definedExternally,
    ): RTCRtpTransceiver

    var ontrack: PeerConnectionEventHandler<RTCTrackEvent>

    // Extension: https://www.w3.org/TR/webrtc/#h-rtcpeerconnection-interface-extensions-1
    val sctp: RTCSctpTransport?
    fun createDataChannel(
        label: String?,
        dataChannelDict: RTCDataChannelInit = definedExternally,
    ): RTCDataChannel

    var ondatachannel: PeerConnectionEventHandler<RTCDataChannelEvent>

    // Extension: https://www.w3.org/TR/webrtc/#h-rtcpeerconnection-interface-extensions-2
    fun getStats(selector: org.w3c.dom.mediacapture.MediaStreamTrack? = definedExternally): kotlin.js.Promise<RTCStatsReport>

    // Extension: https://www.w3.org/TR/webrtc/#legacy-interface-extensions
    // Deprecated!
    fun createOffer(
        successCallback: RTCSessionDescriptionCallback,
        failureCallback: RTCPeerConnectionErrorCallback,
        options: RTCOfferOptions = definedExternally,
    ): kotlin.js.Promise<Unit>

    fun setLocalDescription(
        description: RTCSessionDescriptionInit,
        successCallback: () -> Unit,
        failureCallback: RTCPeerConnectionErrorCallback,
    ): kotlin.js.Promise<Unit>

    fun createAnswer(
        successCallback: RTCSessionDescriptionCallback,
        failureCallback: RTCPeerConnectionErrorCallback,
    ): kotlin.js.Promise<Unit>

    fun setRemoteDescription(
        description: RTCSessionDescriptionInit,
        successCallback: () -> Unit,
        failureCallback: RTCPeerConnectionErrorCallback,
    ): kotlin.js.Promise<Unit>

    fun addIceCandidate(
        candidate: dynamic, /* RTCIceCandidateInit | RTCIceCandidate */
        successCallback: () -> Unit,
        failureCallback: RTCPeerConnectionErrorCallback,
    ): kotlin.js.Promise<Unit>

    fun getStats(
        selector: org.w3c.dom.mediacapture.MediaStreamTrack?,
        successCallback: (report: RTCStatsReport) -> Unit,
        failureCallback: RTCPeerConnectionErrorCallback,
    ): kotlin.js.Promise<Unit>
}
