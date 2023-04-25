package karakum.csstype

import karakum.common.kebabToCamel

typealias Parameters = Array<out Pair<String, String>>

internal fun factory(
    type: String,
    parameters: Parameters,
): String {
    val dataString = parameters
        .flatMap { (n) -> if (n == "lineHeight") sequenceOf("/", "$$n") else sequenceOf(" ", "$$n") }
        .drop(1)
        .joinToString("")

    return """
    inline fun $type(
        ${parameters.stringify()}
    ): $type =
        "$dataString".unsafeCast<$type>()
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
    val typeParameters = when (returnType) {
        "T", "T?" -> "<T: Any>"
        else -> ""
    }

    var functionName = name.kebabToCamel()
    if (functionName == "var")
        functionName = "`$functionName`"

    var params = parameters.stringify()
    if (params.isNotEmpty())
        params = "\n$params\n"

    val cssParams = parameters
        .flatMap { (n) -> sequenceOf(prefix(name, n), "$$n") }
        .filterNotNull()
        .joinToString(delimiter)

    return """
    inline fun $typeParameters $functionName($params): $returnType =
        "$name($cssParams)".unsafeCast<$returnType>()
    """.trimIndent()
}

private fun prefix(
    name: String,
    parameterName: String,
): String? {
    if (name == "circle" || name == "ellipse") {
        if (parameterName == "c" || parameterName == "cx") {
            return "at"
        }
    }

    if (parameterName == "alpha")
        return "/"

    return null
}

private fun Parameters.stringify(): String =
    joinToString("\n") { (n, v) ->
        val vararg = if (n == "stops" || n == "values") "vararg" else ""
        "$vararg $n: $v,"
    }
