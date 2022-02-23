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
    val source = if (parameters.size > 1) {
        """"${parameters.joinToString(" ") { (n) -> "$$n" }}""""
    } else {
        parameters.single().first
    }

    return """
    inline fun ${name.kebabToCamel()}(
        ${parameters.stringify()}
    ): $returnType =
        $source.unsafeCast<$returnType>()
    """.trimIndent()
}

internal fun function(
    name: String,
    returnType: String,
    parameters: Parameters,
    delimiter: String = ",",
): String {
    val typeParameters = if (returnType == "T") "<T: Any>" else ""

    return """
    inline fun $typeParameters ${name.kebabToCamel()}(
        ${parameters.stringify()}
    ): $returnType =
        "$name(${parameters.joinToString(delimiter) { (n) -> "$$n" }})".unsafeCast<$returnType>()
    """.trimIndent()
}

private fun Parameters.stringify(): String =
    joinToString("\n") { (n, v) ->
        val vararg = if (n == "stops" || n == "values") "vararg" else ""
        "$vararg $n: $v,"
    }
