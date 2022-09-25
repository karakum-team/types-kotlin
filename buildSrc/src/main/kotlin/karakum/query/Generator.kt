package karakum.query

import java.io.File

const val GENERATOR_COMMENT = "Automatically generated - do not modify!"

private enum class Suppress {
    UNUSED_TYPEALIAS_PARAMETER,
    NON_EXTERNAL_DECLARATION_IN_INAPPROPRIATE_FILE,

    ;
}

private val DEFAULT_IMPORTS = listOf(
    "Promise" to "kotlin.js.Promise",

    "JsTuple2" to "kotlinx.js.JsTuple2",
    "PromiseResult" to "kotlinx.js.PromiseResult",
    "ReadonlyArray" to "kotlinx.js.ReadonlyArray",
    "Record" to "kotlinx.js.Record",
    "Void" to "kotlinx.js.Void",
)

fun generateKotlinDeclarations(
    coreTypesDir: File,
    reactTypesDir: File,
    sourceDir: File,
) {
    val coreTargetDir = sourceDir.resolve(Package.CORE.path)
        .also { it.mkdirs() }

    val reactTargetDir = sourceDir.resolve(Package.REACT.path)
        .also { it.mkdirs() }

    coreTargetDir.resolve("aliases.kt")
        .writeText(ALIASES_BODY)

    coreTargetDir.resolve("$QUERY_KEY.kt")
        .writeText(QUERY_KEY_BODY)

    generate(coreTypesDir, coreTargetDir, Package.CORE)
    generate(reactTypesDir, reactTargetDir, Package.REACT)
}

private fun moduleDeclaration(
    pkg: Package,
): String =
    "@file:JsModule(\"${pkg.module}\")\n@file:JsNonModule"

private fun generate(
    definitionsDir: File,
    targetDir: File,
    pkg: Package,
) {
    val files = definitionsDir
        .listFiles { file ->
            val name = file.name
                .takeIf { it.endsWith(".d.ts") }
                ?.removeSuffix(".d.ts")

            name != null
                    && name != "index"
                    && !name.endsWith(".native")
                    // TEMP
                    && name != "errorBoundaryUtils"
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
    pkg: Package,
) {
    val body = declarations.asSequence()
        .map { it.toCode() }
        .filter { it.isNotEmpty() }
        .joinToString("\n\n")

    val moduleRequired = "external class " in body || "external fun " in body || "external val " in body
    val annotations = if (moduleRequired) {
        moduleDeclaration(pkg)
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

    val coreImport = sequenceOf(Package.CORE)
        .minus(pkg)
        .map { it.starImport }

    val defaultImports = DEFAULT_IMPORTS
        .filter { it.first in body }
        .map { "import ${it.second}" }
        .plus(coreImport)
        .joinToString("\n")

    val text = sequenceOf(
        "// $GENERATOR_COMMENT",
        annotations,
        suppresses,
        pkg.pkg,
        defaultImports,
        body,
    ).filter { it.isNotEmpty() }
        .joinToString("\n\n")

    file.writeText(text)
}
