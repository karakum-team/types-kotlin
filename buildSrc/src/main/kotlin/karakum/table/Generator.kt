package karakum.table

import karakum.common.GENERATOR_COMMENT
import karakum.common.Suppress.NOTHING_TO_INLINE
import karakum.common.fileSuppress
import karakum.common.writeCode
import java.io.File

private val DEFAULT_IMPORTS = listOf(
    "js.promise.Promise",
    "js.regexp.RegExp",

    "js.array.JsTuple2",
    "js.array.ReadonlyArray",
    "js.collections.JsMap",
    "js.core.Void",
    "js.objects.ReadonlyRecord",
    "js.reflect.unsafeCast",
    "js.symbol.Symbol",

    "seskar.js.JsIntValue",
    "seskar.js.JsValue",

    "web.dom.Document",
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

            "inline fun " in body
                -> fileSuppress(NOTHING_TO_INLINE)

            else -> ""
        }

        val fileName = if (name.endsWith("Fns") && "interface" in body) {
            name + ".type"
        } else name

        targetDir.resolve("$fileName.kt")
            .writeCode(fileContent(Package.TABLE_CORE, annotations, body))
    }
}

private fun readContent(
    definitionsDir: File,
): String =
    definitionsDir.walk()
        .maxDepth(2)
        .filter { it.isFile }
        .filter { it.name.endsWith(".d.ts") }
        .map { file ->
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

    return sequenceOf(
        "// $GENERATOR_COMMENT",
        annotations,
        pkg.pkg,
        defaultImports,
        body,
    ).filter { it.isNotEmpty() }
        .joinToString("\n\n")
}
