package com.github.turansky.react

import com.github.turansky.common.GENERATOR_COMMENT
import com.github.turansky.common.Suppress.DECLARATION_CANT_BE_INLINED
import com.github.turansky.common.Suppress.EXTERNAL_TYPE_EXTENDS_NON_EXTERNAL_TYPE
import com.github.turansky.common.fileSuppress
import java.io.File

fun generateKotlinDeclarations(
    definitionsFile: File,
    sourceDir: File,
) {
    for ((name, body, pkg) in convertDefinitions(definitionsFile)) {
        val annotations = when (name) {
            "AriaAttributes" -> fileSuppress(EXTERNAL_TYPE_EXTENDS_NON_EXTERNAL_TYPE, DECLARATION_CANT_BE_INLINED)
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
): String {
    return sequenceOf(
        "// $GENERATOR_COMMENT",
        annotations,
        pkg.pkg,
        body,
    ).filter { it.isNotEmpty() }
        .joinToString("\n\n")
}
