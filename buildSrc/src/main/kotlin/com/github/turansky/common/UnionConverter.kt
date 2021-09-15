package com.github.turansky.common

internal fun unionBody(
    name: String,
    values: List<String>,
): String {
    val constMap = values.associateBy { enumConstant(it) }

    val jsName = constMap.asSequence()
        .joinToString(
            separator = ", ",
            prefix = "@JsName(\"\"\"({",
            postfix = "})\"\"\")",
        ) { (key, value) ->
            "$key: '$value'"
        }

    val constantNames = constMap.keys
        .joinToString("") { "$it,\n" }

    return """
        @Suppress("NAME_CONTAINS_ILLEGAL_CHARS")
        // language=JavaScript
        $jsName
        external enum class $name {
            $constantNames
            ;
        }
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
