package com.github.turansky.common

internal fun unionBody(
    name: String,
    values: List<String>,
): String {
    val constMap = values.associateBy { enumConstant(it) }

    val constantNames = constMap.keys
        .joinToString("") { "$it,\n" }

    return jsName(constMap) + """
        external enum class $name {
            $constantNames
            ;
        }
    """.trimIndent()
}

private fun jsName(
    constMap: Map<String, String>,
): String {
    val name = constMap.asSequence()
        .joinToString(
            separator = ", ",
            prefix = "@JsName(\"\"\"({",
            postfix = "})\"\"\")",
        ) { (key, value) ->
            "$key: '$value'"
        }

    return """
        @Suppress("NAME_CONTAINS_ILLEGAL_CHARS")
        // language=JavaScript
        $name
    """.trimIndent()
}

private fun enumConstant(
    value: String,
): String =
    when (value) {
        "" -> "none"
        "1" -> "D"
        "false", "true" -> "__${value}__"
        "super" -> "sup"
        else -> value.kebabToCamel()
    }
