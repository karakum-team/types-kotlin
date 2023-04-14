package karakum.react

import karakum.common.GENERATOR_COMMENT
import karakum.common.Suppress
import karakum.common.fileSuppress
import java.io.File

private val ARIA_IMPORTS = """
import react.dom.aria.AriaAttributes
import react.dom.aria.AriaRole    
""".trimIndent()

private val DOM_TYPES = setOf(
    "DOMAttributes",
    "DangerouslySetInnerHTML",
)

private val DOM_IMPORTS = """
import react.dom.DOMAttributes
import web.html.EnterKeyHint
import web.html.InputType
import web.html.Loading
import web.http.ReferrerPolicy
import web.window.WindowTarget
""".trimIndent()

fun generateKotlinDeclarations(
    definitionsFile: File,
    sourceDir: File,
) {
    for ((name, body, pkg) in convertDefinitions(definitionsFile)) {
        val suppresses = mutableListOf<Suppress>().apply {
            if ("JsName(\"\"\"(" in body) {
                add(Suppress.NAME_CONTAINS_ILLEGAL_CHARS)
                add(Suppress.NESTED_CLASS_IN_EXTERNAL_INTERFACE)
            }
        }.toTypedArray()

        val annotations = when {
            suppresses.isNotEmpty()
            -> fileSuppress(*suppresses)

            else -> ""
        }

        val finalPkg = when {
            name.startsWith("Aria") -> Package.ARIA
            name in DOM_TYPES -> Package.DOM
            "SVG" in name -> Package.SVG
            name == "PointerType" -> Package.EVENTS
            name == "ModifierKey" -> Package.EVENTS
            else -> pkg
        }

        val content = when (finalPkg) {
            Package.HTML,
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
