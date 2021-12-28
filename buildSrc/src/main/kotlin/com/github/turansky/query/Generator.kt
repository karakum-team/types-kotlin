package com.github.turansky.query

import java.io.File

const val GENERATOR_COMMENT = "Automatically generated - do not modify!"

// language=Kotlin
private const val MODULE_DECLARATION = "@file:JsModule(\"react-query\")\n@file:JsNonModule"

private enum class Suppress {
    UNUSED_TYPEALIAS_PARAMETER,
    NON_EXTERNAL_DECLARATION_IN_INAPPROPRIATE_FILE,

    ;
}

// language=Kotlin
private const val PACKAGE = """package react.query"""

private const val EXPERIMENTAL_SUFFIX = "-experimental"

fun generateKotlinDeclarations(
    typesDir: File,
    sourceDir: File,
) {
    val targetDir = sourceDir.resolve("react/query")
        .also { it.mkdirs() }

    targetDir.resolve("abort.kt")
        .writeText(ABORT)

    targetDir.resolve("aliases.kt")
        .writeText(ALIASES)

    generate(typesDir.resolve("core"), targetDir, ".core")
    generate(typesDir.resolve("react"), targetDir)

    generateExperimental(typesDir, targetDir)
}

private fun generate(
    definitionsDir: File,
    targetDir: File,
    suffix: String = "",
) {
    val index = definitionsDir.resolve("index.d.ts")
    val files = definitionsDir
        .listFiles { file ->
            file != index && file.name.endsWith(".d.ts")
        }
        ?: return

    val fileMap = files
        .asSequence()
        .map { file ->
            val name = file.name.removeSuffix(".d.ts") + suffix + ".kt"
            val declarations = toDeclarations(file)
            name to declarations
        }
        .filter { (_, declarations) -> declarations.isNotEmpty() }
        .flatMap { (name, declarations) ->
            val enums = declarations.filter { it is Type && it.enumMode }

            enums.asSequence()
                .map { "${it.name}$suffix.kt" to listOf(it) }
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
        generate(targetDir.resolve(name), declarations)
    }
}

private fun generateExperimental(
    typesDir: File,
    targetDir: File,
) {
    val dirs = typesDir.listFiles { file ->
        file.isDirectory && file.name.endsWith(EXPERIMENTAL_SUFFIX)
    } ?: return

    for (dir in dirs) {
        val name = dir.name.removeSuffix(EXPERIMENTAL_SUFFIX)
        if (name != "broadcastQueryClient") continue
        generate(
            file = targetDir.resolve("$name.kt"),
            declarations = toDeclarations(dir.resolve("index.d.ts")),
        )
    }
}

private fun generate(
    file: File,
    declarations: List<Declaration>,
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
        if ("> = Union" in body || "> = (" in body || "> = Any" in body)
            types.add(Suppress.UNUSED_TYPEALIAS_PARAMETER)

        if (moduleRequired && "typealias " in body)
            types.add(Suppress.NON_EXTERNAL_DECLARATION_IN_INAPPROPRIATE_FILE)

        if (types.isNotEmpty()) {
            types.asSequence()
                .map { "\"${it.name}\",\n" }
                .joinToString("", "@file:Suppress(\n", ")")
        } else ""
    }

    val text = sequenceOf(
        "// $GENERATOR_COMMENT",
        annotations,
        suppresses,
        PACKAGE,
        body,
    ).filter { it.isNotEmpty() }
        .joinToString("\n\n")

    file.writeText(text)
}
