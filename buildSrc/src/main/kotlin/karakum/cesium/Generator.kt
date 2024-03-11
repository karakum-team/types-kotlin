package karakum.cesium

import karakum.common.GENERATOR_COMMENT
import karakum.common.Suppress
import karakum.common.fileSuppress
import java.io.File

private const val MODULE_ANNOTATION: String = """@file:JsModule("cesium")"""

internal fun generateKotlinDeclarations(
    definitionsFile: File,
    sourceDir: File,
) {
    val cesiumDir = sourceDir.resolve("cesium")
        .also { it.mkdirs() }

    parseDeclarations(definitionsFile)
        .asSequence()
        .plus(DefaultEvent)
        .plus(CameraOrientation)
        .sortedBy(Declaration::name)
        .forEach { declaration ->
            val file = cesiumDir.resolve("${declaration.name}.kt")
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
