package karakum.table

import karakum.common.GENERATOR_COMMENT
import java.io.File

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
    return sequenceOf(
        "// $GENERATOR_COMMENT",
        annotations,
        pkg.pkg,
        body,
    ).filter { it.isNotEmpty() }
        .joinToString("\n\n")
}
