package com.github.turansky.common

internal fun unionBody(
    name: String,
    values: List<String>,
): String {
    val constMap = values.associateBy { enumConstant(it, true) }

    val constantNames = constMap.keys
        .joinToString("") { "$it,\n" }

    return jsName(constMap) + """
        external enum class $name {
            $constantNames
            ;
        }
    """.trimIndent()
}

internal fun sealedUnionBody(
    name: String,
    values: List<String>,
): String {
    val constMap = values.associateBy { enumConstant(it, false) }

    val constants = constMap.keys
        .joinToString("\n") { "val $it: $name" }

    return jsName(constMap) + """
        sealed external interface $name {
            companion object {
                $constants
            }
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
        // language=JavaScript
        $name
    """.trimIndent()
}

private fun enumConstant(
    source: String,
    strict: Boolean,
): String {
    val value = source
        .removePrefix("@")
        .removeSurrounding("[", "]")
        .removePrefix("::")
        .removePrefix(":")
        .removeSuffix("()")

    return when (value) {
        "" -> "none"
        "1" -> "D"

        "false",
        "true",

        "class",
        "for",
        "is",
        "object",
        -> "__${value}__"

        "data",
        "name",
        "open",
        "value",
        -> if (strict) "__${value}__" else value

        "super" -> "sup"

        else -> value.kebabToCamel()
    }
}
