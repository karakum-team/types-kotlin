package com.github.turansky.csstype

import com.github.turansky.common.GENERATOR_COMMENT
import java.io.File

fun generateKotlinDeclarations(
    definitionsFile: File,
    sourceDir: File,
) {
    val targetDir = sourceDir
        .resolve("csstype")
        .also { it.mkdirs() }

    for ((name, body, ready) in convertDefinitions(definitionsFile)) {
        val ext = if (ready) "kt" else "d.ts"
        targetDir.resolve("$name.$ext")
            .also { check(!it.exists()) { "Duplicated file: ${it.name}" } }
            .writeText(fileContent(body = body))
    }
}

private fun fileContent(
    annotations: String = "",
    body: String,
): String {
    return sequenceOf(
        "// $GENERATOR_COMMENT",
        annotations,
        "package csstype",
        body,
    ).filter { it.isNotEmpty() }
        .joinToString("\n\n")
}
