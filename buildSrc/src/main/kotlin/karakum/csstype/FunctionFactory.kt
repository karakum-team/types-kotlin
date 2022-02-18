package karakum.csstype

import karakum.common.kebabToCamel

internal fun factory(
    type: String,
    parameters: Array<out Pair<String, String>>,
): String {
    return """
    inline fun ${type.kebabToCamel()}(
        ${parameters.joinToString("\n") { (n, v) -> "$n: $v," }}
    ): $type =
        "${parameters.joinToString(" ") { (n) -> "$$n" }}".unsafeCast<$type>()
    """.trimIndent()
}

internal fun factory(
    name: String,
    returnType: String,
    parameters: Array<out Pair<String, String>>,
): String {
    return """
    inline fun ${name.kebabToCamel()}(
        ${parameters.joinToString("\n") { (n, v) -> "$n: $v," }}
    ): $returnType =
        "${parameters.joinToString(" ") { (n) -> "$$n" }}".unsafeCast<$returnType>()
    """.trimIndent()
}

internal fun function(
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
