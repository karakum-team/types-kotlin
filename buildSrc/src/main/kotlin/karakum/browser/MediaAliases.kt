package karakum.browser

private val ALIAS_MAP = listOf(
    "MediaError" to "media",

    "MediaKeys" to "media.key",

    "TimeRanges" to "media.source",
)

internal fun mediaAliases(): List<ConversionResult> =
    ALIAS_MAP.map { (name, pkg) ->
        val alias = when (name) {
            "MediaKeys" -> "org.w3c.dom.encryptedmedia.$name"
            else -> "org.w3c.dom.$name"
        }

        ConversionResult(
            name = name,
            body = "typealias $name = $alias",
            pkg = pkg,
        )
    }
