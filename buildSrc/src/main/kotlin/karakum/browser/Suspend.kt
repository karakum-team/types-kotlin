package karakum.browser

private val ASYNC_FUNCTION_REGEX = Regex(
    """^((operator)?\s*)(fun.*[ >])([a-zA-Z\d]+)(\(.*\)): Promise<(.+)>( = definedExternally)?$""",
    RegexOption.DOT_MATCHES_ALL
)

private const val DELIMITER = "<!----!>"

internal fun withSuspendAdapter(
    source: String,
): Sequence<String> =
    source.replace(
        ASYNC_FUNCTION_REGEX,
        transform = { mr ->
            val p3 = mr.groupValues[3]
            val p4 = mr.groupValues[4]
            val p5 = mr.groupValues[5]
            val p6 = mr.groupValues[6]
            val p7 = mr.groupValues[7]

            val ret = when (p6) {
                "*" -> ": Any?"
                "Void" -> if (p7.isNotEmpty()) ": Unit" else ""
                else -> ": $p6"
            }
            """
            suspend $p3$p4$p5$ret$p7
            $DELIMITER    
            @JsName("$p4")
            $p3${p4}Async$p5: Promise<$p6>$p7
            """.trimIndent()
        }
    ).splitToSequence(DELIMITER)
        .map { it.trim() }
