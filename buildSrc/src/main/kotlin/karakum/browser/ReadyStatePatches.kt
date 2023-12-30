package karakum.browser

private val CORRECTION_MAP = listOf(
    StateCorrection("CSSRule", null),
    StateCorrection("Range", null),

    StateCorrection("FileReader", "readyState"),
    StateCorrection("HTMLTrackElement", "readyState"),
    StateCorrection("WebSocket", "readyState"),
    StateCorrection("EventSource", "readyState"),
    StateCorrection("XMLHttpRequest", "readyState"),

    StateCorrection("GeolocationPositionError", "code"),
    StateCorrection("MediaError", "code"),
)

private val ALIAS_MAP = CORRECTION_MAP.asSequence()
    .mapNotNull { (className, propertyName) ->
        if (propertyName != null) {
            className to propertyName.replaceFirstChar(Char::uppercase)
        } else null
    }
    .toMap()

internal fun getAdditionalAliasName(
    name: String,
): String? =
    ALIAS_MAP[name]

private data class StateCorrection(
    val className: String,
    val propertyName: String?,
)

internal fun String.applyReadyStatePatches(): String =
    CORRECTION_MAP.fold(this) { acc, correction ->
        val className = correction.className

        sequenceOf(
            "\ninterface $className ",
            "\ndeclare var $className: ",
        )
            .map { prefix -> prefix + acc.substringAfter(prefix).substringBefore("\n}\n") }
            .fold(acc) { localAcc, before ->
                localAcc.replace(before, applyCorrection(before, correction))
            }
    }

private val CONSTANT_REGEX = Regex("""(\n\s+readonly [A-Z_]+: )\d+(;)""")

private fun applyCorrection(
    source: String,
    correction: StateCorrection,
): String {
    val propertyName = correction.propertyName
    return if (propertyName != null) {
        val aliasName = propertyName.replaceFirstChar(Char::uppercase)
        source.replace("readonly $propertyName: number;", "readonly $propertyName: $aliasName;")
            .replace(CONSTANT_REGEX, "$1$aliasName$2")
    } else {
        source.replace(CONSTANT_REGEX, "")
    }
}
