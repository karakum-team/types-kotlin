package com.github.turansky.csstype

import com.github.turansky.common.GENERATOR_COMMENT
import java.io.File

fun generateKotlinDeclarations(
    definitionsFile: File,
    sourceDir: File,
) {
    val targetDir = sourceDir.resolve("csstype")
        .also { it.mkdirs() }

    definitionsFile.readText()
        .removePrefix("export {};\n")
        .splitToSequence("\nexport ", "\ndeclare ")
        .drop(1)
        .forEach { content ->
            val name = content.substringAfter(" ")
                .substringBefore(" ")
                .substringBefore("<")

            targetDir.resolve("$name.d.ts")
                .writeText(fileContent(body = content))
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
