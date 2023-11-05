package karakum.table

import karakum.common.GENERATOR_COMMENT
import karakum.common.Suppress
import karakum.common.Suppress.NOTHING_TO_INLINE
import karakum.common.fileSuppress
import java.io.File

private val DEFAULT_IMPORTS = listOf(
    "js.promise.Promise",
    "kotlin.js.RegExp",

    "js.collections.JsMap",
    "js.core.ReadonlyArray",
    "js.core.ReadonlyRecord",
    "js.core.Symbol",
    "js.core.JsTuple2",
    "js.core.Void",

    "seskar.js.JsIntValue",
    "seskar.js.JsUnion",
    "seskar.js.JsValue",
).map { it.substringAfterLast(".") to it }

fun generateKotlinDeclarations(
    coreDefinitionsDir: File,
    sourceDir: File,
) {
    val content = readContent(coreDefinitionsDir)

    val targetDir = sourceDir.resolve("tanstack/table/core")
        .also { it.mkdirs() }

    for ((name, body) in convertDefinitions(content)) {
        val annotations = when {
            "external val " in body || "external object " in body || "external fun " in body
            -> "@file:JsModule(\"${Package.TABLE_CORE.moduleName}\")"

            "@JsValue(" in body
            -> fileSuppress(
                Suppress.NESTED_CLASS_IN_EXTERNAL_INTERFACE,
            )

            "inline fun " in body
            -> fileSuppress(NOTHING_TO_INLINE)

            else -> ""
        }

        val fileName = if (name.endsWith("Fns") && "interface" in body) {
            name + ".type"
        } else name

        targetDir.resolve("$fileName.kt")
            .writeText(fileContent(Package.TABLE_CORE, annotations, body))
    }
}

private fun readContent(
    definitionsDir: File,
): String =
    definitionsDir.walk()
        .maxDepth(2)
        .filter { it.isFile }
        .filter { it.name.endsWith(".d.ts") }
        .mapNotNull { file ->
            file.readLines()
                .filter { !it.startsWith("import ") }
                .filter { !it.startsWith("export * from ") }
                .joinToString("\n")
                .substringBefore("\nexport {")
                .trim()
        }
        .filter { it.isNotEmpty() }
        .joinToString("\n\n", "\n\n")

private fun fileContent(
    pkg: Package,
    annotations: String,
    body: String,
): String {
    val defaultImports = DEFAULT_IMPORTS
        .filter { it.first in body }
        .map { "import ${it.second}" }
        .plus("import tanstack.table.core.VisibilityColumn as ColumnVisibilityColumn")
        .joinToString("\n")

    val finalBody = if (!body.endsWith("\n")) {
        body + "\n"
    } else body

    return sequenceOf(
        "// $GENERATOR_COMMENT",
        annotations,
        pkg.pkg,
        defaultImports,
        finalBody,
    ).filter { it.isNotEmpty() }
        .joinToString("\n\n")
}
