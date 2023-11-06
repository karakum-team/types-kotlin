package karakum.common

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
                it.jsValueAnnotation,
                "val ${it.kotlinName}: ${it.type ?: name}",
            ).joinToString("\n")
        }

    return """
        @JsUnion
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

    val bodyMembers = constants.joinToString("\n") {
        """
        ${it.jsValueAnnotation}    
        val ${it.kotlinName}: $name
        """.trimIndent()
    }

    return """
        @JsUnion
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

    val bodyMembers = constants.joinToString("\n") {
        """
        ${it.jsValueAnnotation}    
        val ${it.kotlinName}: $parentType.${it.kotlinName.replaceFirstChar(Char::uppercase)}
        """.trimIndent()
    }

    return """
        @JsUnion
        sealed external interface $name: $parentType {
            companion object {
                $bodyMembers
            }
        }
    """.trimIndent()
}

internal fun objectUnionBody(
    name: String,
    constants: List<UnionConstant>,
): String {
    val constantNames = constants.joinToString("\n") {
        sequenceOf(
            it.jsValueAnnotation,
            "val ${it.kotlinName} : ${name}.${it.kotlinName}",
        ).joinToString("\n")
    }

    val constantTypes = constants.joinToString("\n") {
        "sealed interface ${it.kotlinName} : $name"
    }

    return """
        @JsUnion
        external sealed interface $name {
            companion object {
                $constantNames
            }
            
            $constantTypes
        }
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
    val jsValueAnnotation: String
        get() {
            val annotation = if (originalValue) {
                if (value.startsWith("\"")) {
                    "@JsValue(\"${value.removeSurrounding("\"")}\")"
                } else "@JsIntValue($value)"
            } else "@JsValue(\"$value\")"

            return listOfNotNull(comment, annotation)
                .joinToString("\n")
        }
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