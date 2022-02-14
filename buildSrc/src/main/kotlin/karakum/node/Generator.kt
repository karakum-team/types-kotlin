package karakum.node

import karakum.common.GENERATOR_COMMENT
import karakum.common.Suppress
import karakum.common.fileSuppress
import java.io.File

private const val DEFAULT_IMPORTS = "import kotlinx.js.ReadonlyArray"

private val MODULES = setOf(
    "buffer",
    "fs",
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

            val annotations = if (suppresses.isNotEmpty()) {
                fileSuppress(*suppresses)
            } else ""

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
    val defaultImports = if ("ReadonlyArray" in body) {
        DEFAULT_IMPORTS
    } else ""

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
