package karakum.csstype

import karakum.common.kebabToCamel

internal fun factory(
    name: String,
    returnType: String,
    vararg parameters: Pair<String, String>,
): String {
    return """
    inline fun ${name.kebabToCamel()}(
        ${parameters.joinToString("\n") { (n, v) -> "$n: $v," }}
    ): $returnType =
        "$name(${parameters.joinToString(", ") { (n) -> "$$n" }})".unsafeCast<$returnType>()
    """.trimIndent()
}
