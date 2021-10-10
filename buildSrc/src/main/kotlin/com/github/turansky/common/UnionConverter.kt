package com.github.turansky.common

private const val UNION = """/*union*/"""

internal fun unionBody(
    name: String,
    values: List<String>,
): String {
    val constMapJsName = values.associateBy { enumConstant(it, strict = false, jsName = true) }
    val constMapBody = values.associateBy { enumConstant(it, strict = false, jsName = false) }

    return unionBody(name, constMapJsName, constMapBody)
}

internal fun unionBody(
    name: String,
    constMapJsName: Map<String, String>,
    constMapBody: Map<String, String> = constMapJsName,
): String {
    val constantNames = constMapBody.keys
        .joinToString("") { "$it,\n" }

    return jsName(constMapJsName) + """
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
    val constMapJsName = values.associateBy { enumConstant(it, strict = false, jsName = true) }
    val constMapBody = values.associateBy { enumConstant(it, strict = false, jsName = false) }

    val constants = constMapBody.keys
        .joinToString("\n") { "val $it: $name" }

    return jsName(constMapJsName) + """
        sealed external interface $name {
            companion object {
                $constants
            }
        }
    """.trimIndent()
}

internal fun sealedUnionBody(
    name: String,
    parentType: String,
    values: List<String>,
): String {
    val constMapJsName = values.associateBy { enumConstant(it, strict = false, jsName = true) }
    val constMapBody = values.associateBy { enumConstant(it, strict = false, jsName = false) }

    val constants = constMapBody.keys
        .joinToString("\n") { "val $it: $parentType.${it.capitalize()}" }

    return jsName(constMapJsName) + """
        sealed external interface $name: $parentType {
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
            prefix = "@JsName(\"\"\"($UNION{",
            postfix = "}$UNION)\"\"\")",
        ) { (key, value) ->
            "$key: '$value'"
        }

    return """
        // language=JavaScript
        $name
    """.trimIndent()
}

internal fun enumConstant(
    source: String,
    strict: Boolean,
    jsName: Boolean,
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
        "super",
        -> if (!jsName) "`${value}`" else value

        "data",
        "open",
        "value",
        -> if (strict && !jsName) "`${value}`" else value

        "name" -> "htmlName"

        else -> value.kebabToCamel()
    }
}
