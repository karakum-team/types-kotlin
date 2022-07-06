package karakum.table

import karakum.common.GENERATOR_COMMENT
import java.io.File

private val DEFAULT_IMPORTS = listOf(
    "Promise" to "kotlin.js.Promise",

    "ReadonlyArray" to "kotlinx.js.ReadonlyArray",
    "Record" to "kotlinx.js.Record",
    "JsPair" to "kotlinx.js.JsPair",
    "Void" to "kotlinx.js.Void",
)

fun generateKotlinDeclarations(
    coreDefinitionsFile: File,
    definitionsFile: File,
    sourceDir: File,
) {
    for ((name, body) in convertDefinitions(coreDefinitionsFile)) {
        val targetDir = sourceDir.resolve("tanstack/table/core")
            .also { it.mkdirs() }

        targetDir.resolve("${name}.kt")
            .writeText(fileContent(Package.TABLE_CORE, "", body))
    }
}

private fun fileContent(
    pkg: Package,
    annotations: String,
    body: String,
): String {
    val defaultImports = DEFAULT_IMPORTS
        .filter { it.first in body }
        .map { "import ${it.second}" }
        .joinToString("\n")

    return sequenceOf(
        "// $GENERATOR_COMMENT",
        annotations,
        pkg.pkg,
        defaultImports,
        body,
    ).filter { it.isNotEmpty() }
        .joinToString("\n\n")
}
