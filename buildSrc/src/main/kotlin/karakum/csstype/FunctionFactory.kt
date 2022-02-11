package karakum.csstype

import karakum.common.kebabToCamel

internal fun factory(
    name: String,
    returnType: String,
    parameters: Array<out Pair<String, String>>,
    delimiter: String = ", ",
): String {
    return """
    inline fun ${name.kebabToCamel()}(
        ${parameters.joinToString("\n") { (n, v) -> "$n: $v," }}
    ): $returnType =
        "$name(${parameters.joinToString(delimiter) { (n) -> "$$n" }})".unsafeCast<$returnType>()
    """.trimIndent()
}
