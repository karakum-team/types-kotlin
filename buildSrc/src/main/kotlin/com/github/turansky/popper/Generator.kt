package com.github.turansky.popper

import com.github.turansky.common.GENERATOR_COMMENT
import java.io.File

fun generateKotlinDeclarations(
    definitionsDir: File,
    sourceDir: File,
) {
    val targetDir = sourceDir
        .resolve("popper/core")
        .also { it.mkdirs() }

    targetDir.resolve("types.kt_")
        .writeText(definitionsDir.resolve("types.d.ts").readText())
}

private fun fileContent(
    annotations: String = "",
    body: String,
): String {
    var result = sequenceOf(
        "// $GENERATOR_COMMENT",
        annotations,
        "package csstype",
        body,
    ).filter { it.isNotEmpty() }
        .joinToString("\n\n")

    if (!result.endsWith("\n"))
        result += "\n"

    return result
}
