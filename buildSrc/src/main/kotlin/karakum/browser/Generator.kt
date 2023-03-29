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
import js.buffer.ArrayBufferLike
import js.buffer.ArrayBufferView
import js.buffer.BufferSource
import js.typedarrays.Float32Array
import js.typedarrays.Float64Array
import js.core.HighResTimeStamp
import js.typedarrays.Int32Array
import js.collections.JsSet
import js.collections.ReadonlyMap
import js.core.Record
import js.core.ReadonlyArray    
import js.core.ReadonlyRecord    
import js.typedarrays.Uint8Array
import js.typedarrays.Uint32Array
import js.typedarrays.Uint8ClampedArray
import js.core.JsLong
import js.core.Void
import web.canvas.CanvasImageSource
import web.canvas.OffscreenCanvas
import web.canvas.PredefinedColorSpace
import web.cssom.CSSStyleDeclaration
import web.cssom.CSSStyleSheet
import web.dom.Document
import web.dom.DocumentFragment
import web.dom.DOMStringList
import web.dom.DOMTokenList
import web.dom.DocumentOrShadowRoot
import web.dom.DocumentVisibilityState
import web.dom.Element
import web.dom.ElementNamespace
import web.dom.HTMLOrSVGElement
import web.dom.InnerHTML
import web.dom.Node
import web.dom.NodeList
import web.dom.NodeListOf
import web.dom.Range
import web.scroll.ScrollIntoViewOptions
import web.scroll.ScrollToOptions
import web.dom.GlobalEventHandlers
import web.window.WindowEventHandlers
import web.window.WindowName
import web.aria.ARIAMixin
import web.cssom.LinkStyle
import web.cssom.ElementCSSInlineStyle
import web.cssom.StylePropertyMapReadOnly
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
import web.fullscreen.FullscreenOptions
import web.html.HTMLCanvasElement
import web.html.HTMLCollectionOf
import web.html.HTMLCollection
import web.html.HTMLElement
import web.html.HTMLMediaElement
import web.html.HTMLSlotElement
import web.html.HtmlTagName
import web.html.ShadowRoot
import web.html.ShadowRootInit
import web.keyboard.KeyCode
import web.keyboard.ModifierKeyCode
import web.window.PictureInPictureWindow
import web.window.Window
import web.window.WindowProxy
import web.window.WindowTarget
import web.selection.Selection
import web.svg.SVG_NAMESPACE
import web.svg.SVGElement
import web.svg.SvgTagName
import web.mathml.MATHML_NAMESPACE
import web.mathml.MathMLElement
import web.mathml.MathMLTagName
import web.xml.XMLDocument
import web.media.capabilities.MediaCapabilities
import web.media.session.MediaSession
import web.media.key.MediaKeys
import web.media.source.TimeRanges
import web.media.streams.MediaStream
import web.media.streams.MediaStreamConstraints
import web.media.streams.MediaStreamTrack
import web.media.streams.MediaTrackConstraints
import web.media.streams.MediaTrackSupportedConstraints
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
    webDefinitionsFile: File,
    serviceworkerDefinitionsFile: File,
    sourceDir: File,
) {
    IterableRegistry.fill(
        definitionsDir,
        webDefinitionsFile.parentFile.resolve("iterable.d.ts"),
        serviceworkerDefinitionsFile.parentFile.resolve("iterable.d.ts"),
    )

    val content = webDefinitionsFile
        .readText()
        .applyPatches()

    val webglTargetDir = sourceDir
        .resolve("webgl")
        .also { it.mkdirs() }

    for ((name, body, optPkg) in eventDeclarations(content, webWorkersContent(serviceworkerDefinitionsFile))) {
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
        .plus(htmlUnions())
        .plus(browserConstants(content))
        .plus(browserTypes(content))
        .plus(browserFunctions(content))
        .plus(browserFunctionTypes(content))
        .plus(keyboardTypes())
        .plus(windowTypes())
        .plus(tagNames(content))
        .plus(intlDeclarations(definitionsDir))
        .plus(webWorkersDeclarations(serviceworkerDefinitionsFile))

    for ((name, body, pkg) in aliases) {
        pkg!!

        val suppresses = mutableSetOf<Suppress>().apply {
            if ("override val type: EventType<" in body)
                add(EXTERNAL_CLASS_CONSTRUCTOR_PROPERTY_PARAMETER)

            if ("JsName(\"\"\"(" in body || "JsName(\"'" in body)
                add(NAME_CONTAINS_ILLEGAL_CHARS)

            if ("JsName(\"\"\"(" in body)
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

            "OffscreenCanvas" -> """  
            import webgl.WebGLRenderingContext
            import webgl.WebGL2RenderingContext
            """.trimIndent()

            "Navigator" -> """
            import web.media.devices.MediaDevices
            import web.media.key.MediaKeySystemAccess
            import web.media.key.MediaKeySystemConfiguration
            import web.serviceworker.ServiceWorkerContainer
            import web.clipboard.Clipboard       
            import web.geolocation.Geolocation       
            import web.gamepad.Gamepad
            import web.midi.MIDIOptions
            import web.midi.MIDIAccess
            import web.useractivation.UserActivation
            import web.wakelock.WakeLock
            """.trimIndent()

            "Window",
            -> """
            import web.device.DeviceMotionEvent    
            import web.device.DeviceOrientationEvent    
            """.trimIndent()

            "GlobalEventHandlers",
            "WindowEventHandlers",
            "FileReader",
            -> """
            import web.cssom.TransitionEvent
            import web.html.FormDataEvent
            import web.html.SubmitEvent
            import web.uievents.*
            import web.animations.AnimationEvent
            import web.clipboard.ClipboardEvent
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
            "WebSocket",
            -> """
            import web.events.IEventTarget
            import web.errors.ErrorEvent
            import web.messaging.MessageEvent
            """.trimIndent()

            "DedicatedWorkerGlobalScope",
            "WorkerGlobalScope",
            -> """
            import web.fonts.FontFaceSource    
            import web.window.PromiseRejectionEvent
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
            if ("JsName(\"\"\"(" in body) {
                add(NAME_CONTAINS_ILLEGAL_CHARS)
                add(NESTED_CLASS_IN_EXTERNAL_INTERFACE)
            }
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
