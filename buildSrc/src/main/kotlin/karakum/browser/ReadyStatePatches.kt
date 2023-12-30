package karakum.browser

private val CORRECTION_MAP = listOf(
    StateCorrection("CSSRule", null),
    StateCorrection("Range", null),
)

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

private fun applyCorrection(
    source: String,
    correction: StateCorrection,
): String {
    return if (correction.propertyName != null) {
        source
    } else {
        source.replace(Regex("""\n\s+readonly [A-Z_]+: \d+;"""), "")
    }
}
