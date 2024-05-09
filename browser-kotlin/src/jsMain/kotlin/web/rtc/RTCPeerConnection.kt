// Automatically generated - do not modify!

package web.rtc

import js.array.ReadonlyArray
import js.core.Void
import js.promise.Promise
import web.crypto.Algorithm
import web.events.Event
import web.events.EventHandler
import web.events.EventTarget
import web.media.streams.MediaStream
import web.media.streams.MediaStreamTrack

/**
 * A WebRTC connection between the local computer and a remote peer. It provides methods to connect to a remote peer, maintain and monitor the connection, and close the connection once it's no longer needed.
 *
 * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection)
 */
external class RTCPeerConnection(
    configuration: RTCConfiguration = definedExternally,
) : EventTarget {
    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection/canTrickleIceCandidates)
     */
    val canTrickleIceCandidates: Boolean?

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection/connectionState)
     */
    val connectionState: RTCPeerConnectionState

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection/currentLocalDescription)
     */
    val currentLocalDescription: RTCSessionDescription?

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection/currentRemoteDescription)
     */
    val currentRemoteDescription: RTCSessionDescription?

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection/iceConnectionState)
     */
    val iceConnectionState: RTCIceConnectionState

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection/iceGatheringState)
     */
    val iceGatheringState: RTCIceGatheringState

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection/localDescription)
     */
    val localDescription: RTCSessionDescription?

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection/connectionstatechange_event)
     */
    var onconnectionstatechange: EventHandler<Event, RTCPeerConnection>?

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection/datachannel_event)
     */
    var ondatachannel: EventHandler<RTCDataChannelEvent, RTCPeerConnection>?

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection/icecandidate_event)
     */
    var onicecandidate: EventHandler<RTCPeerConnectionIceEvent, RTCPeerConnection>?

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection/icecandidateerror_event)
     */
    var onicecandidateerror: EventHandler<RTCPeerConnectionIceErrorEvent, RTCPeerConnection>?

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection/iceconnectionstatechange_event)
     */
    var oniceconnectionstatechange: EventHandler<Event, RTCPeerConnection>?

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection/icegatheringstatechange_event)
     */
    var onicegatheringstatechange: EventHandler<Event, RTCPeerConnection>?

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection/negotiationneeded_event)
     */
    var onnegotiationneeded: EventHandler<Event, RTCPeerConnection>?

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection/signalingstatechange_event)
     */
    var onsignalingstatechange: EventHandler<Event, RTCPeerConnection>?

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection/track_event)
     */
    var ontrack: EventHandler<RTCTrackEvent, RTCPeerConnection>?

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection/pendingLocalDescription)
     */
    val pendingLocalDescription: RTCSessionDescription?

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection/pendingRemoteDescription)
     */
    val pendingRemoteDescription: RTCSessionDescription?

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection/remoteDescription)
     */
    val remoteDescription: RTCSessionDescription?

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection/sctp)
     */
    val sctp: RTCSctpTransport?

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection/signalingState)
     */
    val signalingState: RTCSignalingState

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection/addIceCandidate)
     */
    suspend fun addIceCandidate(candidate: RTCIceCandidateInit = definedExternally): Unit

    @JsName("addIceCandidate")
    fun addIceCandidateAsync(candidate: RTCIceCandidateInit = definedExternally): Promise<Void>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection/addTrack)
     */
    fun addTrack(
        track: MediaStreamTrack,
        vararg streams: MediaStream,
    ): RTCRtpSender

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection/addTransceiver)
     */
    fun addTransceiver(
        trackOrKind: MediaStreamTrack,
        init: RTCRtpTransceiverInit = definedExternally,
    ): RTCRtpTransceiver

    fun addTransceiver(
        trackOrKind: String,
        init: RTCRtpTransceiverInit = definedExternally,
    ): RTCRtpTransceiver

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection/close)
     */
    fun close()

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection/createAnswer)
     */
    suspend fun createAnswer(options: RTCAnswerOptions = definedExternally): RTCSessionDescriptionInit

    @JsName("createAnswer")
    fun createAnswerAsync(options: RTCAnswerOptions = definedExternally): Promise<RTCSessionDescriptionInit>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection/createDataChannel)
     */
    fun createDataChannel(
        label: String,
        dataChannelDict: RTCDataChannelInit = definedExternally,
    ): RTCDataChannel

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection/createOffer)
     */
    suspend fun createOffer(options: RTCOfferOptions = definedExternally): RTCSessionDescriptionInit

    @JsName("createOffer")
    fun createOfferAsync(options: RTCOfferOptions = definedExternally): Promise<RTCSessionDescriptionInit>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection/getConfiguration)
     */
    fun getConfiguration(): RTCConfiguration

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection/getReceivers)
     */
    fun getReceivers(): ReadonlyArray<RTCRtpReceiver>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection/getSenders)
     */
    fun getSenders(): ReadonlyArray<RTCRtpSender>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection/getStats)
     */
    suspend fun getStats(selector: MediaStreamTrack? = definedExternally): RTCStatsReport

    @JsName("getStats")
    fun getStatsAsync(selector: MediaStreamTrack? = definedExternally): Promise<RTCStatsReport>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection/getTransceivers)
     */
    fun getTransceivers(): ReadonlyArray<RTCRtpTransceiver>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection/removeTrack)
     */
    fun removeTrack(sender: RTCRtpSender)

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection/restartIce)
     */
    fun restartIce()

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection/setConfiguration)
     */
    fun setConfiguration(configuration: RTCConfiguration = definedExternally)

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection/setLocalDescription)
     */
    suspend fun setLocalDescription(description: RTCLocalSessionDescriptionInit = definedExternally): Unit

    @JsName("setLocalDescription")
    fun setLocalDescriptionAsync(description: RTCLocalSessionDescriptionInit = definedExternally): Promise<Void>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection/setRemoteDescription)
     */
    suspend fun setRemoteDescription(description: RTCSessionDescriptionInit): Unit

    @JsName("setRemoteDescription")
    fun setRemoteDescriptionAsync(description: RTCSessionDescriptionInit): Promise<Void>

    companion object {
        /**
         * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCPeerConnection/generateCertificate_static)
         */
        suspend fun generateCertificate(keygenAlgorithm: Algorithm): RTCCertificate

        @JsName("generateCertificate")
        fun generateCertificateAsync(keygenAlgorithm: Algorithm): Promise<RTCCertificate>
        suspend fun generateCertificate(keygenAlgorithm: String): RTCCertificate

        @JsName("generateCertificate")
        fun generateCertificateAsync(keygenAlgorithm: String): Promise<RTCCertificate>
    }
}
