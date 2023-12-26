package karakum.browser

private val TYPE_MAP = mapOf(
    "processorerror" to "ErrorEvent",

    "loading" to "FontFaceSetLoadEvent",
    "loadingdone" to "FontFaceSetLoadEvent",
    "loadingerror" to "FontFaceSetLoadEvent",

    "enterpictureinpicture" to "PictureInPictureEvent",
    "leavepictureinpicture" to "PictureInPictureEvent",

    "paymentmethodchange" to "PaymentMethodChangeEvent",

    "icecandidateerror" to "RTCPeerConnectionIceErrorEvent",
)

internal fun String.applyTempEventPatches(): String =
    TYPE_MAP.entries.fold(this) { acc, (name, type) ->
        val result = acc.replaceFirst(""""$name": Event;""", """"$name": $type;""")

        val handler = " on$name: "
        val before = handler + result.substringAfter(handler).substringBefore("\n")
        result.replaceFirst(before, before.replaceFirst(": Event)", ": $type)"))
    }
