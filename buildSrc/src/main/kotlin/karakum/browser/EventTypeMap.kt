package karakum.browser

internal val EVENT_TYPE_MAP = mapOf(
    "Event" to "org.w3c.dom.events.Event",

    "AnimationEvent" to "org.w3c.dom.events.Event",

    // TODO: update
    "AnimationEvent" to "org.w3c.dom.events.Event",
    "ClipboardEvent" to "org.w3c.dom.clipboard.ClipboardEvent",
    "CompositionEvent" to "org.w3c.dom.events.CompositionEvent",
    "DragEvent" to "org.w3c.dom.DragEvent",
    "FocusEvent" to "org.w3c.dom.events.FocusEvent",
    "KeyboardEvent" to "org.w3c.dom.events.KeyboardEvent",
    "MouseEvent" to "org.w3c.dom.events.MouseEvent",
    "TouchEvent" to "org.w3c.dom.TouchEvent",
    "PointerEvent" to "org.w3c.dom.pointerevents.PointerEvent",

    // TODO: update
    "TransitionEvent" to "org.w3c.dom.events.Event",
    "UIEvent" to "org.w3c.dom.events.UIEvent",
    "WheelEvent" to "org.w3c.dom.events.WheelEvent",

    "ErrorEvent" to "org.w3c.dom.ErrorEvent",
    // AnimationPlaybackEvent
    "MessageEvent" to "org.w3c.dom.MessageEvent",
    "ProgressEvent" to "org.w3c.xhr.ProgressEvent",
    "InputEvent" to "org.w3c.dom.InputEvent",
    // FormDataEvent
    // SecurityPolicyViolationEvent
    // SubmitEvent
    "MediaEncryptedEvent" to "org.w3c.dom.encryptedmedia.MediaEncryptedEvent",
    // IDBVersionChangeEvent
    "MediaKeyMessageEvent" to "org.w3c.dom.encryptedmedia.MediaKeyMessageEvent",
    "MediaQueryListEvent" to "org.w3c.dom.MediaQueryListEvent",
    // BlobEvent
    // MediaRecorderErrorEvent
    "MediaStreamTrackEvent" to "org.w3c.dom.mediacapture.MediaStreamTrackEvent",
    // OfflineAudioCompletionEvent
    // RTCDTMFToneChangeEvent
    // RTCDataChannelEvent
    // RTCPeerConnectionIceEvent
    // RTCTrackEvent
    // AudioProcessingEvent
    // SpeechSynthesisEvent
    // SpeechSynthesisErrorEvent
    "TrackEvent" to "org.w3c.dom.TrackEvent",
    "CloseEvent" to "org.w3c.dom.CloseEvent",
    // DeviceMotionEvent
    // DeviceOrientationEvent
    // GamepadEvent
    "BeforeUnloadEvent" to "org.w3c.dom.BeforeUnloadEvent",
    "HashChangeEvent" to "org.w3c.dom.HashChangeEvent",
    "PageTransitionEvent" to "org.w3c.dom.PageTransitionEvent",
    "PopStateEvent" to "org.w3c.dom.PopStateEvent",
    "PromiseRejectionEvent" to "org.w3c.dom.PromiseRejectionEvent",
    "StorageEvent" to "org.w3c.dom.StorageEvent",
)
