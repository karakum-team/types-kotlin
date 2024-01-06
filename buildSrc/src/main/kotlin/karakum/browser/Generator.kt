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
    "js.collections.MutableMapLike",
    "js.collections.MutableSetLike",
    "js.collections.ReadonlyMap",
    "js.core.ArrayLike",
    "js.core.BigInt",
    "js.core.Bitmask",
    "js.core.JsLong",
    "js.core.JsTuple2",
    "js.array.ReadonlyArray",
    "js.core.ReadonlyRecord",
    "js.core.Record",
    "js.core.StructuredSerializeOptions",
    "js.core.Transferable",
    "js.core.Void",
    "js.errors.JsError",
    "js.errors.JsErrorName",
    "js.iterable.IterableIterator",
    "js.iterable.JsIterable",
    "js.promise.Promise",
    "js.promise.PromiseLike",
    "js.typedarrays.BigInt64Array",
    "js.typedarrays.Float32Array",
    "js.typedarrays.Float64Array",
    "js.typedarrays.Int32Array",
    "js.typedarrays.TypedArray",
    "js.typedarrays.Uint32Array",
    "js.typedarrays.Uint8Array",
    "js.typedarrays.Uint8ClampedArray",

    "kotlin.js.Date",

    "seskar.js.JsIntValue",
    "seskar.js.JsTypeGuard",
    "seskar.js.JsValue",
    "seskar.js.JsVirtual",

    "web.abort.AbortSignal",
    "web.animations.Animation",
    "web.animations.DocumentTimeline",
    "web.animations.Keyframe",
    "web.animations.KeyframeAnimationOptions",
    "web.animations.PropertyIndexedKeyframes",
    "web.aria.ARIAMixin",
    "web.autofill.AutoFill",
    "web.autofill.AutoFillBase",
    "web.buffer.Blob",
    "web.buffer.BlobPart",
    "web.buffer.BlobPropertyBag",
    "web.canvas.CanvasImageSource",
    "web.canvas.OffscreenCanvas",
    "web.canvas.PredefinedColorSpace",
    "web.components.CustomElementCallbacks",
    "web.components.ElementInternals",
    "web.components.HTMLSlotElement",
    "web.components.HTMLTemplateElement",
    "web.components.ShadowRoot",
    "web.components.ShadowRootInit",
    "web.components.Slottable",
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
    "web.dom.HTMLOrSVGImageElement",
    "web.dom.HTMLOrSVGScriptElement",
    "web.dom.InnerHTML",
    "web.dom.Node",
    "web.dom.NodeList",
    "web.dom.NodeListOf",
    "web.dom.ParentNode",
    "web.errors.DOMException",
    "web.errors.ErrorEvent",
    "web.events.Event",
    "web.events.EventHandler",
    "web.events.EventInit",
    "web.events.EventTarget",
    "web.events.EventType",
    "web.file.File",
    "web.file.FileList",
    "web.fs.FileSystemDirectoryHandle",
    "web.fs.FileSystemEntry",
    "web.form.FormControl",
    "web.form.FormData",
    "web.form.FormDataEvent",
    "web.form.SubmitEvent",
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
    "web.gl.TexImageSource",
    "web.highlight.HighlightRegistry",
    "web.html.HTMLCanvasElement",
    "web.html.HTMLCollection",
    "web.html.HTMLCollectionOf",
    "web.html.HTMLElement",
    "web.html.HTMLFormElement",
    "web.html.HTMLLabelElement",
    "web.html.HTMLMediaElement",
    "web.html.HtmlTagName",
    "web.http.BodyInit",
    "web.http.CrossOrigin",
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
    "web.messaging.MessageEvent",
    "web.messaging.MessageEventSource",
    "web.messaging.MessagePort",
    "web.notifications.Notification",
    "web.notifications.NotificationOptions",
    "web.permissions.PermissionState",
    "web.permissions.Permissions",
    "web.pip.PictureInPictureEvent",
    "web.pip.PictureInPictureWindow",
    "web.push.PushManager",
    "web.ranges.AbstractRange",
    "web.ranges.Range",
    "web.ranges.StaticRange",
    "web.remoteplayback.RemotePlayback",
    "web.rendering.OffscreenRenderingContext",
    "web.rendering.RenderingContext",
    "web.rendering.RenderingContextId",
    "web.scroll.ScrollIntoViewOptions",
    "web.scroll.ScrollToOptions",
    "web.selection.Selection",
    "web.serviceworker.ExtendableEvent",
    "web.serviceworker.ExtendableEventInit",
    "web.share.ShareData",
    "web.sockets.BinaryType",
    "web.storage.StorageManager",
    "web.streams.GenericTransformStream",
    "web.streams.ReadableStream",
    "web.streams.WritableStream",
    "web.svg.SVGElement",
    "web.svg.SVG_NAMESPACE",
    "web.svg.SvgTagName",
    "web.time.DOMHighResTimeStamp",
    "web.time.EpochTimeStamp",
    "web.url.URL",
    "web.url.URLSearchParams",
    "web.validation.ValidationTarget",
    "web.validation.ValidityState",
    "web.validation.ValidityStateFlags",
    "web.vibration.VibratePattern",
    "web.vtt.TextTrack",
    "web.vtt.TextTrackKind",
    "web.vtt.TextTrackList",
    "web.window.Window",
    "web.window.WindowEventHandlers",
    "web.window.WindowName",
    "web.window.WindowProxy",
    "web.window.WindowTarget",
    "web.workers.AbstractWorker",
    "web.workers.Worker",
    "web.workers.WorkerType",
    "web.worklets.Worklet",
    "web.worklets.WorkletGlobalScope",
    "web.xml.XMLDocument",
)

fun generateKotlinDeclarations(
    idlDir: File,
    definitionsDir: File,
    webDefinitionsFile: File,
    webIterableDefinitionsFile: File,
    serviceworkerDefinitionsFile: File,
    serviceworkerIterableDefinitionsFile: File,
    audioWorkletDefinitionsFile: File,
    sourceDir: File,
) {
    IDLRegistry.rootDirectory = idlDir

    IterableRegistry.fill(
        definitionsDir,
        webIterableDefinitionsFile,
        serviceworkerIterableDefinitionsFile,
    )

    MarkerRegistry.fill(webDefinitionsFile)
    RenderingContextRegistry.fill(webDefinitionsFile)

    val content = webDefinitionsFile
        .readText()
        .applyPatches()

    val webglTargetDir = sourceDir
        .resolve("web/gl")
        .also { it.mkdirs() }

    val serviceWorkersContent = serviceWorkersContent(serviceworkerDefinitionsFile)
    val eventDeclarations = eventDeclarations(content, WEB_WORKER_CONTENT, serviceWorkersContent) +
            webWorkersEventDeclarations(WEB_WORKER_CONTENT) +
            serviceWorkersEventDeclarations(content, serviceWorkersContent)

    for ((name, body, optPkg) in eventDeclarations) {
        val suppresses = mutableSetOf<Suppress>().apply {
            if ("val type: EventType<" in body)
                add(EXTERNAL_CLASS_CONSTRUCTOR_PROPERTY_PARAMETER)

            if ("companion object" in body && !name.endsWith("Event"))
                add(NESTED_CLASS_IN_EXTERNAL_INTERFACE)

            if (name.endsWith("Event.types") && " = definedExternally" in body)
                add(NON_ABSTRACT_MEMBER_OF_EXTERNAL_INTERFACE)

            if ("inline fun " in body)
                add(NOTHING_TO_INLINE)
        }.toTypedArray()

        var annotations = if (suppresses.isNotEmpty()) {
            fileSuppress(suppresses = suppresses)
        } else ""

        if (name == "TouchEvent") {
            annotations = """@file:JsQualifier("globalThis")""" + "\n\n" + annotations
        }

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
        .plus(mouseButtonTypes())
        .plus(windowTypes())
        .plus(tagNames(content))
        .plus(intlDeclarations(definitionsDir))
        .plus(atomicsDeclarations(definitionsDir))
        .plus(webAssemblyDeclarations(content))
        .plus(webWorkersDeclarations())
        .plus(serviceWorkersDeclarations(serviceworkerDefinitionsFile))
        .plus(audioWorkletDeclarations(audioWorkletDefinitionsFile))

    for ((name, body, pkg) in aliases) {
        pkg!!

        val suppresses = mutableSetOf<Suppress>().apply {
            if ("override val type: EventType<" in body)
                add(EXTERNAL_CLASS_CONSTRUCTOR_PROPERTY_PARAMETER)

            if ("@JsVirtual" in body && "companion object" in body)
                add(NESTED_CLASS_IN_EXTERNAL_INTERFACE)

            if ("inline fun " in body && name != "Transferable" && !name.endsWith("ReadOnly.ext"))
                add(NOTHING_TO_INLINE)

            if (name == "Locale")
                add(VIRTUAL_MEMBER_HIDDEN)

            if (name in Mixins.UNSAFE)
                add(NON_ABSTRACT_MEMBER_OF_EXTERNAL_INTERFACE)

            if (name == "ChildNode" || name == "ParentNode" || name == "AbstractWorker" || name == "FontFaceSet" || name == "AudioWorkletProcessorImpl")
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

                pkg == "web.assembly" && ("external class " in body || "external fun " in body)
                -> """@file:JsQualifier("WebAssembly")"""

                else -> ""
            },
        ).filter { it.isNotEmpty() }
            .joinToString("\n\n")


        val imports = when (name) {
            "setInterval",
            "setTimeout",
            -> """
            import kotlin.time.Duration
            import kotlin.time.DurationUnit.MILLISECONDS
            """.trimIndent()

            "Document",
            "DocumentOrShadowRoot",
            -> """
            import web.cssom.StyleSheetList    
            import web.fonts.FontFaceSource    
            import web.html.*        
            import web.xpath.XPathEvaluatorBase
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
            import web.uievents.*
            import web.animations.AnimationEvent
            import web.clipboard.ClipboardEvent
            import web.csp.SecurityPolicyViolationEvent
            import web.events.ProgressEvent
            import web.gamepad.GamepadEvent
            import web.history.HashChangeEvent
            import web.history.PageTransitionEvent
            import web.history.PopStateEvent
            import web.storage.StorageEvent
            """.trimIndent()

            "EventSource",
            "XMLHttpRequestEventTarget",
            "RTCDataChannel",
            "ServiceWorkerContainer",
            "BroadcastChannel",
            -> """
            import web.events.ProgressEvent
            """.trimIndent()

            "DedicatedWorkerGlobalScope",
            "ServiceWorkerGlobalScope",
            "WorkerGlobalScope",
            -> """
            import web.fonts.FontFaceSource    
            import web.window.PromiseRejectionEvent
            import web.push.PushEvent
            import web.rtc.RTCTransformEvent
            import web.workers.WorkerGlobalScope
            """.trimIndent()

            "WorkerNavigator",
            -> """
            import web.navigator.*
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
            if ("@JsVirtual" in body) {
                add(NESTED_CLASS_IN_EXTERNAL_INTERFACE)
            }
        }.toTypedArray()

        val annotations = if (suppresses.isNotEmpty()) {
            fileSuppress(*suppresses)
        } else ""

        webglTargetDir.resolve("$name.kt")
            .also { check(!it.exists()) { "Duplicated file: ${it.name}" } }
            .writeText(fileContent(annotations, "", body, "web.gl"))
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
