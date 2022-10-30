package karakum.browser

private val ALIAS_MAP = listOf(
    "MediaError" to "media",

    "MediaDevices" to "media.devices",

    "MediaKeys" to "media.key",
    "MediaKeySystemAccess" to "media.key",
    "MediaKeySystemConfiguration" to "media.key",

    "TimeRanges" to "media.source",

    "Clipboard" to "web.clipboard",
    "ServiceWorkerContainer" to "serviceworkers",
)

internal fun mediaAliases(): List<ConversionResult> =
    ALIAS_MAP.map { (name, pkg) ->
        val alias = when (name) {
            "MediaDevices",
            -> "org.w3c.dom.mediacapture.$name"

            "MediaKeys",
            "MediaKeySystemAccess",
            "MediaKeySystemConfiguration",
            -> "org.w3c.dom.encryptedmedia.$name"

            "Clipboard",
            -> "org.w3c.dom.clipboard.$name"

            "ServiceWorkerContainer",
            -> "org.w3c.workers.$name"

            else -> "org.w3c.dom.$name"
        }

        ConversionResult(
            name = name,
            body = "typealias $name = $alias",
            pkg = pkg,
        )
    }
