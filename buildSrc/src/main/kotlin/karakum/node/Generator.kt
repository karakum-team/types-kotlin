package karakum.node

import karakum.common.GENERATOR_COMMENT
import karakum.common.Suppress
import karakum.common.fileSuppress
import java.io.File

private val DEFAULT_IMPORTS = listOf(
    "Promise" to "kotlin.js.Promise",

    "AsyncIterable" to "kotlinx.js.AsyncIterable",
    "BigInt" to "kotlinx.js.BigInt",
    "JsSet" to "kotlinx.js.JsSet",
    "PromiseResult" to "kotlinx.js.PromiseResult",
    "Record" to "kotlinx.js.Record",
    "ReadonlyArray" to "kotlinx.js.ReadonlyArray",
    "Void" to "kotlinx.js.Void",

    "Uint8Array" to "org.khronos.webgl.Uint8Array",
    "ArrayBuffer" to "org.khronos.webgl.ArrayBuffer",
    "ArrayBufferView" to "org.khronos.webgl.ArrayBufferView",

    ".await()" to "kotlinx.coroutines.await",

    "Dict<" to "node.Dict",
    "AbortSignal" to "node.AbortSignal",
)

private val MODULES = setOf(
    "buffer",
    "events",
    "globals",
    "fs",
    "fs/promises",
    "os",
    "path",
    "process",
    "querystring",
    "stream/web",
    "url",
)

private val GLOBAL_TYPES = setOf(
    ABORT_CONTROLLER,
    ABORT_SIGNAL,
    "Buffer",
)

fun generateKotlinDeclarations(
    definitionsDir: File,
    sourceDir: File,
) {
    for (module in MODULES) {
        val pkg = Package(module)
        val targetDir = sourceDir
            .resolve(pkg.path)
            .also { it.mkdirs() }

        val source = definitionsDir.resolve("$module.d.ts").readText()
        for ((name, body) in convertDefinitions(source, pkg)) {
            val suppresses = mutableListOf<Suppress>().apply {
                if ("JsName(\"\"\"(" in body || "JsName(\"'" in body)
                    add(Suppress.NAME_CONTAINS_ILLEGAL_CHARS)
            }.toTypedArray()

            val annotations = when {
                name in GLOBAL_TYPES -> ""

                "@JsModule(" in body -> ""

                "external class " in body || "external val " in body || "external fun " in body
                -> "@file:JsModule(\"${pkg.id}\")\n@file:JsNonModule"

                suppresses.isNotEmpty()
                -> fileSuppress(*suppresses)

                else -> ""
            }

            val targetFile = targetDir.resolve("$name.kt")
            if (!targetFile.exists() || /* TEMP */ name == "EventEmitter") {
                targetFile.writeText(
                    fileContent(
                        annotations = annotations,
                        body = body,
                        pkg = pkg,
                    )
                )
            } else {
                targetFile.appendText("\n\n$body")
            }
        }
    }
}

private fun fileContent(
    annotations: String = "",
    body: String,
    pkg: Package,
): String {
    val defaultImports = DEFAULT_IMPORTS
        .filter { it.first in body }
        .map { "import ${it.second}" }
        .joinToString("\n")

    var result = sequenceOf(
        "// $GENERATOR_COMMENT",
        annotations,
        pkg.pkg,
        defaultImports,
        body,
    ).filter { it.isNotEmpty() }
        .joinToString("\n\n")

    if (!result.endsWith("\n"))
        result += "\n"

    return result
}
