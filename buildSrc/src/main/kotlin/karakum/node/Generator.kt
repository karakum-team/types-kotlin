package karakum.node

import karakum.common.GENERATOR_COMMENT
import java.io.File

private val DEFAULT_IMPORTS = "import kotlinext.js.ReadonlyArray"

fun generateKotlinDeclarations(
    definitionsDir: File,
    sourceDir: File,
) {
    val targetDir = sourceDir
        .resolve("node")
        .also { it.mkdirs() }

    println("Node definitions:")
    println(definitionsDir.listFiles()!!.joinToString("\n") { it.name })
}

private fun fileContent(
    annotations: String = "",
    body: String,
): String {
    val defaultImports = if ("ReadonlyArray" in body) {
        DEFAULT_IMPORTS
    } else ""

    var result = sequenceOf(
        "// $GENERATOR_COMMENT",
        annotations,
        "package node",
        defaultImports,
        body,
    ).filter { it.isNotEmpty() }
        .joinToString("\n\n")

    if (!result.endsWith("\n"))
        result += "\n"

    return result
}
