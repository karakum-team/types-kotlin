package karakum.node

import karakum.common.GENERATOR_COMMENT
import karakum.common.Suppress
import karakum.common.fileSuppress
import java.io.File

private val DEFAULT_IMPORTS = listOf(
    "BigInt" to "kotlinx.js.BigInt",
    "ReadonlyArray" to "kotlinx.js.ReadonlyArray",
    "Uint8Array" to "org.khronos.webgl.Uint8Array",
)

private val MODULES = setOf(
    "buffer",
    "fs",
    "fs/promises",
    "path",
)

fun generateKotlinDeclarations(
    definitionsDir: File,
    sourceDir: File,
) {
    for (module in MODULES) {
        val pkg = Package(module)
        val targetDir = sourceDir
            .resolve(pkg.path)
            .also { it.mkdirs() }

        val source = definitionsDir.resolve("$module.d.ts").readText()
        for ((name, body) in convertDefinitions(source, pkg)) {
            val suppresses = mutableListOf<Suppress>().apply {
                if ("JsName(\"\"\"(" in body)
                    add(Suppress.NAME_CONTAINS_ILLEGAL_CHARS)
            }.toTypedArray()

            val annotations = when {
                // TEMP
                name == "Buffer" -> ""

                "external class " in body || "external val " in body || "external fun " in body
                -> "@file:JsModule(\"${pkg.id}\")\n@file:JsNonModule"

                suppresses.isNotEmpty()
                -> fileSuppress(*suppresses)

                else -> ""
            }

            targetDir.resolve("$name.kt")
                .writeText(fileContent(annotations = annotations, body = body, pkg = pkg))
        }
    }
}

private fun fileContent(
    annotations: String = "",
    body: String,
    pkg: Package,
): String {
    val defaultImports = DEFAULT_IMPORTS
        .filter { it.first in body }
        .map { "import ${it.second}" }
        .joinToString("\n")

    var result = sequenceOf(
        "// $GENERATOR_COMMENT",
        annotations,
        pkg.pkg,
        defaultImports,
        body,
    ).filter { it.isNotEmpty() }
        .joinToString("\n\n")

    if (!result.endsWith("\n"))
        result += "\n"

    return result
}
