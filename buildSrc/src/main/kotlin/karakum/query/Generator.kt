package karakum.query

import java.io.File

const val GENERATOR_COMMENT = "Automatically generated - do not modify!"

// language=Kotlin
private const val MODULE_DECLARATION = "@file:JsModule(\"react-query\")\n@file:JsNonModule"

private enum class Suppress {
    UNUSED_TYPEALIAS_PARAMETER,
    NON_EXTERNAL_DECLARATION_IN_INAPPROPRIATE_FILE,

    ;
}

private val DEFAULT_IMPORTS = listOf(
    "Promise" to "kotlin.js.Promise",

    "JsPair" to "kotlinx.js.JsPair",
    "ReadonlyArray" to "kotlinx.js.ReadonlyArray",
    "Record" to "kotlinx.js.Record",
    "Void" to "kotlinx.js.Void",
)

private const val CORE_PACKAGE = "package tanstack.query.core"
private const val REACT_PACKAGE = "package tanstack.react.query"

fun generateKotlinDeclarations(
    coreTypesDir: File,
    reactTypesDir: File,
    sourceDir: File,
) {
    val coreTargetDir = sourceDir.resolve("tanstack/query/core")
        .also { it.mkdirs() }

    val reactTargetDir = sourceDir.resolve("tanstack/react/query")
        .also { it.mkdirs() }

    coreTargetDir
        .resolve("aliases.kt")
        .writeText(ALIASES.replace("##pkg##", CORE_PACKAGE))

    generate(coreTypesDir, coreTargetDir, CORE_PACKAGE)
    generate(reactTypesDir, reactTargetDir, REACT_PACKAGE)
}

private fun generate(
    definitionsDir: File,
    targetDir: File,
    pkg: String,
) {
    val files = definitionsDir
        .listFiles { file ->
            val name = file.name
                .takeIf { it.endsWith(".d.ts") }
                ?.removeSuffix(".d.ts")

            name != null && name != "index" && !name.endsWith(".native")
        }
        ?: return

    val fileMap = files
        .asSequence()
        .map { file ->
            val name = file.name.removeSuffix(".d.ts") + ".kt"
            val declarations = toDeclarations(file)
            name to declarations
        }
        .filter { (_, declarations) -> declarations.isNotEmpty() }
        .flatMap { (name, declarations) ->
            val enums = declarations.filter { it is Type && it.enumMode }

            enums.asSequence()
                .map { "${it.name}.kt" to listOf(it) }
                .plus(name to (declarations - enums))
        }
        .toList()

    TypeRegistry(
        fileMap.asSequence()
            .flatMap { (_, declarations) -> declarations }
            .filterIsInstance<TypeBase>()
            .toList()
    )

    for ((name, declarations) in fileMap) {
        generate(targetDir.resolve(name), declarations, pkg)
    }
}

private fun generate(
    file: File,
    declarations: List<Declaration>,
    pkg: String,
) {
    val body = declarations.asSequence()
        .map { it.toCode() }
        .filter { it.isNotEmpty() }
        .joinToString("\n\n")

    val moduleRequired = "external class " in body || "external fun " in body || "external val " in body
    val annotations = if (moduleRequired) {
        MODULE_DECLARATION
    } else ""

    val suppresses: String = run {
        val types = mutableListOf<Suppress>()
        if ("> = Union" in body || "> = (" in body || "> = Any" in body || "> = Function<" in body)
            types.add(Suppress.UNUSED_TYPEALIAS_PARAMETER)

        if (moduleRequired && "typealias " in body)
            types.add(Suppress.NON_EXTERNAL_DECLARATION_IN_INAPPROPRIATE_FILE)

        if (types.isNotEmpty()) {
            types.asSequence()
                .map { "\"${it.name}\",\n" }
                .joinToString("", "@file:Suppress(\n", ")")
        } else ""
    }

    var coreImport = if (pkg != CORE_PACKAGE) {
        sequenceOf(
            CORE_PACKAGE.replace("package ", "import ") + ".*"
        )
    } else emptySequence<String>()

    val defaultImports = DEFAULT_IMPORTS
        .filter { it.first in body }
        .map { "import ${it.second}" }
        .plus(coreImport)
        .joinToString("\n")

    val text = sequenceOf(
        "// $GENERATOR_COMMENT",
        annotations,
        suppresses,
        pkg,
        defaultImports,
        body,
    ).filter { it.isNotEmpty() }
        .joinToString("\n\n")

    file.writeText(text)
}
