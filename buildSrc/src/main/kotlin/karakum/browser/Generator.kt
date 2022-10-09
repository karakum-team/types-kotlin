package karakum.browser

import karakum.common.GENERATOR_COMMENT
import karakum.common.Suppress
import karakum.common.Suppress.*
import karakum.common.fileSuppress
import java.io.File

private val DEFAULT_IMPORTS = """
import kotlin.js.Promise
import kotlinx.js.ArrayBufferView
import kotlinx.js.BufferSource
import kotlinx.js.Float32Array
import kotlinx.js.Int32Array
import kotlinx.js.ReadonlyArray    
import kotlinx.js.Uint32Array
import dom.Document
import dom.DocumentFragment
import dom.DOMTokenList
import dom.NodeListOf
import dom.html.HTMLCanvasElement
""".trimIndent()

fun generateKotlinDeclarations(
    definitionsFile: File,
    sourceDir: File,
) {
    val webglTargetDir = sourceDir
        .resolve("webgl")
        .also { it.mkdirs() }

    for ((name, body, optPkg) in eventDeclarations(definitionsFile)) {
        val suppresses = mutableSetOf<Suppress>().apply {
            if ("JsName(\"\"\"(" in body)
                add(NAME_CONTAINS_ILLEGAL_CHARS)

            if ("companion object" in body)
                add(NESTED_CLASS_IN_EXTERNAL_INTERFACE)

            if ("inline fun " in body)
                add(NOTHING_TO_INLINE)
        }.toTypedArray()

        val annotations = if (suppresses.isNotEmpty()) {
            fileSuppress(*suppresses)
        } else ""

        val pkg = optPkg ?: EVENT_INFO_MAP.getValue(name.substringBefore("."))
            .fqn
            .substringBeforeLast(".")

        val targetDir = sourceDir
            .resolve(pkg.replace(".", "/"))
            .also { it.mkdirs() }

        val finalBody = when (name) {
            "AnimationEvent",
            "TransitionEvent",
            -> "import web.events.Event\n"

            else -> ""
        } + "import web.events.EventType\n" +
                body

        targetDir.resolve("$name.kt")
            .also { check(!it.exists()) { "Duplicated file: ${it.name}" } }
            .writeText(fileContent(annotations, finalBody, pkg))
    }

    val aliases = domAliases()
        .plus(htmlAliases())
        .plus(svgAliases())
        .plus(canvasAliases())
        .plus(htmlDeclarations(definitionsFile))

    for ((name, body, pkg) in aliases) {
        pkg!!

        val targetDir = sourceDir
            .resolve(pkg.replace(".", "/"))
            .also { it.mkdirs() }

        targetDir.resolve("$name.kt")
            .also { check(!it.exists()) { "Duplicated file: ${it.name}" } }
            .writeText(fileContent(body = body, pkg = pkg))
    }

    for ((name, body) in webglDeclarations(definitionsFile)) {
        val suppresses = mutableSetOf<Suppress>().apply {
            if ("JsName(\"\"\"(" in body)
                add(NAME_CONTAINS_ILLEGAL_CHARS)

            if (name == "WebGLExtension")
                add(NESTED_CLASS_IN_EXTERNAL_INTERFACE)
        }.toTypedArray()

        val annotations = if (suppresses.isNotEmpty()) {
            fileSuppress(*suppresses)
        } else ""

        webglTargetDir.resolve("$name.kt")
            .also { check(!it.exists()) { "Duplicated file: ${it.name}" } }
            .writeText(fileContent(annotations, body, "webgl"))
    }
}

private fun fileContent(
    annotations: String = "",
    body: String,
    pkg: String,
): String {
    var result = sequenceOf(
        "// $GENERATOR_COMMENT",
        annotations,
        "package $pkg",
        DEFAULT_IMPORTS,
        body,
    ).filter { it.isNotEmpty() }
        .joinToString("\n\n")

    if (!result.endsWith("\n"))
        result += "\n"

    return result
}
