package com.github.turansky.csstype

import com.github.turansky.common.GENERATOR_COMMENT
import com.github.turansky.common.Suppress.*
import com.github.turansky.common.fileSuppress
import java.io.File

fun generateKotlinDeclarations(
    definitionsFile: File,
    sourceDir: File,
) {
    val targetDir = sourceDir
        .resolve("csstype")
        .also { it.mkdirs() }

    for ((name, body) in convertDefinitions(definitionsFile)) {
        val annotations = when {
            "inline fun " in body
            -> fileSuppress(NOTHING_TO_INLINE)

            "companion object" in body
            -> fileSuppress(NAME_CONTAINS_ILLEGAL_CHARS, NESTED_CLASS_IN_EXTERNAL_INTERFACE)

            "JsName(\"\"\"({" in body
            -> fileSuppress(NAME_CONTAINS_ILLEGAL_CHARS)

            else -> ""
        }

        targetDir.resolve("$name.kt")
            .also { check(!it.exists()) { "Duplicated file: ${it.name}" } }
            .writeText(fileContent(annotations, body))
    }
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
