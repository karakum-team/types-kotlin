package karakum.browser

import karakum.common.GENERATOR_COMMENT
import karakum.common.Suppress
import karakum.common.Suppress.*
import karakum.common.fileSuppress
import java.io.File

private val DEFAULT_IMPORTS = """
import kotlin.js.Date
import kotlin.js.Promise
import kotlinx.js.ArrayBufferView
import kotlinx.js.BufferSource
import kotlinx.js.Float32Array
import kotlinx.js.HighResTimeStamp
import kotlinx.js.Int32Array
import kotlinx.js.ReadonlyArray    
import kotlinx.js.Uint32Array
import kotlinx.js.Void
import dom.Document
import dom.DocumentFragment
import dom.DOMTokenList
import dom.Element
import dom.Node
import dom.NodeListOf
import dom.css.LinkStyle
import dom.html.HTMLCanvasElement
import media.MediaError
import media.key.MediaKeys
import media.source.TimeRanges
import media.stream.MediaStream
import remoteplayback.RemotePlayback
import web.buffer.Blob
import web.events.EventTarget
import web.file.FileList
import web.http.ReferrerPolicy
import webvtt.TextTrack
import webvtt.TextTrackKind
import webvtt.TextTrackList
""".trimIndent()

fun generateKotlinDeclarations(
    definitionsFile: File,
    sourceDir: File,
) {
    val content = definitionsFile
        .readText()
        .applyPatches()

    val webglTargetDir = sourceDir
        .resolve("webgl")
        .also { it.mkdirs() }

    for ((name, body, optPkg) in eventDeclarations(content)) {
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
            .writeText(fileContent(annotations, "", finalBody, pkg))
    }

    val aliases = domAliases()
        .plus(cssAliases())
        .plus(mediaAliases())
        .plus(htmlAliases())
        .plus(svgAliases())
        .plus(canvasAliases())
        .plus(fileAliases())
        .plus(htmlDeclarations(content))
        .plus(browserTypes(content))
        .plus(browserFunctionTypes(content))

    for ((name, body, pkg) in aliases) {
        pkg!!

        val suppresses = mutableSetOf<Suppress>().apply {
            if ("JsName(\"\"\"(" in body)
                add(NAME_CONTAINS_ILLEGAL_CHARS)

            if (name == "NodeListOf" || name == "HTMLCollectionOf")
                add(UNUSED_TYPEALIAS_PARAMETER)

            if (name == "CanvasContextId")
                add(NESTED_CLASS_IN_EXTERNAL_INTERFACE)
        }.toTypedArray()

        val annotations = if (suppresses.isNotEmpty()) {
            fileSuppress(*suppresses)
        } else ""

        val imports = when (name) {
            "HTMLCanvasElement" -> """
            import canvas.*    
            import webgl.*    
            """.trimIndent()

            else -> ""
        }

        val targetDir = sourceDir
            .resolve(pkg.replace(".", "/"))
            .also { it.mkdirs() }

        targetDir.resolve("$name.kt")
            .also { check(!it.exists()) { "Duplicated file: ${it.name}" } }
            .writeText(
                fileContent(
                    annotations = annotations,
                    imports = imports,
                    body = body,
                    pkg = pkg,
                )
            )
    }

    for ((name, body) in webglDeclarations(content)) {
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
            .writeText(fileContent(annotations, "", body, "webgl"))
    }
}

private fun fileContent(
    annotations: String = "",
    imports: String = "",
    body: String,
    pkg: String,
): String {
    var result = sequenceOf(
        "// $GENERATOR_COMMENT",
        annotations,
        "package $pkg",
        DEFAULT_IMPORTS,
        imports,
        body,
    ).filter { it.isNotEmpty() }
        .joinToString("\n\n")

    if (!result.endsWith("\n"))
        result += "\n"

    return result
}
