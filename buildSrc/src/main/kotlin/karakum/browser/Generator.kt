package karakum.browser

import karakum.common.GENERATOR_COMMENT
import karakum.common.Suppress
import karakum.common.Suppress.*
import karakum.common.fileSuppress
import java.io.File

private val DEFAULT_IMPORTS = """
import kotlin.js.Date
import kotlin.js.Promise
import js.core.ArrayLike
import js.core.BigInt
import js.core.JsIterable
import js.core.EpochTimeStamp
import js.buffer.ArrayBuffer
import js.buffer.ArrayBufferView
import js.buffer.BufferSource
import js.typedarrays.Float32Array
import js.typedarrays.Float64Array
import js.core.HighResTimeStamp
import js.typedarrays.Int32Array
import js.core.Record    
import js.core.ReadonlyArray    
import js.typedarrays.Uint8Array
import js.typedarrays.Uint32Array
import js.typedarrays.Uint8ClampedArray
import js.core.JsLong
import js.core.Void
import web.canvas.OffscreenCanvas
import web.cssom.CSSStyleDeclaration
import web.cssom.CSSStyleSheet
import dom.Document
import dom.DocumentFragment
import dom.DOMStringList
import dom.DOMTokenList
import dom.DocumentOrShadowRoot
import dom.DocumentVisibilityState
import dom.Element
import dom.HTMLOrSVGElement
import dom.InnerHTML
import dom.Node
import dom.NodeList
import dom.NodeListOf
import dom.Range
import dom.ScrollToOptions
import dom.DocumentAndElementEventHandlers
import dom.GlobalEventHandlers
import web.html.WindowEventHandlers
import web.aria.ARIAMixin
import web.dom.css.LinkStyle
import web.dom.css.ElementCSSInlineStyle
import web.data.DataTransfer
import web.geometry.DOMMatrix
import web.geometry.DOMMatrixReadOnly
import web.geometry.DOMMatrix2DInit
import web.geometry.DOMPoint
import web.geometry.DOMPointInit
import web.geometry.DOMPointReadOnly
import web.geometry.DOMRect
import web.geometry.DOMRectInit
import web.geometry.DOMRectReadOnly
import web.geometry.DOMRectList
import web.html.HTMLCanvasElement
import web.html.HTMLCollectionOf
import web.html.HTMLCollection
import web.html.HTMLElement
import web.html.HTMLMediaElement
import web.html.HTMLSlotElement
import web.html.HtmlTagName
import web.html.ShadowRoot
import web.html.ShadowRootInit
import web.html.Window
import web.selection.Selection
import web.svg.SVGElement
import web.svg.SvgTagName
import web.xml.XMLDocument
import media.capabilities.MediaCapabilities
import media.session.MediaSession
import media.key.MediaKeys
import media.source.TimeRanges
import media.streams.MediaStream
import media.streams.MediaStreamConstraints
import media.streams.MediaStreamTrack
import media.streams.MediaTrackConstraints
import media.streams.MediaTrackSupportedConstraints
import web.remoteplayback.RemotePlayback
import web.abort.AbortSignal
import web.animations.Animation
import web.buffer.Blob
import web.buffer.BlobPart
import web.buffer.BlobPropertyBag
import web.crypto.Algorithm
import web.events.Event
import web.events.EventInit
import web.events.EventType
import web.events.EventHandler
import web.events.EventTarget
import web.errors.DOMException
import web.file.File
import web.file.FileList
import web.filesystem.FileSystemDirectoryHandle
import web.filesystem.FileSystemEntry
import web.http.BodyInit
import web.http.FormData
import web.http.ReferrerPolicy
import web.http.Request
import web.http.RequestCredentials
import web.http.Response
import web.locks.LockManager
import web.messaging.MessagePort
import web.messaging.Transferable
import web.messaging.StructuredSerializeOptions
import web.notifications.Notification
import web.notifications.NotificationOptions
import web.permissions.Permissions
import web.permissions.PermissionState
import web.push.PushManager
import web.share.ShareData
import web.storage.StorageManager
import web.streams.ReadableStream
import web.streams.WritableStream
import web.url.URL
import web.vibration.VibratePattern
import web.workers.AbstractWorker
import web.workers.WorkerType
import web.worklets.Worklet
import websockets.BinaryType
import webvtt.TextTrack
import webvtt.TextTrackKind
import webvtt.TextTrackList
""".trimIndent()

fun generateKotlinDeclarations(
    definitionsDir: File,
    sourceDir: File,
) {
    IterableRegistry.fill(definitionsDir)

    val definitionsFile = definitionsDir
        .resolve("lib.dom.d.ts")

    val content = definitionsFile
        .readText()
        .applyPatches()

    val webglTargetDir = sourceDir
        .resolve("webgl")
        .also { it.mkdirs() }

    for ((name, body, optPkg) in eventDeclarations(content, webWorkersContent(definitionsDir))) {
        val suppresses = mutableSetOf<Suppress>().apply {
            if ("override val type: EventType<" in body)
                add(EXTERNAL_CLASS_CONSTRUCTOR_PROPERTY_PARAMETER)

            if ("JsName(\"\"\"(" in body || name == "TouchEvent")
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

        targetDir.resolve("$name.kt")
            .also { check(!it.exists()) { "Duplicated file: ${it.name}" } }
            .writeText(fileContent(annotations, "", body, pkg))
    }

    val aliases = domAliases()
        .plus(htmlFactories(content))
        .plus(htmlDeclarations(content))
        .plus(browserConstants(content))
        .plus(browserTypes(content))
        .plus(browserFunctionTypes(content))
        .plus(tagNames(content))
        .plus(intlDeclarations(definitionsDir))
        .plus(webWorkersDeclarations(definitionsDir))

    for ((name, body, pkg) in aliases) {
        pkg!!

        val suppresses = mutableSetOf<Suppress>().apply {
            if ("override val type: EventType<" in body)
                add(EXTERNAL_CLASS_CONSTRUCTOR_PROPERTY_PARAMETER)

            if ("JsName(\"\"\"(" in body)
                add(NAME_CONTAINS_ILLEGAL_CHARS)

            if (name == RENDERING_CONTEXT_ID || name == "KeyFormat")
                add(NESTED_CLASS_IN_EXTERNAL_INTERFACE)

            if ("inline fun " in body)
                add(NOTHING_TO_INLINE)

            if (name == "Locale")
                add(VIRTUAL_MEMBER_HIDDEN)

            // TEMP Remove HTML classes fix
            if (name in HTML_FACTORIES)
                add(ABSTRACT_CLASS_MEMBER_NOT_IMPLEMENTED)

            // TEMP Remove after inheritance fix
            if (name == "TextDecoder" || name == "TextDecoderStream")
                add(ABSTRACT_MEMBER_NOT_IMPLEMENTED)

            if (name == "WorkerNavigator")
                add(SEALED_INHERITOR_IN_DIFFERENT_PACKAGE)
        }.toTypedArray()

        val annotations = sequenceOf(
            if (suppresses.isNotEmpty()) {
                fileSuppress(*suppresses)
            } else "",
            if (pkg == "js.intl" && "external class" in body) """@file:JsQualifier("Intl")""" else "",
        ).filter { it.isNotEmpty() }
            .joinToString("\n\n")


        val imports = when (name) {
            "Document",
            "DocumentOrShadowRoot",
            -> """
            import web.cssom.StyleSheetList    
            import web.fonts.FontFaceSource    
            import web.html.*        
            import web.xpath.XPathEvaluatorBase
            """.trimIndent()

            "HTMLCanvasElement" -> """
            import web.canvas.*    
            import webgl.*    
            """.trimIndent()

            "Navigator" -> """
            import media.devices.MediaDevices
            import media.key.MediaKeySystemAccess
            import media.key.MediaKeySystemConfiguration
            import web.serviceworker.ServiceWorkerContainer
            import web.clipboard.Clipboard       
            import web.geolocation.Geolocation       
            import web.gamepad.Gamepad       
            """.trimIndent()

            "DocumentAndElementEventHandlers",
            -> """
            import web.clipboard.ClipboardEvent
            """.trimIndent()

            "Window",
            -> """
            import web.device.DeviceMotionEvent    
            import web.device.DeviceOrientationEvent    
            """.trimIndent()

            "HTMLMediaElement",
            -> """
            import dom.events.MediaEncryptedEvent    
            """.trimIndent()

            "GlobalEventHandlers",
            "WindowEventHandlers",
            "FileReader",
            -> """
            import dom.events.*
            import web.animations.AnimationEvent
            import web.csp.SecurityPolicyViolationEvent
            import web.events.ProgressEvent
            import web.gamepad.GamepadEvent
            import web.history.HashChangeEvent
            import web.history.PageTransitionEvent
            import web.history.PopStateEvent
            import web.messaging.MessageEvent
            import web.storage.StorageEvent
            """.trimIndent()

            "EventSource",
            "XMLHttpRequestEventTarget",
            "RTCDataChannel",
            "ServiceWorkerContainer",
            "BroadcastChannel",
            -> """
            import web.events.ProgressEvent
            import web.messaging.MessageEvent
            """.trimIndent()

            "AbstractWorker",
            "Worker",
            -> """
            import web.events.IEventTarget
            import web.errors.ErrorEvent
            import web.messaging.MessageEvent
            """.trimIndent()

            "DedicatedWorkerGlobalScope",
            "WorkerGlobalScope",
            -> """
            import web.fonts.FontFaceSource    
            import dom.events.PromiseRejectionEvent
            import web.errors.ErrorEvent
            import web.messaging.MessageEvent
            """.trimIndent()

            "WorkerNavigator",
            -> """
            import web.navigator.*
            import web.messaging.MessageEvent
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
