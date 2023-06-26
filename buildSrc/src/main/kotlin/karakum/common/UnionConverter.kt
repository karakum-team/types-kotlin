package karakum.common

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
        .joinToString("\n") {
            sequenceOf(
                it.comment,
                "val ${it.kotlinName}: ${it.type ?: name}",
            ).filterNotNull()
                .joinToString("\n")
        }

    return """
        ${jsName(constants)}
        sealed external interface $name {
            companion object {
            $constantNames
            }
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

    return """
        ${jsName(constants)}
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

internal fun objectUnionBody(
    name: String,
    type: String = name,
    constants: List<UnionConstant>,
): String {
    val constantNames = constants
        .joinToString("\n") {
            sequenceOf(
                it.comment,
                "object ${it.kotlinName} : $type",
            ).filterNotNull()
                .joinToString("\n")
        }

    val modifier = if (name == type) "sealed interface" else "object"
    return """
        ${jsName(constants)}
        external $modifier $name {
            $constantNames
        }
    """.trimIndent()
}

internal fun jsName(
    constants: List<UnionConstant>,
): String {
    val name = constants
        .joinToString(
            separator = ", ",
            prefix = "@JsName(\"\"\"($UNION{",
            postfix = "}$UNION)\"\"\")",
        ) {
            "${it.jsName}: ${it.jsValue}"
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
    val type: String? = null,
    private val originalValue: Boolean = false,
    val comment: String? = null,
) {
    val jsValue: String
        get() = if (originalValue) {
            if (value.startsWith("\"")) {
                "'${value.removeSurrounding("\"")}'"
            } else value
        } else "'$value'"
}

internal fun unionConstant(value: String): UnionConstant {
    val name = value
        .removePrefix("@")
        .removeSurrounding("[", "]")
        .removePrefix("::")
        .removePrefix(":")
        .removeSuffix("()")
        .replace(":", "-")

    val jsName = when (name) {
        "" -> "none"
        "1" -> "D"

        "2x" -> "x2"
        "4x" -> "x4"

        // TEMP
        "2d" -> "canvas"

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
