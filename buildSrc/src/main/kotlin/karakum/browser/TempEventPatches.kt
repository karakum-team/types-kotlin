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
)

private data class EventCorrection(
    val name: String,
    val type: String,
)

internal fun String.applyTempEventPatches(): String =
    TYPE_MAP.fold(this) { acc, (name, type) ->
        val result = acc.replaceFirst(""""$name": Event;""", """"$name": $type;""")

        val handler = " on$name: "
        val before = handler + result.substringAfter(handler).substringBefore("\n")
        result.replaceFirst(before, before.replaceFirst(": Event)", ": $type)"))
    }
