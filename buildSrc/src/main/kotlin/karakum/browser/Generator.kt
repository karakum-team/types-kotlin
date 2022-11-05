package karakum.browser

import karakum.common.GENERATOR_COMMENT
import karakum.common.Suppress
import karakum.common.Suppress.*
import karakum.common.fileSuppress
import java.io.File

private val DEFAULT_IMPORTS = """
import kotlin.js.Date
import kotlin.js.Promise
import kotlinx.js.EpochTimeStamp
import kotlinx.js.ArrayBuffer
import kotlinx.js.ArrayBufferView
import kotlinx.js.BufferSource
import kotlinx.js.Float32Array
import kotlinx.js.HighResTimeStamp
import kotlinx.js.Int32Array
import kotlinx.js.Record    
import kotlinx.js.ReadonlyArray    
import kotlinx.js.Uint32Array
import kotlinx.js.JsLong
import kotlinx.js.Void
import dom.Document
import dom.DocumentFragment
import dom.DOMStringList
import dom.DOMTokenList
import dom.DocumentOrShadowRoot
import dom.Element
import dom.HTMLOrSVGElement
import dom.InnerHTML
import dom.Node
import dom.NodeList
import dom.NodeListOf
import dom.ScrollToOptions
import dom.DocumentAndElementEventHandlers
import dom.GlobalEventHandlers
import dom.html.WindowEventHandlers
import dom.aria.ARIAMixin
import dom.css.LinkStyle
import dom.css.ElementCSSInlineStyle
import dom.events.DataTransfer
import dom.geometry.DOMMatrix
import dom.geometry.DOMMatrix2DInit
import dom.geometry.DOMPoint
import dom.geometry.DOMPointInit
import dom.geometry.DOMPointReadOnly
import dom.geometry.DOMRect
import dom.geometry.DOMRectReadOnly
import dom.geometry.DOMRectList
import dom.html.HTMLCanvasElement
import dom.html.HTMLCollectionOf
import dom.html.HTMLElement
import dom.html.HTMLSlotElement
import dom.html.ShadowRoot
import dom.html.ShadowRootInit
import dom.html.Window
import media.MediaError
import media.key.MediaKeys
import media.source.TimeRanges
import media.stream.MediaStream
import media.stream.MediaStreamTrack
import remoteplayback.RemotePlayback
import web.abort.AbortSignal
import web.animations.Animation
import web.buffer.Blob
import web.events.EventTarget
import web.file.File
import web.file.FileList
import web.filesystem.FileSystemDirectoryHandle
import web.filesystem.FileSystemEntry
import web.http.ReferrerPolicy
import web.locks.LockManager
import web.messaging.Transferable
import web.messaging.StructuredSerializeOptions
import web.permissions.Permissions
import web.share.ShareData
import web.storage.StorageManager
import web.url.URL
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

            if ("companion object" in body && !name.endsWith("Event"))
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

        val finalBody = "import web.events.EventType\n$body"

        targetDir.resolve("$name.kt")
            .also { check(!it.exists()) { "Duplicated file: ${it.name}" } }
            .writeText(fileContent(annotations, "", finalBody, pkg))
    }

    val aliases = domAliases()
        .plus(cssAliases())
        .plus(mediaAliases())
        .plus(htmlAliases())
        .plus(canvasAliases())
        .plus(fileAliases())
        .plus(htmlDeclarations(content))
        .plus(browserConstants(content))
        .plus(browserTypes(content))
        .plus(browserFunctionTypes(content))
        .plus(tagNames(content))

    for ((name, body, pkg) in aliases) {
        pkg!!

        val suppresses = mutableSetOf<Suppress>().apply {
            if ("JsName(\"\"\"(" in body)
                add(NAME_CONTAINS_ILLEGAL_CHARS)

            if (name == "NodeListOf" || name == "HTMLCollectionOf")
                add(UNUSED_TYPEALIAS_PARAMETER)

            if (name == RENDERING_CONTEXT_ID)
                add(NESTED_CLASS_IN_EXTERNAL_INTERFACE)

            if ("inline fun " in body)
                add(NOTHING_TO_INLINE)
        }.toTypedArray()

        val annotations = if (suppresses.isNotEmpty()) {
            fileSuppress(*suppresses)
        } else ""

        val imports = when (name) {
            "Document",
            "DocumentOrShadowRoot",
            -> """
            import cssom.CSSStyleSheet    
            import cssom.StyleSheetList    
            import cssom.fonts.FontFaceSource    
            import dom.html.*        
            import dom.svg.SVGElement    
            import dom.xpath.XPathEvaluatorBase    
            """.trimIndent()

            "HTMLCanvasElement" -> """
            import canvas.*    
            import webgl.*    
            """.trimIndent()

            "Navigator" -> """
            import media.devices.MediaDevices
            import media.key.MediaKeySystemAccess
            import media.key.MediaKeySystemConfiguration
            import serviceworkers.ServiceWorkerContainer
            import web.clipboard.Clipboard       
            import web.geolocation.Geolocation       
            import web.gamepad.Gamepad       
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
