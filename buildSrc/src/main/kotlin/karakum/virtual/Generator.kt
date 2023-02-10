package karakum.virtual

import karakum.common.GENERATOR_COMMENT
import java.io.File

private val DEFAULT_IMPORTS = listOf(
    "Promise" to "kotlin.js.Promise",
    "RegExp" to "kotlin.js.RegExp",

    "ReadonlyArray" to "js.core.ReadonlyArray",
    "Record" to "js.core.Record",
    "ReadonlyMap" to "js.collections.ReadonlyMap",
    "JsTuple2" to "js.core.JsTuple2",
    "Void" to "js.core.Void",

    "Element" to "web.dom.Element",
    "Window" to "web.window.Window",
    "EventTarget" to "web.events.EventTarget",
    "ScrollBehavior" to "web.scroll.ScrollBehavior",
    "ResizeObserverEntry" to "web.dom.observers.ResizeObserverEntry",
)

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
            .writeText(fileContent(Package.VIRTUAL_CORE, annotations, body))
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
