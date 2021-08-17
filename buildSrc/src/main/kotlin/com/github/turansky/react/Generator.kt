package com.github.turansky.react

import java.io.File

private const val GENERATOR_COMMENT = "Automatically generated - do not modify!"

private enum class Suppress {
    EXTERNAL_TYPE_EXTENDS_NON_EXTERNAL_TYPE,
    DECLARATION_CANT_BE_INLINED,

    ;
}

fun generateKotlinDeclarations(
    definitionsFile: File,
    sourceDir: File,
) {
    for ((name, body, pkg) in convertDefinitions(definitionsFile)) {
        val annotations = when (name) {
            "AriaAttributes" -> {
                sequenceOf(Suppress.EXTERNAL_TYPE_EXTENDS_NON_EXTERNAL_TYPE, Suppress.DECLARATION_CANT_BE_INLINED)
                    .map { """"${it.name}"""" }
                    .joinToString(",\n", "@file:Suppress(\n", ",\n)")
            }
            else -> ""
        }

        val finalPkg = if ("SVG" in name) Package.SVG else pkg

        val content = if (finalPkg == Package.SVG) {
            sequenceOf(
                "AriaAttributes",
                "DOMAttributes",
                "AriaRole",
            ).fold(body) { acc, clazz ->
                acc.replace(clazz, "react.dom.$clazz")
            }
        } else body

        val targetDir = sourceDir.resolve(finalPkg.path)
            .also { it.mkdirs() }

        targetDir.resolve("${name}.kt")
            .writeText(fileContent(finalPkg, annotations, content))
    }
}

private fun fileContent(
    pkg: Package,
    annotations: String,
    body: String,
) =
    sequenceOf(
        "// $GENERATOR_COMMENT",
        annotations,
        pkg.pkg,
        body,
    ).filter { it.isNotEmpty() }
        .joinToString("\n\n")
