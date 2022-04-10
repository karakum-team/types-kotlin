package karakum.webrtc

internal const val RTC_ERROR_DETAIL_TYPE = """
type RTCErrorDetailType =
    | 'data-channel-failure'
    | 'dtls-failure'
    | 'fingerprint-failure'
    | 'hardware-encoder-error'
    | 'hardware-encoder-not-available'
    | 'sctp-failure'
    | 'sdp-syntax-error';
"""
