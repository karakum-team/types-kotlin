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
    for ((name, body, pkg) in convertDefinitions(definitionsFile)) {
        val suppresses = mutableListOf<Suppress>().apply {
            if ("contract {" in body)
                add(CANNOT_CHECK_FOR_EXTERNAL_INTERFACE)

            if (name in CONTRACT_WITH_ERASED_TYPE) {
                add(CANNOT_CHECK_FOR_ERASED)
                add(ERROR_IN_CONTRACT_DESCRIPTION)
            }

            if ("companion object" in body
                || name == "SyntaxKind"
                || name == "TypePredicateKind"
                || name == "InvalidatedProjectKind"
            )
                add(NESTED_CLASS_IN_EXTERNAL_INTERFACE)

            if ("override var kind: TypePredicateKind." in body
                || name == "TupleTypeReference"
                || name == "CompletionEntryDataResolved"
                || name == "CompletionEntryDataUnresolved"
                || name == "DiagnosticWithLocation"
            )
                add(VAR_TYPE_MISMATCH_ON_OVERRIDE)

            if ("inline operator fun " in body && name != "ReadonlyArrayAdapter")
                add(DECLARATION_CANT_BE_INLINED)
        }.toTypedArray()

        val annotations = when {
            "external val " in body || "external fun " in body || "external class " in body || "external object " in body
            -> """@file:JsModule("typescript")"""

            suppresses.isNotEmpty() ->
                fileSuppress(*suppresses)

            else -> ""
        }

        val extension = when (name) {
            "CreateProgram" -> "alias.kt"
            else -> "kt"
        }

        val file = sourceDir
            .resolve(pkg.path)
            .also { it.mkdirs() }
            .resolve("$name.$extension")

        check(!file.exists())
        file.writeText(fileContent(pkg, annotations, body))
    }
}

private val DEFAULT_IMPORTS = listOf(
    "js.array.JsTuple2",
    "js.array.ReadonlyArray",
    "js.collections.JsMap",
    "js.collections.JsSet",
    "js.iterable.JsIterator",
)

private fun fileContent(
    pkg: Package,
    annotations: String = "",
    body: String,
): String {
    val defaultImports = DEFAULT_IMPORTS
        .filter { "${it.substringAfterLast(".")}<" in body }
        .map { "import $it" }
        .plus(
            sequenceOf("JsVirtual", "JsValue", "JsIntValue")
                .filter { it in body }
                .map { "import seskar.js.$it" }
        )
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
