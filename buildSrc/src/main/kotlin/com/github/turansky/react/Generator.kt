package com.github.turansky.react

import java.io.File

private const val GENERATOR_COMMENT = "Automatically generated - do not modify!"

private enum class Suppress {
    EXTERNAL_TYPE_EXTENDS_NON_EXTERNAL_TYPE,
    DECLARATION_CANT_BE_INLINED,

    ;
}

// language=Kotlin
private const val PACKAGE = "package react.dom"

fun generateKotlinDeclarations(
    definitionsFile: File,
    sourceDir: File,
) {
    val targetDir = sourceDir.resolve("react/dom")
        .also { it.mkdirs() }

    for ((name, body) in convertDefinitions(definitionsFile)) {
        val annotations = when (name) {
            "AriaAttributes" -> {
                sequenceOf(Suppress.EXTERNAL_TYPE_EXTENDS_NON_EXTERNAL_TYPE, Suppress.DECLARATION_CANT_BE_INLINED)
                    .map { """"${it.name}"""" }
                    .joinToString(",\n", "@file:Suppress(\n", ",\n)")
            }
            else -> ""
        }

        targetDir.resolve("${name}.kt")
            .writeText(fileContent(annotations, body))
    }
}

private fun fileContent(
    annotations: String = "",
    body: String,
) =
    sequenceOf(
        "// $GENERATOR_COMMENT",
        annotations,
        PACKAGE,
        body,
    ).filter { it.isNotEmpty() }
        .joinToString("\n\n")
