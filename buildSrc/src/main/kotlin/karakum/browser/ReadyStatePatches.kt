package karakum.browser

internal const val EVENT_PHASE = "EventPhase"
internal const val NODE_TYPE = "NodeType"

private val CORRECTION_MAP = listOf(
    StateCorrection("CSSRule", null),
    StateCorrection("Range", null),

    StateCorrection("FileReader", "readyState"),
    StateCorrection("HTMLTrackElement", "readyState"),
    StateCorrection("HTMLMediaElement", "networkState", constantPrefix = "NETWORK_"),
    StateCorrection("HTMLMediaElement", "readyState", constantPrefix = "HAVE_"),
    StateCorrection("WebSocket", "readyState"),
    StateCorrection("EventSource", "readyState"),
    StateCorrection("XMLHttpRequest", "readyState"),

    StateCorrection("GeolocationPositionError", "code"),
    StateCorrection("MediaError", "code"),

    StateCorrection("Event", "eventPhase", existedAliasName = EVENT_PHASE),
    StateCorrection("Node", "nodeType", existedAliasName = NODE_TYPE),
)

private val ALIAS_NAME_MAP = CORRECTION_MAP.asSequence()
    .mapNotNull { correction ->
        val (_, propertyName, existedAliasName) = correction
        if (propertyName != null && existedAliasName == null) {
            val aliasName = propertyName.replaceFirstChar(Char::uppercase)

            correction to aliasName
        } else null
    }
    .toMap()

private val ALIASES_MAP = ALIAS_NAME_MAP.entries.groupBy(
    keySelector = { it.key.className },
    valueTransform = { it.value },
)

internal fun getAdditionalAliasNames(
    name: String,
): List<String>? =
    ALIASES_MAP[name]

private data class StateCorrection(
    val className: String,
    val propertyName: String?,
    val existedAliasName: String? = null,
    val constantPrefix: String = "",
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

private fun constantRegex(
    prefix: String = "",
): Regex =
    Regex("""(\n\s+readonly $prefix[A-Z_]+: )[\dx]+(;)""")

private fun applyCorrection(
    source: String,
    correction: StateCorrection,
): String {
    val propertyName = correction.propertyName
    return if (propertyName != null) {
        val aliasName = correction.existedAliasName
            ?: ALIAS_NAME_MAP.getValue(correction)

        source.replace("readonly $propertyName: number;", "readonly $propertyName: $aliasName;")
            .replace(constantRegex(correction.constantPrefix), "$1$aliasName$2")
    } else {
        source.replace(constantRegex(), "")
    }
}
