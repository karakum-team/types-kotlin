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
                "val ${it.name}: ${it.type ?: name}",
            ).joinToString("\n")
        }

    return """
        @JsVirtual
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
        val ${it.name}: $name
        """.trimIndent()
    }

    return """
        @JsVirtual
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
        val ${it.name}: $parentType.${it.name.replaceFirstChar(Char::uppercase)}
        """.trimIndent()
    }

    return """
        @JsVirtual
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
            "val ${it.name} : ${name}.${it.name}",
        ).joinToString("\n")
    }

    val constantTypes = constants.joinToString("\n") {
        "sealed interface ${it.name} : $name"
    }

    return """
        @JsVirtual
        external sealed interface $name {
            companion object {
                $constantNames
            }
            
            $constantTypes
        }
    """.trimIndent()
}

internal data class UnionConstant(
    val name: String,
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
            } else {
                val escapedValue = when (value) {
                    "\"" -> """\""""
                    else -> value
                }

                """@JsValue("$escapedValue")"""
            }

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
        "2-digit" -> "twoDigit"

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

    return UnionConstant(
        name = kotlinName,
        value = value,
    )
}
