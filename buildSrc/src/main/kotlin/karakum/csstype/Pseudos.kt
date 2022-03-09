package karakum.csstype

import karakum.common.unionConstant

internal fun convertMetaPseudos(
    name: String,
    source: String,
): ConversionResult {
    val builderName = "$name$RULE_BUILDER"
    val parentTypes = source
        .substringAfter(" = ")
        .substringBefore(";")
        .splitToSequence(" | ")
        .joinToString(",\n") { "$it$RULE_BUILDER<T>" }

    return ConversionResult(
        builderName,
        "interface $builderName<T: Any>:\n$parentTypes",
    )
}

internal fun convertPseudos(
    name: String,
    source: String,
): ConversionResult {
    val method: (String) -> String = when (name) {
        "SimplePseudos" -> { selector ->
            val kotlinName = unionConstant(selector).kotlinName
            """
            inline fun $kotlinName(
                block: T.() -> Unit,
            ) {
                "$selector"(block)
            }
            
            inline val $SELECTOR.$kotlinName
                get() = $SELECTOR("${'$'}{this}$selector")
            """.trimIndent()
        }

        "AdvancedPseudos" -> { selector ->
            val kotlinName = unionConstant(selector).kotlinName
            sequenceOf(SELECTOR, "String").joinToString("\n\n") { type ->
                """
                inline fun $kotlinName(
                    selector: $type,
                ): $SELECTOR =
                    $SELECTOR("$selector(${'$'}selector)")
                """.trimIndent()
            }
        }

        else -> TODO("Method for `$name`")
    }

    return convertPseudos(name, source, method)
}

private fun convertPseudos(
    name: String,
    source: String,
    method: (selector: String) -> String,
): ConversionResult {
    val builderName = "$name$RULE_BUILDER"
    val selectors = source.substringAfter("=\n")
        .substringBefore(";")
        .trimIndent()
        .trimMargin("| ")
        .split("\n")
        .toUnionValues()
        .distinctBy { it.removePrefix("::").removePrefix(":") }

    val methods = selectors.joinToString("\n\n", transform = method)

    val body = """
        interface $builderName<T: Any>: $RULE_BUILDER<T> {
            $methods
        }
    """.trimIndent()

    return ConversionResult(builderName, body)
}

