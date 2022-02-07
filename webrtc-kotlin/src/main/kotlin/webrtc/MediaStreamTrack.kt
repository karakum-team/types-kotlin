// Automatically generated - do not modify!

package webrtc

sealed external interface MediaStreamTrack {
    //id: string;
    //kind: string;
    //label: string;
    var enabled: Boolean

    //muted: boolean;
    //remote: boolean;
    //readyState: MediaStreamTrackState;
    //onmute: EventListener;
    //onunmute: EventListener;
    //onended: EventListener;
    //onoverconstrained: EventListener;
    fun clone(): MediaStreamTrack
    fun stop()
    fun getCapabilities(): MediaTrackCapabilities
    fun getConstraints(): MediaTrackConstraints
    fun getSettings(): MediaTrackSettings
    fun applyConstraints(constraints: MediaTrackConstraints): kotlin.js.Promise<Unit>
}
