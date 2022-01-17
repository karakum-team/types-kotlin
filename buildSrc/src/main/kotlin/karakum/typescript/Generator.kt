package karakum.typescript

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
        .resolve("typescript")
        .also { it.mkdirs() }

    for ((name, body) in convertDefinitions(definitionsFile)) {
        val suppresses = mutableListOf<Suppress>().apply {
            if ("JsName(\"\"\"(" in body)
                add(NAME_CONTAINS_ILLEGAL_CHARS)

            if ("companion object" in body)
                add(NESTED_CLASS_IN_EXTERNAL_INTERFACE)

            if ("inline operator fun " in body)
                add(DECLARATION_CANT_BE_INLINED)
        }.toTypedArray()

        val annotations = when {
            "external val " in body || "external fun " in body || "external class " in body
            -> "@file:JsModule(\"typescript\")\n@file:JsNonModule"

            suppresses.isNotEmpty() ->
                fileSuppress(*suppresses)

            else -> ""
        }

        val suffix = when {
            "typealias " in body -> ""
            "external fun " in body -> ""
            "external enum class " in body -> ""
            "external interface " in body -> ""
            "external class " in body -> ""
            else -> "_"
        }

        val file = targetDir.resolve("$name.kt$suffix")
        if (file.exists()) {
            if (name[0].isLowerCase()) {
                file.appendText("\n$body")
            } else {
                println("Duplicated file: $name")
            }
        } else {
            file.writeText(fileContent(annotations, body))
        }
    }

    targetDir.resolve("ReadonlyArray.kt")
        .writeText(fileContent(body = "typealias ReadonlyArray<T> = Array<out T>"))
}

private fun fileContent(
    annotations: String = "",
    body: String,
): String {
    var result = sequenceOf(
        "// $GENERATOR_COMMENT",
        annotations,
        "package typescript",
        body,
    ).filter { it.isNotEmpty() }
        .joinToString("\n\n")

    if (!result.endsWith("\n"))
        result += "\n"

    return result
}
