package karakum.webrtc

internal const val RTC_PEER_CONNECTION = """
interface RTCPeerConnectionErrorCallback {
    (error: DOMException): void;
}

interface RTCStatsReport {
    forEach(callbackfn: (value: any, key: string, parent: RTCStatsReport) => void, thisArg?: any): void;
}

declare var RTCStatsReport: {
    prototype: RTCStatsReport;
    new(): RTCStatsReport;
};

"""
