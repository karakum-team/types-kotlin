package karakum.cesium

import karakum.common.Suppress
import karakum.common.suppress
import java.io.File

private var nonModularMode: Boolean = false

val LAZY_MODE: Boolean
    get() = nonModularMode

private const val GENERATOR_COMMENT = "Automatically generated - do not modify!"

private const val MODULE_ANNOTATION: String = "@file:JsModule(\"cesium\")\n@file:JsNonModule"

internal fun generateKotlinDeclarations(
    definitionsFile: File,
    sourceDir: File,
    nonModular: Boolean,
) {
    nonModularMode = nonModular

    val cesiumDir = sourceDir.resolve("cesium")
        .also { it.mkdirs() }

    parseDeclarations(definitionsFile)
        .asSequence()
        .plus(DefaultEvent)
        .plus(CameraOrientation)
        .sortedBy(Declaration::name)
        .forEach { declaration ->
            val file = cesiumDir.resolve("${declaration.name}.kt")
            val code = declaration.toCode()
            if (!file.exists()) {
                val content = "// $GENERATOR_COMMENT\n\n" +
                        if (hasRuntimeDeclarations(code)) {
                            if (LAZY_MODE) {
                                code.addLazyAnnotations()
                            } else {
                                MODULE_ANNOTATION + "\n\n" + code
                            }
                        } else code

                file.writeText(content)
            } else {
                // for functions with union type parameters
                file.appendText("\n\n" + code)
            }
        }
}

private fun hasRuntimeDeclarations(code: String): Boolean {
    if ("\nexternal " !in code)
        return false

    if (code.count("\nexternal ") == code.count("\nexternal interface"))
        return "companion object" in code

    return true
}

private val LAZY_REGEXP = Regex("""\n *external +(.+)\n""")

private fun String.addLazyAnnotations(): String =
    replace(LAZY_REGEXP) {
        val value = it.value
        val name = it.groupValues[1]
            .substringBefore("{")
            .substringBefore("(")
            .substringBefore(":")
            .substringBefore("<")
            .trim()
            .splitToSequence(" ")
            .last()

        sequenceOf(
            suppress(Suppress.NAME_CONTAINS_ILLEGAL_CHARS),
            "@JsName(\"Cesium.$name\")$value",
        ).joinToString("\n", "\n")
    }
