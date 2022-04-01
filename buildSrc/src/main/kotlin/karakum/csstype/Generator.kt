package karakum.csstype

import karakum.common.GENERATOR_COMMENT
import karakum.common.Suppress
import karakum.common.Suppress.*
import karakum.common.fileSuppress
import java.io.File

fun generateKotlinDeclarations(
    definitionsFile: File,
    sourceDir: File,
) {
    val targetDir = sourceDir
        .resolve("csstype")
        .also { it.mkdirs() }

    for ((name, body) in convertDefinitions(definitionsFile)) {
        val suppresses = mutableSetOf<Suppress>().apply {
            if ("JsName(\"\"\"(" in body)
                add(NAME_CONTAINS_ILLEGAL_CHARS)

            if ("companion object" in body)
                add(NESTED_CLASS_IN_EXTERNAL_INTERFACE)

            if ("inline operator fun " in body)
                if (name == "$LENGTH.operators") {
                    add(NOTHING_TO_INLINE)
                } else {
                    add(DECLARATION_CANT_BE_INLINED)
                }

            if ("inline fun " in body)
                add(if (RULE_BUILDER in body) DECLARATION_CANT_BE_INLINED else NOTHING_TO_INLINE)
        }.toTypedArray()

        val annotations = if (suppresses.isNotEmpty()) {
            fileSuppress(*suppresses)
        } else ""

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
