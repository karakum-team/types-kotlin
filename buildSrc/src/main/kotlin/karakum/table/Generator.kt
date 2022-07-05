package karakum.table

import karakum.common.GENERATOR_COMMENT
import karakum.common.Suppress.DECLARATION_CANT_BE_INLINED
import karakum.common.Suppress.EXTERNAL_TYPE_EXTENDS_NON_EXTERNAL_TYPE
import karakum.common.fileSuppress
import java.io.File

fun generateKotlinDeclarations(
    coreDefinitionsFile: File,
    definitionsFile: File,
    sourceDir: File,
) {
    val lineCount = coreDefinitionsFile.readLines().size
    println("LINES: $lineCount")
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
