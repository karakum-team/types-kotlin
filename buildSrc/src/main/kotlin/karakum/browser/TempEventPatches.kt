package karakum.browser

private val TYPE_MAP = listOf(
    EventCorrection("processorerror", "ErrorEvent"),

    EventCorrection("loading", "FontFaceSetLoadEvent"),
    EventCorrection("loadingdone", "FontFaceSetLoadEvent"),
    EventCorrection("loadingerror", "FontFaceSetLoadEvent"),

    EventCorrection("enterpictureinpicture", "PictureInPictureEvent"),
    EventCorrection("leavepictureinpicture", "PictureInPictureEvent"),

    EventCorrection("paymentmethodchange", "PaymentMethodChangeEvent"),

    EventCorrection("icecandidateerror", "RTCPeerConnectionIceErrorEvent"),

    EventCorrection("rtctransform", "RTCTransformEvent"),

    EventCorrection("remove", "AnimationPlaybackEvent", "Animation"),
    EventCorrection("statechange", "MIDIConnectionEvent", "MIDIAccess"),
    EventCorrection("error", "ErrorEvent", "MediaRecorder"),
    EventCorrection("error", "RTCErrorEvent", "RTCDataChannel"),
    EventCorrection("error", "RTCErrorEvent", "RTCDtlsTransport"),
)

private data class EventCorrection(
    val name: String,
    val type: String,
    val className: String? = null,
)

internal fun String.applyTempEventPatches(): String =
    TYPE_MAP.fold(this) { acc, correction ->
        val className = correction.className
        if (className != null) {
            sequenceOf("", "EventMap")
                .map { "\ninterface ${className}${it} " }
                .map { prefix -> prefix + acc.substringAfter(prefix).substringBefore("\n}\n") }
                .fold(acc) { localAcc, before ->
                    localAcc.replace(before, applyCorrection(before, correction))
                }
        } else {
            applyCorrection(acc, correction)
        }
    }

private fun applyCorrection(
    source: String,
    correction: EventCorrection,
): String {
    val (name, type) = correction
    val result = source.replaceFirst(""""$name": Event;""", """"$name": $type;""")

    val handler = " on$name: "
    val before = handler + result.substringAfter(handler).substringBefore("\n")
    return result.replaceFirst(before, before.replaceFirst(": Event)", ": $type)"))
}
