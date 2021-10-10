package com.github.turansky.common

private const val UNION = """/*union*/"""

internal fun unionBody(
    name: String,
    values: List<String>,
): String {
    val constData = values.map(::enumConstant)

    return unionBodyByData(name, constData)
}

internal fun unionBodyByData(
    name: String,
    constData: List<ConstData>,
): String {
    val constantNames = constData
        .joinToString("") { "${it.kotlinName},\n" }

    return jsName(constData) + """
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
    val constData = values.map(::enumConstant)

    val constants = constData
        .joinToString("\n") { "val ${it.kotlinName}: $name" }

    return jsName(constData) + """
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
    val constData = values.map(::enumConstant)

    val constants = constData
        .joinToString("\n") { "val ${it.kotlinName}: $parentType.${it.kotlinName.capitalize()}" }

    return jsName(constData) + """
        sealed external interface $name: $parentType {
            companion object {
                $constants
            }
        }
    """.trimIndent()
}

private fun jsName(
    constData: List<ConstData>,
): String {
    val name = constData
        .joinToString(
            separator = ", ",
            prefix = "@JsName(\"\"\"($UNION{",
            postfix = "}$UNION)\"\"\")",
        ) {
            "${it.jsName}: '${it.value}'"
        }

    return """
        // language=JavaScript
        $name
    """.trimIndent()
}

internal data class ConstData(
    val kotlinName: String,
    val jsName: String,
    val value: String,
)

internal fun enumConstant(value: String): ConstData {
    val name = value
        .removePrefix("@")
        .removeSurrounding("[", "]")
        .removePrefix("::")
        .removePrefix(":")
        .removeSuffix("()")

    val jsName = when (name) {
        "" -> "none"
        "1" -> "D"

        "name" -> "htmlName"

        else -> name.kebabToCamel()
    }

    val kotlinName = when (jsName) {
        "false",
        "true",

        "class",
        "for",
        "is",
        "object",
        "super",
        -> "`${jsName}`"

        else -> jsName
    }

    return ConstData(kotlinName, jsName, value)
}
