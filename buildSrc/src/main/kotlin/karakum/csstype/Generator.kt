package karakum.csstype

import karakum.common.ConversionResult
import karakum.common.GENERATOR_COMMENT
import karakum.common.Suppress
import karakum.common.Suppress.*
import karakum.common.fileSuppress
import java.io.File

private val MEDIA_IMPORTS = """
import csstype.Length
import web.cssom.MediaQuery
""".trimIndent()

fun generateKotlinDeclarations(
    definitionsFile: File,
    sourceDir: File,
) {
    writeDeclarations(
        declarations = convertDefinitions(definitionsFile),
        sourceDir = sourceDir,
        pkg = "csstype",
    )

    writeDeclarations(
        declarations = mediaTypes(),
        sourceDir = sourceDir,
        pkg = "csstype.media",
        imports = MEDIA_IMPORTS,
    )
}

private fun writeDeclarations(
    declarations: Sequence<ConversionResult>,
    sourceDir: File,
    pkg: String,
    imports: String = "",
) {
    val targetDir = sourceDir
        .resolve(pkg.replace(".", "/"))
        .also { it.mkdirs() }

    for ((name, body) in declarations) {
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

            if (name == VARIABLE_RECORD)
                add(NATIVE_INDEXER_KEY_SHOULD_BE_STRING_OR_NUMBER)
        }.toTypedArray()

        val annotations = if (suppresses.isNotEmpty()) {
            fileSuppress(*suppresses)
        } else ""

        targetDir.resolve("$name.kt")
            .also { check(!it.exists()) { "Duplicated file: ${it.name}" } }
            .writeText(
                fileContent(
                    annotations = annotations,
                    imports = imports,
                    body = body,
                    pkg = pkg
                )
            )
    }
}

private fun fileContent(
    annotations: String,
    imports: String,
    body: String,
    pkg: String,
): String {
    var result = sequenceOf(
        "// $GENERATOR_COMMENT",
        annotations,
        "package $pkg",
        imports,
        body,
    ).filter { it.isNotEmpty() }
        .joinToString("\n\n")

    if (!result.endsWith("\n"))
        result += "\n"

    return result
}
