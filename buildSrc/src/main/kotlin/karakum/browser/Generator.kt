package karakum.browser

import karakum.common.GENERATOR_COMMENT
import karakum.common.Suppress
import karakum.common.Suppress.*
import karakum.common.fileSuppress
import karakum.common.writeCode
import karakum.events.EventDataRegistry
import java.io.File

private val DEFAULT_IMPORTS = Imports(
    "js.array.ArrayLike",
    "js.array.JsTuple2",
    "js.array.ReadonlyArray",
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
    "js.collections.ReadonlySet",
    "js.collections.ReadonlySetLike",
    "js.core.BigInt",
    "js.core.Bitmask",
    "js.core.JsLong",
    "js.core.Void",
    "js.errors.JsError",
    "js.errors.JsErrorName",
    "js.intl.DateTimeFormatOptions",
    "js.intl.Locale",
    "js.intl.Localizable",
    "js.intl.UnicodeBCP47LocaleIdentifier",
    "js.iterable.JsIterable",
    "js.iterable.JsIterator",
    "kotlinx.js.JsPlainObject",
    "js.objects.PropertyKey",
    "js.objects.ReadonlyRecord",
    "js.objects.Record",
    "js.objects.jso",
    "js.promise.Promise",
    "js.promise.PromiseLike",
    "js.temporal.Instant",
    "js.transferable.Transferable",
    "js.typedarrays.BigInt64Array",
    "js.typedarrays.Float32Array",
    "js.typedarrays.Float64Array",
    "js.typedarrays.Int32Array",
    "js.typedarrays.TypedArray",
    "js.typedarrays.Uint32Array",
    "js.typedarrays.Uint8Array",
    "js.typedarrays.Uint8ClampedArray",

    "js.date.Date",

    "seskar.js.JsAlias",
    "seskar.js.JsAlias.Companion.THIS",
    "seskar.js.JsAsync",
    "seskar.js.JsIntValue",
    "seskar.js.JsTypeGuard",
    "seskar.js.JsValue",

    "web.abort.AbortSignal",
    "web.abort.Abortable",
    "web.animations.Animation",
    "web.animations.DocumentTimeline",
    "web.animations.Keyframe",
    "web.animations.KeyframeAnimationOptions",
    "web.animations.PropertyIndexedKeyframes",
    "web.aria.ARIAMixin",
    "web.autofill.AutoFill",
    "web.autofill.AutoFillBase",
    "web.blob.Blob",
    "web.blob.BlobPart",
    "web.blob.BlobPropertyBag",
    "web.canvas.CanvasImageSource",
    "web.canvas.OffscreenCanvas",
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
    "web.cssom.ViewTransition",
    "web.cssom.ViewTransitionUpdateCallback",
    "web.data.DataTransfer",
    "web.dom.DOMStringList",
    "web.dom.DOMTokenList",
    "web.dom.Document",
    "web.dom.DocumentFragment",
    "web.dom.DocumentOrShadowRoot",
    "web.dom.DocumentVisibilityState",
    "web.dom.Element",
    "web.dom.ElementNamespace",
    "web.dom.GetHTMLOptions",
    "web.dom.GlobalEventHandlers",
    "web.dom.HTMLOrSVGElement",
    "web.dom.HTMLOrSVGImageElement",
    "web.dom.HTMLOrSVGScriptElement",
    "web.dom.Node",
    "web.dom.NodeList",
    "web.dom.NodeListOf",
    "web.dom.ParentNode",
    "web.errors.DOMException",
    "web.errors.ErrorEvent",
    "web.events.Event",
    "web.events.EventHandler",
    "web.events.EventInit",
    "web.events.EventInstance",
    "web.events.EventTarget",
    "web.events.EventType",
    "web.events.JsEvent",
    "web.events.ProgressEvent",
    "web.file.File",
    "web.file.FileList",
    "web.form.FormControl",
    "web.form.FormData",
    "web.form.FormDataEvent",
    "web.form.FormEncType",
    "web.form.FormMethod",
    "web.form.SubmitEvent",
    "web.fs.FileSystemDirectoryHandle",
    "web.fs.FileSystemEntry",
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
    "web.http.FetchPriority",
    "web.http.ReferrerPolicy",
    "web.http.Request",
    "web.http.RequestCredentials",
    "web.http.RequestMethod",
    "web.http.Response",
    "web.images.ImageBitmap",
    "web.images.ImageBitmapSource",
    "web.images.ImageData",
    "web.images.ImageDataSettings",
    "web.images.PredefinedColorSpace",
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
    "web.serialization.StructuredSerializeOptions",
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
    "web.url.FragmentDirective",
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

    // TEMP
    "web.events.EventInitMutable",
)

fun generateKotlinDeclarations(
    idlDir: File,
    eventsSourceFile: File,
    definitionsDir: File,
    webDefinitionsFile: File,
    webIterableDefinitionsFile: File,
    serviceworkerDefinitionsFile: File,
    serviceworkerIterableDefinitionsFile: File,
    audioWorkletDefinitionsFile: File,
    sourceDir: File,
) {
    IDLRegistry.rootDirectory = idlDir
    EventDataRegistry.sourceFile = eventsSourceFile

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
    val (mainEventDeclarations, knownEventTypes) = eventDeclarations(content, WEB_WORKER_CONTENT, serviceWorkersContent)
    val eventDeclarations = mainEventDeclarations +
            webWorkersEventDeclarations(WEB_WORKER_CONTENT) +
            serviceWorkersEventDeclarations(content, serviceWorkersContent)

    for ((name, body, optPkg) in eventDeclarations) {
        val suppresses = mutableSetOf<Suppress>().apply {
            if ("val type: EventType<" in body && name != EVENT_INSTANCE)
                add(EXTERNAL_CLASS_CONSTRUCTOR_PROPERTY_PARAMETER)

            if ("Event.types" in name && " = definedExternally" in body)
                add(NON_ABSTRACT_MEMBER_OF_EXTERNAL_INTERFACE)

            if ("inline fun " in body && !name.endsWith(".temp"))
                add(NOTHING_TO_INLINE)

            if (name == EVENT_HANDLER
                || name == EVENT_INSTANCE
                || name == EVENT_TARGET
            )
                add(BOUNDS_NOT_ALLOWED_IF_BOUNDED_BY_TYPE_PARAMETER)
        }.toTypedArray()

        val annotations = if (suppresses.isNotEmpty()) {
            fileSuppress(suppresses = suppresses)
        } else ""

        val pkg = optPkg ?: EVENT_INFO_MAP.getValue(name.substringBefore("."))
            .fqn
            .substringBeforeLast(".")

        val targetDir = sourceDir
            .resolve(pkg.replace(".", "/"))
            .also { it.mkdirs() }

        targetDir.resolve("$name.kt")
            .also { check(!it.exists()) { "Duplicated file: ${it.name}" } }
            .writeCode(fileContent(annotations, "", body, pkg))
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
        .plus(decoratorsDeclarations(definitionsDir))
        .plus(dateDeclarations(definitionsDir))
        .plus(intlDeclarations(definitionsDir))
        .plus(atomicsDeclarations(definitionsDir))
        .plus(webAssemblyDeclarations(content))
        .plus(webWorkersDeclarations())
        .plus(serviceWorkersDeclarations(serviceworkerDefinitionsFile))
        .plus(workerFunctions(serviceWorkersContent))
        .plus(audioWorkletDeclarations(audioWorkletDefinitionsFile))
        .plus(webGpuDeclarations())
        .withEventInstances(knownEventTypes)

    for ((name, body, pkg) in aliases) {
        pkg!!

        val suppresses = mutableSetOf<Suppress>().apply {
            if ("override val type: EventType<" in body)
                add(EXTERNAL_CLASS_CONSTRUCTOR_PROPERTY_PARAMETER)

            if ("inline fun " in body)
                add(NOTHING_TO_INLINE)

            if (name == "Locale")
                add(VIRTUAL_MEMBER_HIDDEN)

            if (name in Mixins.UNSAFE || name == LOCALIZABLE)
                add(NON_ABSTRACT_MEMBER_OF_EXTERNAL_INTERFACE)

            if (name == "ChildNode"
                || name == "ParentNode"
                || name == "AbstractWorker"
                || name == "FontFaceSet"
                || name == "AudioWorkletProcessorImpl"
                || name == "GlobalEventHandlers"
                || name == "WindowEventHandlers"
            )
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

            "GlobalEventHandlers",
            "WindowEventHandlers",
            "FileReader",
                -> """
            import web.cssom.TransitionEvent
            import web.uievents.*
            import web.animations.AnimationEvent
            import web.clipboard.ClipboardEvent
            import web.csp.SecurityPolicyViolationEvent
            import web.gamepad.GamepadEvent
            import web.history.HashChangeEvent
            import web.history.PageTransitionEvent
            import web.history.PopStateEvent
            import web.storage.StorageEvent
            import web.promise.PromiseRejectionEvent
            """.trimIndent()

            "WorkerNavigator",
                -> """
            import web.navigator.*
            """.trimIndent()

            // TEMP
            "Window",
            "Document",
            "DocumentOrShadowRoot",
            "Text",
            "Element",
            "HTMLElement",
            "SVGElement",
            "OffscreenCanvas",
            "HTMLCanvasElement",
            "HTMLDetailsElement",
            "HTMLMediaElement",
            "HTMLFormElement",
            "HTMLInputElement",
            "HTMLSelectElement",
            "HTMLTextAreaElement",
            "HTMLTrackElement",
            "HTMLButtonElement",
            "MathMLElement",
            "WorkerGlobalScope",
            "ServiceWorkerGlobalScope",
            "DedicatedWorkerGlobalScope",
                -> """
            import web.cssom.ContentVisibilityAutoStateChangeEvent
            import web.cssom.TransitionEvent
            import web.dom.*
            import web.window.*
            import web.uievents.*
            import web.animations.AnimationEvent
            import web.gl.WebGLContextEvent
            import web.clipboard.ClipboardEvent
            import web.csp.SecurityPolicyViolationEvent
            import web.device.DeviceMotionEvent    
            import web.device.DeviceOrientationEvent
            import web.gamepad.GamepadEvent
            import web.history.HashChangeEvent
            import web.history.PageTransitionEvent
            import web.history.PopStateEvent
            import web.storage.StorageEvent
            import web.promise.PromiseRejectionEvent
            import web.push.PushEvent
            import web.rtc.RTCTransformEvent
            import web.cssom.StyleSheetList    
            import web.fonts.FontFaceSource    
            import web.html.*        
            import web.xpath.XPathEvaluatorBase    
            import web.workers.WorkerGlobalScope
            """.trimIndent()

            "VisualViewport.events",
                -> """
            import web.dom.*
            """.trimIndent()

            else -> ""
        }

        val targetDir = sourceDir
            .resolve(pkg.replace(".", "/"))
            .also { it.mkdirs() }

        targetDir.resolve("$name.kt")
            .also { check(!it.exists()) { "Duplicated file: ${it.name}" } }
            .writeCode(
                fileContent(
                    annotations = annotations,
                    imports = imports,
                    body = body,
                    pkg = pkg,
                )
            )
    }

    for ((name, body) in webglDeclarations(content)) {
        webglTargetDir.resolve("$name.kt")
            .also { check(!it.exists()) { "Duplicated file: ${it.name}" } }
            .writeCode(fileContent("", "", body, "web.gl"))
    }
}

private fun fileContent(
    annotations: String = "",
    imports: String = "",
    body: String,
    pkg: String,
): String {
    val result = sequenceOf(
        "// $GENERATOR_COMMENT",
        annotations,
        "package $pkg",
        DEFAULT_IMPORTS.forContent(body),
        imports,
        body,
    ).filter { it.isNotEmpty() }
        .joinToString("\n\n")

    return result
}
