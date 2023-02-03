package karakum.actions

import java.io.File

private val DEFAULT_IMPORTS = """
import kotlin.js.Promise
import js.core.Record
import js.core.ReadonlyArray
import node.buffer.Buffer
import web.url.URL
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

    if (library.name == "http-client") {
        results +=
            ConversionResult(
                name = "HttpClientResponse",
                body = "// TEMP\nexternal interface HttpClientResponse",
            )
    }

    for ((name, body) in results) {
        val kotlinMode = "external interface " in body || "typealias" in body
        val ext = if (kotlinMode) "kt" else "d.ts"

        val finalBody = if (kotlinMode) "package ${library.pkg}\n\n$DEFAULT_IMPORTS\n\n$body" else body

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
