package karakum.csstype

import karakum.common.kebabToCamel

typealias Parameters = Array<out Pair<String, String>>

internal fun factory(
    type: String,
    parameters: Parameters,
): String {
    return """
    inline fun $type(
        ${parameters.stringify()}
    ): $type =
        "${parameters.joinToString(" ") { (n) -> "$$n" }}".unsafeCast<$type>()
    """.trimIndent()
}

internal fun factory(
    name: String,
    returnType: String,
    parameters: Parameters,
): String {
    return """
    inline fun ${name.kebabToCamel()}(
        ${parameters.stringify()}
    ): $returnType =
        "${parameters.joinToString(" ") { (n) -> "$$n" }}".unsafeCast<$returnType>()
    """.trimIndent()
}

internal fun function(
    name: String,
    returnType: String,
    parameters: Parameters,
    delimiter: String = ", ",
): String {
    return """
    inline fun ${name.kebabToCamel()}(
        ${parameters.stringify()}
    ): $returnType =
        "$name(${parameters.joinToString(delimiter) { (n) -> "$$n" }})".unsafeCast<$returnType>()
    """.trimIndent()
}

private fun Parameters.stringify(): String =
    joinToString("\n") { (n, v) ->
        val vararg = if (n == "stops") "vararg" else ""
        "$vararg $n: $v,"
    }
