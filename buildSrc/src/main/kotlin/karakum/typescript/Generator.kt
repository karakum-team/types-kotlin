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
            if ("JsName(\"\"\"(" in body)
                add(NAME_CONTAINS_ILLEGAL_CHARS)

            if ("contract {" in body)
                add(CANNOT_CHECK_FOR_EXTERNAL_INTERFACE)

            when (name) {
                "isAsteriskToken",
                "isDotDotDotToken",
                "isMinusToken",
                "isPlusToken",
                -> {
                    add(CANNOT_CHECK_FOR_ERASED)
                    add(ERROR_IN_CONTRACT_DESCRIPTION)
                }
            }

            if ("companion object" in body || name == "SyntaxKind" || name == "TypePredicateKind" || name == "InvalidatedProjectKind")
                add(NESTED_CLASS_IN_EXTERNAL_INTERFACE)

            if ("override var kind: TypePredicateKind." in body || name == "TupleTypeReference" || name == "CompletionEntryDataResolved" || name == "DiagnosticWithLocation")
                add(VAR_TYPE_MISMATCH_ON_OVERRIDE)

            if ("inline operator fun " in body && name != "ReadonlyArrayAdapter")
                add(DECLARATION_CANT_BE_INLINED)

            if (name == "ReadonlySet" || name == "Set") {
                add(PARAMETER_NAME_CHANGED_ON_OVERRIDE)
                add(DIFFERENT_NAMES_FOR_THE_SAME_PARAMETER_IN_SUPERTYPES)
            }
        }.toTypedArray()

        val annotations = when {
            "external val " in body || "external fun " in body || "external class " in body || "external object " in body
            -> "@file:JsModule(\"typescript\")\n@file:JsNonModule"

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

private fun fileContent(
    pkg: Package,
    annotations: String = "",
    body: String,
): String {
    val defaultImports = sequenceOf("JsTuple2", "JsIterator", "ReadonlyArray")
        .filter { "$it<" in body }
        .map { "import js.core.$it" }
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
