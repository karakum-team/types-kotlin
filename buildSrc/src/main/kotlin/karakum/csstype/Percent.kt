package karakum.csstype

internal const val PERCENT = "Percent"

internal fun Percent(): ConversionResult {
    val declarations = sequenceOf(
        """
            sealed external interface $PERCENT:
                $LENGTH
        """.trimIndent(),

        unitsExtension(PERCENT, "pct", "%"),
    )

    return ConversionResult(PERCENT, declarations.joinToString("\n\n"))
}
