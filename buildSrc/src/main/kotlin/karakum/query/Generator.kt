package karakum.query

import karakum.common.GENERATOR_COMMENT
import karakum.common.Suppress
import java.io.File

private val DEFAULT_IMPORTS = listOf(
    "js.core.EpochTimeStamp",
    "js.core.JsTuple2",
    "js.core.ReadonlyArray",
    "js.core.Record",
    "js.core.Symbol",
    "js.core.Void",

    "js.promise.Promise",
    "js.promise.PromiseResult",

    "js.collections.JsSet",

    "js.iterable.IterableIterator",

    "web.abort.AbortController",
    "web.abort.AbortSignal",

    "seskar.js.JsIntValue",
    "seskar.js.JsVirtual",
    "seskar.js.JsValue",
).map { it.substringAfterLast(".") to it }

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
    "@file:JsModule(\"${pkg.module}\")"

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
                    && name != "useSyncExternalStore"
                    && name != "reactBatchedUpdates"
                    && name != "queryClient-89a8a2bf"
                    && name != "utils"
                    && !(name == "QueryStatus" && pkg == Package.REACT)
                    // TEMP
                    && name != "suspense"
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
    if (file.name.startsWith("queryClient-")) {
        for (group in declarations.groupBy { it.name }.values) {
            generate(
                file = file.parentFile.resolve(group.first().name + ".kt"),
                declarations = group,
                pkg = pkg,
            )
        }
        return
    }

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

        if ("@JsValue(" in body) {
            types.add(Suppress.NESTED_CLASS_IN_EXTERNAL_INTERFACE)
        }

        if (types.isNotEmpty()) {
            types.asSequence()
                .map { "\"${it.name}\",\n" }
                .joinToString("", "@file:Suppress(\n", ")")
        } else ""
    }

    val coreImport = sequenceOf(Package.CORE)
        .minus(pkg)
        .map {
            """
            import tanstack.query.core.InfiniteQueryObserverOptions
            import tanstack.query.core.InfiniteQueryObserverResult
            import tanstack.query.core.DefinedQueryObserverResult
            import tanstack.query.core.HydrateOptions
            import tanstack.query.core.QueryClient
            import tanstack.query.core.QueryFunction
            import tanstack.query.core.QueryKey
            import tanstack.query.core.QueryObserver
            import tanstack.query.core.QueryObserverOptions
            import tanstack.query.core.QueryObserverResult
            import tanstack.query.core.QueryStatus
            import tanstack.query.core.MutationObserverOptions
            import tanstack.query.core.MutationFilters
            import tanstack.query.core.MutationFunction
            import tanstack.query.core.MutationKey
            import tanstack.query.core.MutateFunction
            import tanstack.query.core.MutationObserverResult
            import tanstack.query.core.QueryFilters
            
            import tanstack.query.core.False
            import tanstack.query.core.True
            """.trimIndent()
        }

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
