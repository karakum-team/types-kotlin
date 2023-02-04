package karakum.actions

import karakum.common.GENERATOR_COMMENT
import karakum.common.Suppress
import karakum.common.fileSuppress
import java.io.File

private val DEFAULT_IMPORTS = """
import kotlin.js.Promise
import js.core.Record
import js.core.ReadonlyArray
import js.core.Void
import js.errors.JsError
import node.buffer.Buffer
import node.http.IncomingHttpHeaders
import node.http.OutgoingHttpHeaders
import web.url.URL

import actions.http.client.HttpClientResponse
""".trimIndent()

fun generateKotlinDeclarations(
    definitionsDir: File,
    sourceDir: File,
) {
    definitionsDir.listFiles()!!
        .filter { it.isDirectory }
        .forEach { dir ->
            generate(
                definitionsDir = dir,
                sourceDir = sourceDir,
            )
        }
}

private fun generate(
    definitionsDir: File,
    sourceDir: File,
) {
    val library = Library(definitionsDir.name)
    val files = sequenceOf("lib", "lib/internal")
        .map { definitionsDir.resolve(it) }
        .filter { it.exists() }
        .mapNotNull { it.listFiles { file -> file.name.endsWith(".d.ts") } }
        .flatMap { it.asSequence() }
        .toList()

    val dir = sourceDir.resolve(library.path)
        .also { it.mkdirs() }

    var results = files.asSequence()
        .flatMap { convert(it.readText()) }
        .toList()
        .mergeDuplicated()

    if (library.name == "http-client") {
        results +=
            ConversionResult(
                name = "HttpClientResponse",
                body = "// TEMP\nexternal interface HttpClientResponse",
            )
    }

    for ((name, body) in results) {
        val kotlinMode = "external interface " in body
                || "external enum " in body
                || "external fun " in body
                || "typealias" in body
        val ext = if (kotlinMode) "kt" else "d.ts"

        val finalBody = if (kotlinMode) {
            val suppresses = mutableListOf<Suppress>().apply {
                if ("JsName(\"\"\"(" in body)
                    add(Suppress.NAME_CONTAINS_ILLEGAL_CHARS)
            }.toTypedArray()

            val annotations = when {
                "external class " in body
                        || "external val " in body
                        || "external fun " in body
                -> """@file:JsModule("${library.moduleId}")"""

                suppresses.isNotEmpty()
                -> fileSuppress(*suppresses)

                else -> ""
            }

            fileContent(
                annotations = annotations,
                body = body,
                pkg = library.pkg,
            )
        } else body

        var f = dir.resolve(name + ".$ext")

        var index = 2
        while (f.exists()) {
            f = dir.resolve(name + "_${index++}.$ext")
        }

        check(!f.exists()) {
            "File $f already exists!"
        }

        f.writeText(finalBody)
    }
}

private fun fileContent(
    annotations: String = "",
    body: String,
    pkg: String,
): String {
    var result = sequenceOf(
        "// $GENERATOR_COMMENT",
        annotations,
        "package $pkg",
        DEFAULT_IMPORTS,
        body,
    ).filter { it.isNotEmpty() }
        .joinToString("\n\n")

    if (!result.endsWith("\n"))
        result += "\n"

    return result
}
