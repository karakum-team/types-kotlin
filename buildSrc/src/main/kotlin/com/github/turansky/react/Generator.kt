package com.github.turansky.react

import com.github.turansky.common.GENERATOR_COMMENT
import com.github.turansky.common.Suppress.DECLARATION_CANT_BE_INLINED
import com.github.turansky.common.Suppress.EXTERNAL_TYPE_EXTENDS_NON_EXTERNAL_TYPE
import com.github.turansky.common.fileSuppress
import java.io.File

private val ARIA_IMPORTS = """
import react.dom.aria.AriaAttributes
import react.dom.aria.AriaRole    
""".trimIndent()

private val DOM_IMPORTS = """
import react.dom.html.DOMAttributes
""".trimIndent()

fun generateKotlinDeclarations(
    definitionsFile: File,
    sourceDir: File,
) {
    for ((name, body, pkg) in convertDefinitions(definitionsFile)) {
        val annotations = when (name) {
            "AriaAttributes" -> fileSuppress(EXTERNAL_TYPE_EXTENDS_NON_EXTERNAL_TYPE, DECLARATION_CANT_BE_INLINED)
            else -> ""
        }

        val finalPkg = when {
            name.startsWith("Aria") -> Package.ARIA
            "SVG" in name -> Package.SVG
            name == "PointerType" -> Package.EVENTS
            else -> pkg
        }

        val content = when (finalPkg) {
            Package.HTML,
            -> ARIA_IMPORTS + "\n" + body

            Package.SVG,
            -> ARIA_IMPORTS + "\n" + DOM_IMPORTS + "\n" + body

            else -> body
        }

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
