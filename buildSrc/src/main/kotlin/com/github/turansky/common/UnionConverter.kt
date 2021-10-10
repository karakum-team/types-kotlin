package com.github.turansky.common

private const val UNION = """/*union*/"""

internal fun unionBody(
    name: String,
    values: List<String>,
): String {
    val constants = values.map(::unionConstant)

    return unionBodyByConstants(name, constants)
}

internal fun unionBodyByConstants(
    name: String,
    constants: List<UnionConstant>,
): String {
    val constantNames = constants
        .joinToString("") { "${it.kotlinName},\n" }

    return jsName(constants) + """
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
    val constants = values.map(::unionConstant)

    val bodyMembers = constants
        .joinToString("\n") { "val ${it.kotlinName}: $name" }

    return jsName(constants) + """
        sealed external interface $name {
            companion object {
                $bodyMembers
            }
        }
    """.trimIndent()
}

internal fun sealedUnionBody(
    name: String,
    parentType: String,
    values: List<String>,
): String {
    val constants = values.map(::unionConstant)

    val bodyMembers = constants
        .joinToString("\n") { "val ${it.kotlinName}: $parentType.${it.kotlinName.capitalize()}" }

    return jsName(constants) + """
        sealed external interface $name: $parentType {
            companion object {
                $bodyMembers
            }
        }
    """.trimIndent()
}

private fun jsName(
    constants: List<UnionConstant>,
): String {
    val name = constants
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

internal data class UnionConstant(
    val kotlinName: String,
    val jsName: String,
    val value: String,
)

internal fun unionConstant(value: String): UnionConstant {
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

    return UnionConstant(kotlinName, jsName, value)
}
