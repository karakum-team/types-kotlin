package karakum.cesium

import karakum.common.GENERATOR_COMMENT
import karakum.common.Suppress
import karakum.common.fileSuppress
import java.io.File

private const val MODULE_ANNOTATION: String = """@file:JsModule("cesium")"""

internal fun generateKotlinDeclarations(
    engineDefinitionsFile: File,
    widgetsDefinitionsFile: File,
    sourceDir: File,
) {
    generate(
        declarations = parseDeclarations(engineDefinitionsFile)
            .plus(DefaultEvent)
            .plus(CameraOrientation),
        pkg = "cesium",
        sourceDir = sourceDir.resolve("cesium"),
    )

    generate(
        declarations = parseDeclarations(widgetsDefinitionsFile)
            .filter { it.name != "ContextOptions" }
            .filter { it.name != "WebGLOptions" },
        pkg = "cesium",
        sourceDir = sourceDir.resolve("cesium"),
    )
}

private fun generate(
    declarations: List<Declaration>,
    pkg: String,
    sourceDir: File,
) {
    sourceDir.mkdirs()

    for (declaration in declarations.sortedBy(Declaration::name)) {
        val file = sourceDir.resolve("${declaration.name}.kt")
        val body = declaration.toCode()
        if (!file.exists()) {
            val isRuntime = hasRuntimeDeclarations(body)
            val moduleDeclaration = if (isRuntime) {
                MODULE_ANNOTATION
            } else ""

            val suppresses = mutableListOf<Suppress>()
            if (declaration is TypeBase)
                suppresses += declaration.suppresses()

            if ("sealed external interface" in body && "companion object" in body)
                suppresses += Suppress.NESTED_CLASS_IN_EXTERNAL_INTERFACE

            val annotations = when {
                suppresses.isNotEmpty()
                -> fileSuppress(*suppresses.toTypedArray())

                else -> ""
            }

            val content = sequenceOf(
                "// $GENERATOR_COMMENT",
                moduleDeclaration,
                annotations,
                "package $pkg",
                body,
            ).filter { it.isNotEmpty() }
                .joinToString("\n\n")

            file.writeText(content)
        } else {
            // for functions with union type parameters
            file.appendText("\n\n" + body)
        }
    }
}

private fun hasRuntimeDeclarations(code: String): Boolean {
    if ("\nexternal " !in code && "\nsealed external " !in code)
        return false

    if ("\nsealed external interface " in code)
        return "companion object" in code

    if (code.count("\nexternal ") == code.count("\nexternal interface"))
        return "companion object" in code

    return true
}
