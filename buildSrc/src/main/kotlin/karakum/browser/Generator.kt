package karakum.browser

import karakum.common.GENERATOR_COMMENT
import karakum.common.Suppress
import karakum.common.Suppress.*
import karakum.common.fileSuppress
import java.io.File

private val DEFAULT_IMPORTS = Imports(
    "js.buffer.AllowSharedBufferSource",
    "js.buffer.ArrayBuffer",
    "js.buffer.ArrayBufferLike",
    "js.buffer.ArrayBufferView",
    "js.buffer.BufferSource",
    "js.collections.AsyncMapLike",
    "js.collections.JsSet",
    "js.collections.ListLike",
    "js.collections.MapLike",
    "js.collections.ReadonlyMap",
    "js.core.ArrayLike",
    "js.core.BigInt",
    "js.core.EpochTimeStamp",
    "js.core.DOMHighResTimeStamp",
    "js.iterable.IterableIterator",
    "js.iterable.JsIterable",
    "js.core.JsLong",
    "js.core.JsTuple2",
    "js.core.ReadonlyArray",
    "js.core.ReadonlyRecord",
    "js.core.Record",
    "js.core.Void",
    "js.errors.JsError",
    "js.promise.Promise",
    "js.promise.PromiseLike",
    "js.typedarrays.Float32Array",
    "js.typedarrays.Float64Array",
    "js.typedarrays.Int32Array",
    "js.typedarrays.Uint32Array",
    "js.typedarrays.Uint8Array",
    "js.typedarrays.Uint8ClampedArray",

    "kotlin.js.Date",

    "seskar.js.JsIntValue",
    "seskar.js.JsUnion",
    "seskar.js.JsValue",

    "web.abort.AbortSignal",
    "web.animations.Animation",
    "web.aria.ARIAMixin",
    "web.buffer.Blob",
    "web.buffer.BlobPart",
    "web.buffer.BlobPropertyBag",
    "web.canvas.CanvasImageSource",
    "web.canvas.OffscreenCanvas",
    "web.canvas.PredefinedColorSpace",
    "web.crypto.Algorithm",
    "web.cssom.CSSStyleDeclaration",
    "web.cssom.CSSStyleSheet",
    "web.cssom.ElementCSSInlineStyle",
    "web.cssom.LinkStyle",
    "web.cssom.MediaQuery",
    "web.cssom.StylePropertyMapReadOnly",
    "web.data.DataTransfer",
    "web.dom.DOMStringList",
    "web.dom.DOMTokenList",
    "web.dom.Document",
    "web.dom.DocumentFragment",
    "web.dom.DocumentOrShadowRoot",
    "web.dom.DocumentVisibilityState",
    "web.dom.Element",
    "web.dom.ElementNamespace",
    "web.dom.GlobalEventHandlers",
    "web.dom.HTMLOrSVGElement",
    "web.dom.InnerHTML",
    "web.dom.Node",
    "web.dom.NodeList",
    "web.dom.NodeListOf",
    "web.dom.Range",
    "web.errors.DOMException",
    "web.events.Event",
    "web.events.EventHandler",
    "web.events.EventInit",
    "web.events.EventTarget",
    "web.events.EventType",
    "web.file.File",
    "web.file.FileList",
    "web.filesystem.FileSystemDirectoryHandle",
    "web.filesystem.FileSystemEntry",
    "web.fullscreen.FullscreenOptions",
    "web.geometry.DOMMatrix",
    "web.geometry.DOMMatrix2DInit",
    "web.geometry.DOMMatrixReadOnly",
    "web.geometry.DOMPoint",
    "web.geometry.DOMPointInit",
    "web.geometry.DOMPointReadOnly",
    "web.geometry.DOMRect",
    "web.geometry.DOMRectInit",
    "web.geometry.DOMRectList",
    "web.geometry.DOMRectReadOnly",
    "web.html.HTMLCanvasElement",
    "web.html.HTMLCollection",
    "web.html.HTMLCollectionOf",
    "web.html.HTMLElement",
    "web.html.HTMLMediaElement",
    "web.html.HTMLSlotElement",
    "web.html.HtmlTagName",
    "web.html.ShadowRoot",
    "web.html.ShadowRootInit",
    "web.http.BodyInit",
    "web.http.CrossOrigin",
    "web.http.FormData",
    "web.http.ReferrerPolicy",
    "web.http.Request",
    "web.http.RequestCredentials",
    "web.http.Response",
    "web.keyboard.KeyCode",
    "web.keyboard.ModifierKeyCode",
    "web.locks.LockManager",
    "web.mathml.MATHML_NAMESPACE",
    "web.mathml.MathMLElement",
    "web.mathml.MathMLTagName",
    "web.media.capabilities.MediaCapabilities",
    "web.media.key.MediaKeys",
    "web.media.session.MediaSession",
    "web.media.source.TimeRanges",
    "web.media.streams.MediaStream",
    "web.media.streams.MediaStreamConstraints",
    "web.media.streams.MediaStreamTrack",
    "web.media.streams.MediaTrackCapabilities",
    "web.media.streams.MediaTrackConstraints",
    "web.media.streams.MediaTrackSupportedConstraints",
    "web.messaging.MessagePort",
    "web.messaging.StructuredSerializeOptions",
    "web.messaging.Transferable",
    "web.notifications.Notification",
    "web.notifications.NotificationOptions",
    "web.permissions.PermissionState",
    "web.permissions.Permissions",
    "web.push.PushManager",
    "web.remoteplayback.RemotePlayback",
    "web.scroll.ScrollIntoViewOptions",
    "web.scroll.ScrollToOptions",
    "web.selection.Selection",
    "web.share.ShareData",
    "web.storage.StorageManager",
    "web.streams.GenericTransformStream",
    "web.streams.ReadableStream",
    "web.streams.WritableStream",
    "web.svg.SVGElement",
    "web.svg.SVG_NAMESPACE",
    "web.svg.SvgTagName",
    "web.url.URL",
    "web.vibration.VibratePattern",
    "web.window.PictureInPictureWindow",
    "web.window.Window",
    "web.window.WindowEventHandlers",
    "web.window.WindowName",
    "web.window.WindowProxy",
    "web.window.WindowTarget",
    "web.workers.AbstractWorker",
    "web.workers.Worker",
    "web.workers.WorkerType",
    "web.worklets.Worklet",
    "web.xml.XMLDocument",
    "websockets.BinaryType",
    "webvtt.TextTrack",
    "webvtt.TextTrackKind",
    "webvtt.TextTrackList",
)

fun generateKotlinDeclarations(
    definitionsDir: File,
    webDefinitionsFile: File,
    webIterableDefinitionsFile: File,
    serviceworkerDefinitionsFile: File,
    serviceworkerIterableDefinitionsFile: File,
    sourceDir: File,
) {
    IterableRegistry.fill(
        definitionsDir,
        webIterableDefinitionsFile,
        serviceworkerIterableDefinitionsFile,
    )

    val content = webDefinitionsFile
        .readText()
        .applyPatches()

    val webglTargetDir = sourceDir
        .resolve("webgl")
        .also { it.mkdirs() }

    val webWorkersContent = webWorkersContent(serviceworkerDefinitionsFile)
    for ((name, body, optPkg) in (eventDeclarations(content, webWorkersContent) + workerEventDeclarations(content, webWorkersContent))) {
        val suppresses = mutableSetOf<Suppress>().apply {
            if ("override val type: EventType<" in body)
                add(EXTERNAL_CLASS_CONSTRUCTOR_PROPERTY_PARAMETER)

            if (name == "TouchEvent")
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
        .plus(cssObject(content))
        .plus(keyboardTypes())
        .plus(windowTypes())
        .plus(tagNames(content))
        .plus(intlDeclarations(definitionsDir))
        .plus(webAssemblyDeclarations(content))
        .plus(webWorkersDeclarations(serviceworkerDefinitionsFile))

    for ((name, body, pkg) in aliases) {
        pkg!!

        val suppresses = mutableSetOf<Suppress>().apply {
            if ("override val type: EventType<" in body)
                add(EXTERNAL_CLASS_CONSTRUCTOR_PROPERTY_PARAMETER)

            if ("@JsValue(" in body && "companion object" in body)
                add(NESTED_CLASS_IN_EXTERNAL_INTERFACE)

            if ("inline fun " in body)
                add(NOTHING_TO_INLINE)

            if (name == "Locale")
                add(VIRTUAL_MEMBER_HIDDEN)

            if (name in Mixins.UNSAFE)
                add(NON_ABSTRACT_MEMBER_OF_EXTERNAL_INTERFACE)

            if (name == "ReadableStreamReadResult")
                add(UNUSED_TYPEALIAS_PARAMETER)

            if (name == "ChildNode" || name == "ParentNode" || name == "AbstractWorker")
                add(INTERFACE_WITH_SUPERCLASS)

            if (name == "WorkerNavigator"
                || name == "CompressionStream"
                || name == "DecompressionStream"
                || name == "TextEncoderStream"
                || name == "TextDecoderStream"
            )
                add(SEALED_INHERITOR_IN_DIFFERENT_PACKAGE)
        }.toTypedArray()

        val annotations = sequenceOf(
            if (suppresses.isNotEmpty()) {
                fileSuppress(*suppresses)
            } else "",
            when {
                pkg == "js.intl" && "external class " in body
                -> """@file:JsQualifier("Intl")"""

                pkg == "webassembly" && ("external class " in body || "external fun " in body)
                -> """@file:JsQualifier("WebAssembly")"""

                else -> ""
            },
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
            import webgl.*
            """.trimIndent()

            "Navigator" -> """
            import web.media.devices.MediaDevices
            import web.media.key.MediaKeySystemAccess
            import web.media.key.MediaKeySystemConfiguration
            import web.serviceworker.ServiceWorkerContainer
            import web.clipboard.Clipboard       
            import web.credentials.CredentialsContainer       
            import web.geolocation.Geolocation       
            import web.gamepad.Gamepad
            import web.midi.MIDIOptions
            import web.midi.MIDIAccess
            import web.useractivation.UserActivation
            import web.wakelock.WakeLock
            """.trimIndent()

            "CredentialCreationOptions",
            "CredentialRequestOptions",
            -> """
            import web.authn.PublicKeyCredentialCreationOptions
            import web.authn.PublicKeyCredentialRequestOptions    
            """.trimIndent()

            "PublicKeyCredential",
            -> """
            import web.credentials.Credential    
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
            if ("@JsUnion" in body) {
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
        DEFAULT_IMPORTS.forContent(body),
        imports,
        body,
    ).filter { it.isNotEmpty() }
        .joinToString("\n\n")

    if (!result.endsWith("\n"))
        result += "\n"

    return result
}
