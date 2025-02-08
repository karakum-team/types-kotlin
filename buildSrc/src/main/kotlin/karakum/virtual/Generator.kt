package karakum.virtual

import karakum.common.GENERATOR_COMMENT
import karakum.common.writeCode
import java.io.File

private val DEFAULT_IMPORTS = listOf(
    "js.promise.Promise",

    "js.array.ReadonlyArray",
    "js.objects.JsPlainObject",
    "js.collections.ReadonlyMap",
    "js.array.JsTuple2",
    "js.core.Void",

    "web.dom.Element",
    "web.window.Window",
    "web.events.EventTarget",
    "web.scroll.ScrollBehavior",
    "web.dom.observers.ResizeObserverEntry",

    "seskar.js.JsRawValue",
    "seskar.js.JsValue",
).map { it.substringAfterLast(".") to it }

fun generateKotlinDeclarations(
    coreDefinitionsFile: File,
    sourceDir: File,
) {
    val targetDir = sourceDir.resolve("tanstack/virtual/core")
        .also { it.mkdirs() }

    for ((name, body) in convertDefinitions(coreDefinitionsFile)) {
        val annotations = when {
            "external val " in body || "external object " in body || "external fun " in body || "external class " in body
                -> "@file:JsModule(\"${Package.VIRTUAL_CORE.moduleName}\")"

            else -> ""
        }

        targetDir.resolve("${name}.kt")
            .writeCode(fileContent(Package.VIRTUAL_CORE, annotations, body))
    }
}

private fun fileContent(
    pkg: Package,
    annotations: String,
    body: String,
): String {
    val defaultImports = DEFAULT_IMPORTS
        .filter { it.first in body }
        .map { "import ${it.second}" }
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
