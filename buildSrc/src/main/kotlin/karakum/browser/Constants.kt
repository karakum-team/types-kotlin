package karakum.browser

private val PKG_MAP = mapOf(
    "window" to "browser",
    "document" to "browser",

    "devicePixelRatio" to "web.device",
    "history" to "web.history",
    "location" to "web.location",
    "navigator" to "web.navigator",
    "screen" to "web.screen",
    "speechSynthesis" to "web.speech",
    "localStorage" to "web.storage",
    "sessionStorage" to "web.storage",
    "crypto" to "web.crypto",
)

internal fun browserConstants(
    content: String,
): Sequence<ConversionResult> =
    content
        .splitToSequence("\ndeclare var ")
        .drop(1)
        .map { it.substringBefore(";\n") }
        .map { it.removeSuffix(";") }
        .filter { "\n" !in it }
        .mapNotNull { convertConstant(it) }

private fun convertConstant(
    source: String,
): ConversionResult? {
    val (name, typeSource) = source
        .substringBefore(" & ")
        .split(": ")

    val pkg = PKG_MAP[name]
        ?: return null

    val type = when (typeSource) {
        "number" -> "Double"
        else -> typeSource
    }

    return ConversionResult(
        name = "$name.val",
        body = "external val $name: $type",
        pkg = pkg
    )
}
