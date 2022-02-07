package karakum.node

import karakum.common.GENERATOR_COMMENT
import java.io.File

private val DEFAULT_IMPORTS = "import kotlinext.js.ReadonlyArray"

private val MODULES = setOf(
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
            targetDir.resolve("$name.kt")
                .writeText(fileContent(body = body, pkg = pkg))
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
