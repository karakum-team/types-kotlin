package com.github.turansky.csstype

import com.github.turansky.common.enumConstant

internal fun convertPseudos(
    name: String,
    source: String,
): ConversionResult {
    val builderName = "${name}RuleBuilder"
    val selectors = source.substringAfter("=\n")
        .substringBefore(";")
        .trimIndent()
        .trimMargin("| ")
        .split("\n")
        .toUnionValues()
        .distinctBy { it.removePrefix("::").removePrefix(":") }

    val methods = selectors.joinToString("\n\n") { selector ->
        """
            inline fun ${enumConstant(selector, false)}(
                block: T.() -> Unit,
            ) {
                "$selector"(block)
            }
        """.trimIndent()
    }

    val body = """
        interface $builderName<T: Any>: $RULE_BUILDER<T> {
            $methods
        }
    """.trimIndent()

    return ConversionResult(builderName, body)
}
