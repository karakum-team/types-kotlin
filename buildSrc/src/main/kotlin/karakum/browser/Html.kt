package karakum.browser

import karakum.common.UnionConstant
import karakum.common.objectUnionBody

internal const val VIDEO_FRAME_REQUEST_ID = "VideoFrameRequestId"
internal const val RENDERING_CONTEXT_ID = "RenderingContextId"
internal const val GEOLOCATION_WATCH_ID = "GeolocationWatchId"

private val DEPRECATED = setOf(
    "HTMLDirectoryElement",
    "HTMLFontElement",
    "HTMLFrameElement",
    "HTMLFrameSetElement",
    "HTMLMarqueeElement",

    "HTMLTableHeaderCellElement",
    "HTMLTableDataCellElement",

    "HTMLAllCollection",

    // TEMP
    "CanvasCaptureMediaStreamTrack",
)

private val ANIMATION_TYPES = setOf(
    "Animation",
    "AnimationEffect",
    "AnimationTimeline",

    "ComputedEffectTiming",
    "EffectTiming",
    "OptionalEffectTiming",
)

private val DOM_TYPES = setOf(
    "DOMStringList",
    "DOMStringMap",
    "DOMTokenList",

    "Attr",
    "CDATASection",
    "CharacterData",
    "Comment",
    "ElementCreationOptions",
    "NonDocumentTypeChildNode",
    "NonElementParentNode",
    "ProcessingInstruction",
    "ChildNode",
    "DocumentType",
    "NodeFilter",
    "TreeWalker",
    "AbstractRange",
    "Range",
    "Slottable",
    "Text",
    "NodeIterator",
    "InnerHTML",

    // special
    "HTMLOrSVGElement",
    "FocusOptions",

    "Node",
    "NodeFilter",
    "NodeList",
    "NamedNodeMap",
    "GetRootNodeOptions",
    "DOMImplementation",

    "ChildNode",
    "ParentNode",
    "Element",
    "CheckVisibilityOptions",

    "DocumentAndElementEventHandlers",
    "GlobalEventHandlers",
)

private val SCROLL_TYPES = setOf(
    "ScrollOptions",
    "ScrollToOptions",
    "ScrollIntoViewOptions",
)

private val FULLSCREEN_TYPES = setOf(
    "FullscreenOptions",
)

private val CSSOM_INTERFACES = listOf(
    "ElementCSSInlineStyle",
    "LinkStyle",
)

private val CSSOM_TYPES = listOf(
    "StylePropertyMap",
    "StylePropertyMapReadOnly",
    "PropertyDefinition",
) + CSSOM_INTERFACES

private val DOM_DATA_TYPES = listOf(
    "DataTransfer",
    "DataTransferItem",
    "DataTransferItemList",
)

internal val DOM_GEOMETRY_TYPES = listOf(
    "DOMPointReadOnly",
    "DOMPoint",
    "DOMPointInit",

    "DOMRectReadOnly",
    "DOMRect",
    "DOMRectInit",
    "DOMRectList",

    "DOMMatrixReadOnly",
    "DOMMatrix",
    "DOMMatrixInit",
    "DOMMatrix2DInit",

    "DOMQuad",
    "DOMQuadInit",
)

private val DOM_PARSING_TYPES = listOf(
    "DOMParser",
    "XMLSerializer",
)

private val CANVAS_TYPES = listOf(
    "ImageData",
    "ImageDataSettings",
    "ImageEncodeOptions",

    "ImageBitmap",
    "ImageBitmapOptions",
    "ImageBitmapRenderingContext",
    "ImageBitmapRenderingContextSettings",

    "Path2D",
    "TextMetrics",

    "OffscreenCanvas",
    "OffscreenCanvasRenderingContext2D",
)

private val HTTP_TYPES = listOf(
    "Body",
    "Request",
    "RequestInit",
    "Response",
    "ResponseInit",

    "Headers",
    "HeadersInit",

    "FormData",
)

private val MEDIA_STREAM_TYPES = listOf(
    "DoubleRange",
    "ULongRange",
)

private val MEDIA_SESSION_TYPES = listOf(
    "MediaSession",
    "MediaSessionAction",
    "MediaSessionActionDetails",
    "MediaMetadata",
    "MediaMetadataInit",
    "MediaImage",
    "MediaPositionState",
)

private val MEDIA_CAPABILITIES_TYPES = listOf(
    "AudioConfiguration",
    "VideoConfiguration",

    "MediaConfiguration",
    "MediaEncodingConfiguration",
    "MediaDecodingConfiguration",
)

private val MEDIA_SOURCE_TYPES = listOf(
    "MediaSource",
    "SourceBuffer",
    "SourceBufferList",
    "TimeRanges",
)

private val WEB_AUDIO_TYPES = listOf(
    "AudioTimestamp",

    "AudioContext",
    "AudioContextOptions",

    "AnalyserOptions",
    "AudioBufferOptions",
    "AudioBufferSourceOptions",
    "AudioNodeOptions",
    "AudioWorkletNodeOptions",
    "AudioParamMap",
    "MediaElementAudioSourceOptions",
    "OfflineAudioContextOptions",
    "OscillatorOptions",
    "PannerOptions",
    "PeriodicWaveOptions",
    "StereoPannerOptions",
    "WaveShaperOptions",
    "PeriodicWaveConstraints",
    "DecodeSuccessCallback",
    "DecodeErrorCallback",
    "BiquadFilterOptions",
    "ChannelMergerOptions",
    "ChannelSplitterOptions",
    "ConstantSourceOptions",
    "ConvolverOptions",
    "DelayOptions",
    "DynamicsCompressorOptions",
    "GainOptions",
    "IIRFilterOptions",

    "AnalyserNode",
    "AudioBuffer",
    "AudioBufferSourceNode",
    "AudioDestinationNode",
    "AudioListener",
    "AudioNode",
    "AudioParam",
    "AudioScheduledSourceNode",
    "AudioWorklet",
    "AudioWorkletGlobalScope",
    "AudioWorkletNode",
    "AudioWorkletProcessor",
    "BaseAudioContext",
    "BiquadFilterNode",
    "ChannelMergerNode",
    "ChannelSplitterNode",
    "ConstantSourceNode",
    "ConvolverNode",
    "DelayNode",
    "DynamicsCompressorNode",
    "GainNode",
    "IIRFilterNode",
    "MediaElementAudioSourceNode",
    "MediaStreamAudioDestinationNode",
    "MediaStreamAudioSourceNode",
    "MediaStreamAudioSourceOptions",
    "OfflineAudioContext",
    "OscillatorNode",
    "PannerNode",
    "PeriodicWave",
    "StereoPannerNode",
    "WaveShaperNode",
)

private val WORKERS_TYPES = listOf(
    "AbstractWorker",
    "SharedWorker",
    "Worker",
    "WorkerOptions",
)

private val WEB_AUTHN_TYPES = listOf(
    "AuthenticationExtensionsClientInputs",
    "AuthenticationExtensionsClientOutputs",
    "AuthenticatorAssertionResponse",
    "AuthenticatorAttestationResponse",
    "AuthenticatorResponse",
    "AuthenticatorSelectionCriteria",
    "CredentialPropertiesOutput",
    "PublicKeyCredential",
    "PublicKeyCredentialCreationOptions",
    "PublicKeyCredentialDescriptor",
    "PublicKeyCredentialEntity",
    "PublicKeyCredentialParameters",
    "PublicKeyCredentialRequestOptions",
    "PublicKeyCredentialRpEntity",
    "PublicKeyCredentialUserEntity",
)

private val CREDENTIALS_TYPES = listOf(
    "Credential",
    "CredentialCreationOptions",
    "CredentialRequestOptions",
    "CredentialsContainer",
)

private val WEB_CRYPTO_TYPES = listOf(
    "Algorithm",
    "KeyAlgorithm",

    "Crypto",
    "CryptoKey",
    "CryptoKeyPair",
    "SubtleCrypto",

    "JsonWebKey",

    "AesCbcParams",
    "AesCtrParams",
    "AesDerivedKeyParams",
    "AesGcmParams",
    "AesKeyAlgorithm",
    "AesKeyGenParams",
    "Algorithm",
    "EcKeyAlgorithm",
    "EcKeyGenParams",
    "EcKeyImportParams",
    "EcdhKeyDeriveParams",
    "EcdsaParams",
    "HkdfParams",
    "HmacImportParams",
    "HmacKeyAlgorithm",
    "HmacKeyGenParams",
    "KeyAlgorithm",
    "Pbkdf2Params",
    "RsaHashedImportParams",
    "RsaHashedKeyAlgorithm",
    "RsaKeyAlgorithm",
    "RsaKeyGenParams",
    "RsaOaepParams",
    "RsaPssParams",

    "RsaOtherPrimesInfo",
    "RsaHashedKeyGenParams",
)

private val USER_ACTIVATION_TYPES = listOf(
    "UserActivation",
)

private val QUERY_TYPES = listOf(
    "Cache",
    "CacheStorage",

    "CacheQueryOptions",
    "MultiCacheQueryOptions",
)

private val CODECS_TYPES = listOf(
    "AvcEncoderConfig",
    "EncodedVideoChunk",
    "EncodedVideoChunkInit",
    "VideoColorSpace",
    "VideoColorSpaceInit",
    "VideoDecoder",
    "VideoDecoderConfig",
    "VideoDecoderConfig",
    "VideoDecoderInit",
    "VideoDecoderSupport",
    "VideoEncoder",
    "VideoEncoderConfig",
    "VideoEncoderConfig",
    "VideoEncoderEncodeOptions",
    "VideoEncoderInit",
    "VideoEncoderSupport",
    "VideoFrame",
    "VideoFrameBufferInit",
    "VideoFrameInit",
    "EncodedVideoChunkMetadata",
    "VideoFrameCopyToOptions",
    "PlaneLayout",
)

private val FILE_SYSTEM_TYPES = listOf(
    "WriteParams",
)

private val STREAMS_TYPES = listOf(
    "QueuingStrategy",
    "QueuingStrategyInit",
    "QueuingStrategySize",
    "ReadableByteStreamController",
    "ReadableStream",
    "ReadableStreamBYOBReader",
    "ReadableStreamBYOBRequest",
    "ReadableStreamController",
    "ReadableStreamDefaultController",
    "ReadableStreamDefaultReadDoneResult",
    "ReadableStreamDefaultReadValueResult",
    "ReadableStreamDefaultReader",
    "ReadableStreamGenericReader",
    "ReadableStreamGetReaderOptions",
    "ReadableStreamReadDoneResult",
    "ReadableStreamReadResult",
    "ReadableStreamReadValueResult",
    "ReadableWritablePair",
    "StreamPipeOptions",
    "TextDecoderOptions",
    "TextDecoderStream",
    "TextEncoderStream",
    "Transformer",
    "TransformStream",
    "TransformStreamDefaultController",
    "UnderlyingByteSource",
    "UnderlyingDefaultSource",
    "UnderlyingSink",
    "UnderlyingSource",
    "WritableStream",
    "WritableStreamDefaultController",
    "WritableStreamDefaultWriter",
)

internal fun htmlDeclarations(
    source: String,
): Sequence<ConversionResult> {
    val (content, additionalType) = prepareContent(
        source.replace(";\n     *", ";--\n     *")
    )

    val getStaticSource = { name: String ->
        when (name) {
            "ElementInternals" -> null
            else -> getStaticSource(name, content)
        }
    }

    val patterns = sequenceOf(
        "HTML.+?",
        "SVG.+?",
        "MathML.+?",
        "HTMLCollection .+?",
        "MediaError",

        "ShadowRoot .+?",
        "ShadowRootInit",
        "WindowEventHandlers",

        "CustomElementRegistry",
        "CustomElementConstructor",
        "ElementDefinitionOptions",

        "ARIAMixin",

        "ElementContentEditable",
        "ElementInternals .+?",
        "ValidityStateFlags",

        "Node .+?",
        "NodeList<.+?",
        "ChildNode .+?",
        "ParentNode .+?",
        "Element .+?",
        "Attr .+?",
        "CDATASection .+?",
        "Comment .+?",
        "Range .+?",
        "CharacterData .+?",
        "Text .+?",
        "ProcessingInstruction .+?",

        "Document .+?",
        "DocumentType .+?",
        "DocumentFragment .+?",
        "DocumentOrShadowRoot",
        "XMLDocument .+?",
        "XPath.+?",

        "DOMMatrix .+?",
        "DOMMatrixInit .+?",
        "DOMPoint .+?",
        "DOMRect .+?",

        "IntersectionObserve?.+",
        "MutationObserve?.+",
        "MutationRecord",
        "ResizeObserve?.+",

        "ScrollToOptions .+?",
        "ScrollIntoViewOptions .+?",

        "Selection",

        "Touch",
        "TouchInit",
        "TouchList",

        "Canvas.+?",
        "CanvasRenderingContext2D .+?",
        "OffscreenCanvas .+?",
        "OffscreenCanvasRenderingContext2D .+?",
        "Path2D .+?",

        "Window .+?",
        "VisualViewport .+?",
        "WindowPostMessageOptions .+?",

        "Animation .+?",
        "ComputedEffectTiming .+?",

        "FileReader .+?",

        "Request .+?",
        "Response .+?",

        "CSS.+?",
        "StyleSheet",
        "StyleSheetList",
        "MediaList",
        "MediaQueryList .+?",

        "FontFace",
        "FontFaceDescriptors",
        "FontFaceSet .+?",
        "FontFaceSource",

        "EventModifierInit .+?",

        "Clipboard .+?",
        "Clipboard.+?",

        "History",
        "Location",

        "Screen",
        "ScreenOrientation .+?",

        "SpeechReco.+?",
        "SpeechSynt.+?",

        "Geolocation",
        "Geolocation.+?",
        "PositionOptions",

        "DeviceMotionEventAcceleration",
        "DeviceMotionEventAccelerationInit",
        "DeviceMotionEventRotationRate",
        "DeviceMotionEventRotationRateInit",

        "ShareData",

        "EventSourceInit",
        "EventSource .+?",

        "XMLHttp.+?",

        "BroadcastChannel .+?",

        "Storage",
        "StorageEstimate",
        "StorageManager",
        "Permission.+?",
        "Gamepad",
        "Gamepad.+?",

        "File",
        "File.+?",

        "FileSystem",
        "FileSystem.+?",

        "Lock",
        "Lock.+?",
        "Navigator.+?",
        "PictureInPictureWindow.+?",
        "ValidityState",
        "AssignedNodesOptions",
        "VideoFrameCallbackMetadata",
        "VideoPlaybackQuality",
        "RemotePlayback .+?",
        "DOMMatrix2DInit",

        "RTC.+?",

        "MediaKey.+?",
        "TextTrack.+?",

        "MediaStream.+?",
        "MediaTrack.+?",
        "Constrain.+?",

        "MediaDevice.+?",
        "InputDeviceInfo .+?",
        "DisplayMediaStreamOptions",

        "MediaCapabilitie.+?",
        "MediaEncodingConfiguration .+?",
        "MediaDecodingConfiguration .+?",

        "MediaRecorder.+?",

        "Worklet",
        "WorkletOptions",

        "Notification.+?",

        "ServiceWorker.+?",
        "GetNotificationOptions",
        "NavigationPreloadManager",
        "NavigationPreloadState",
        "RegistrationOptions",

        "TextEncode.+?",
        "TextDecode.+?",
        "GenericTransformStream",

        "IDB.+?",

        "Push.+?",

        "Performance.+?",

        "WebSocket.+?",
        "MIDI.+?",

        "WakeLock",
        "WakeLock.+?",

        "Console",

        "Payment.+?",
    ).plus(ANIMATION_TYPES)
        .plus(DOM_TYPES)
        .plus(SCROLL_TYPES)
        .plus(FULLSCREEN_TYPES)
        .plus(CSSOM_TYPES.flatMap { sequenceOf(it, "$it .+?") })
        .plus(DOM_DATA_TYPES)
        .plus(DOM_GEOMETRY_TYPES)
        .plus(DOM_PARSING_TYPES)
        .plus(CANVAS_TYPES)
        .plus(HTTP_TYPES)
        .plus(MEDIA_CAPABILITIES_TYPES)
        .plus(MEDIA_STREAM_TYPES)
        .plus(MEDIA_SESSION_TYPES)
        .plus(MEDIA_SOURCE_TYPES.flatMap { sequenceOf(it, "$it .+?") })
        .plus(WEB_AUDIO_TYPES.flatMap { sequenceOf(it, "$it .+?") })
        .plus(WORKERS_TYPES.flatMap { sequenceOf(it, "$it .+?") })
        .plus(WEB_CRYPTO_TYPES.flatMap { sequenceOf(it, "$it .+?") })
        .plus(QUERY_TYPES.flatMap { sequenceOf(it, "$it .+?") })
        .plus(CODECS_TYPES.flatMap { sequenceOf(it, "$it .+?") })
        .plus(USER_ACTIVATION_TYPES)
        .plus(FILE_SYSTEM_TYPES)
        .plus(WEB_AUTHN_TYPES.flatMap { sequenceOf(it, "$it .+?") })
        .plus(STREAMS_TYPES.flatMap { sequenceOf(it, "$it<.+?", "$it .+?") })
        .plus(CREDENTIALS_TYPES)
        .joinToString("|")

    val interfaces =
        Regex("""interface ($patterns) \{[\s\S]*?\n\}""")
            .findAll(content)
            .map { it.value }
            .mapNotNull { convertInterface(it, getStaticSource) }

    return interfaces
        .plus(additionalType)
        .plus(
            ConversionResult(
                name = "EventCounts",
                body = "sealed external class EventCounts : ReadonlyMap<EventType<*>, Int>",
                pkg = "web.performance",
            )
        )
        .plus(
            ConversionResult(
                name = "ElementNamespace",
                body = "external interface ElementNamespace",
                pkg = "web.dom",
            )
        )
        .plus(
            ConversionResult(
                name = "NodeFilter",
                body = "typealias NodeFilter = (node: Node) -> Short",
                pkg = "web.dom",
            )
        )
        .plus(
            ConversionResult(
                name = VIDEO_FRAME_REQUEST_ID,
                body = "sealed external interface $VIDEO_FRAME_REQUEST_ID",
                pkg = "web.html",
            )
        )
        .plus(
            ConversionResult(
                name = GEOLOCATION_WATCH_ID,
                body = "sealed external interface $GEOLOCATION_WATCH_ID",
                pkg = "web.geolocation",
            )
        )
        .plus(
            ConversionResult(
                name = "HTMLCollectionOf",
                body = "typealias HTMLCollectionOf<T> = HTMLCollection<T>",
                pkg = "web.html",
            )
        )
        .plus(
            ConversionResult(
                name = "CSSNumberish",
                body = "typealias CSSNumberish = Double",
                pkg = "web.animations",
            )
        )
        .plus(
            ConversionResult(
                name = "XPathNSResolver",
                body = "typealias XPathNSResolver = (prefix: String?) -> String?",
                pkg = "web.xpath",
            )
        )
        .plus(
            ConversionResult(
                name = "ReadableStreamReader",
                body = "typealias ReadableStreamReader = ReadableStreamGenericReader /* {\n    fun releaseLock()\n} */",
                pkg = "web.streams",
            )
        )
        .plus(
            DOM_GEOMETRY_TYPES
                .filter { it.endsWith("ReadOnly") }
                .map { type ->
                    val initType = type.removeSuffix("ReadOnly") + "Init"
                    ConversionResult(
                        name = "$type.ext",
                        body = """
                        inline fun $type.asInit(): $initType =    
                            unsafeCast<$initType>()                                                 
                        """.trimIndent(),
                        pkg = "web.geometry",
                    )
                }
        )
        .plus(DedicatedWorkerGlobalScope())
}

private fun prepareContent(
    source: String,
): Pair<String, ConversionResult> {
    val ids = Regex("""getContext\(contextId\: "([\w\d]+)"\, """)
        .findAll(source)
        .map { it.groupValues[1] }
        .distinct()
        .toList()

    fun kotlinName(id: String): String =
        if (id == "2d") "canvas" else id

    val contextIdBody = objectUnionBody(
        name = RENDERING_CONTEXT_ID,
        constants = ids.map { id ->
            val name = kotlinName(id)

            UnionConstant(
                kotlinName = name,
                jsName = name,
                value = id,
                comment = if (id != name) "// `$id`" else null
            )
        }
    )

    val contextId = ConversionResult(
        RENDERING_CONTEXT_ID,
        contextIdBody,
        "web.canvas",
    )

    val content = ids.fold(source) { acc, id ->
        val name = kotlinName(id)

        acc.replace(
            """getContext(contextId: "$id"""",
            """getContext(contextId: $RENDERING_CONTEXT_ID.$name""",
        )
    }

    return content to contextId
}

private val COLLECTIONS_WITH_BOUNDS = setOf(
    "NodeList",
    "HTMLCollectionBase",
    "HTMLCollection",
)

internal fun convertInterface(
    source: String,
    getStaticSource: (String) -> String?,
    predefinedPkg: String? = null,
): ConversionResult? {
    val name = source
        .substringAfter(" ")
        .substringBefore(" ")
        .substringBefore("<")

    when {
        name in DEPRECATED -> return null
        name.endsWith("NameMap") -> return null
        name.endsWith("Event") -> return null
        name.endsWith("EventInit") -> return null
        name.endsWith("EventMap") -> return null

        // TEMP
        name == "HTMLCollectionOf" -> return null
    }

    if (("target: string" in source || "target?: string" in source) && (name.startsWith("HTML") || name == "Window")) {
        var newSource = source
            .replace("target: string", "target: WindowTarget")
            .replace("target?: string", "target?: WindowTarget")

        if (name == "Window") {
            newSource = newSource.replace("name: string", "name: WindowName")
        }

        return convertInterface(
            source = newSource,
            getStaticSource = getStaticSource,
            predefinedPkg = predefinedPkg,
        )
    }

    if (name == "HTMLInputElement" && " type: string;" in source) {
        var newSource = source
            .replace(" type: string;", " type: InputType;")

        return convertInterface(
            source = newSource,
            getStaticSource = getStaticSource,
            predefinedPkg = predefinedPkg,
        )
    }

    val staticSource = getStaticSource(name)
    val type = if (staticSource != null) "class" else "interface"

    var declaration = source.substringBefore(" {\n")
        .replace(", AnimationFrameProvider", "")
        .replace(", WindowLocalStorage, WindowOrWorkerGlobalScope, WindowSessionStorage", "")
        .replace(" = any, ", ", ")
        .replace(" = any>", ">")
        .replace("interface ", "$type ")

    declaration = when (name) {
        "ChildNode",
        "ParentNode",
        -> declaration.replace("extends Node", "/* : Node */")

        "Body",
        "CanvasPath",
        -> declaration.replace("interface", "class /* interface */")

        "AbstractWorker",
        -> declaration + ": IEventTarget"

        in COLLECTIONS_WITH_BOUNDS,
        -> declaration
            .replaceFirst(" extends HTMLCollectionBase", " :\nHTMLCollectionBase<T>")
            .replaceFirst(" extends ", " : ")
            .replace(" extends ", " :\n")

        "HTMLFormControlsCollection",
        -> declaration
            .replaceFirst(" extends HTMLCollectionBase", " :\nHTMLCollectionBase<Element>")

        else -> {
            declaration
                .replace(" extends ", " :\n")
                .let { if (":" in it) it.replace(", ", ",\n") else it }
                .replace("\nAnimatable,", "\n/* Animatable, */")
        }
    }

    var memberSource = source
        .substringAfter(" {\n")
        .removeSuffix("}")
        .removeSuffix(";\n")
        .trimIndent()

    if (memberSource.startsWith("("))
        return null

    if (memberSource == "[name: string]: string | undefined") {
        declaration += " : Record<String, String>"
        memberSource = ""
    }

    if (memberSource == "new (...params: any[]): HTMLElement")
        return ConversionResult(
            name = name,
            body = "typealias $name = JsClass<HTMLElement>",
            pkg = "web.html",
        )

    val arrayType = if ("readonly length: number;" in memberSource) {
        val result = Regex("""\[index\: number\]\: (\w+)""")
            .find(memberSource)

        if (result != null) {
            when (val t = result.groupValues[1]) {
                "string" -> "String"
                else -> t
            }
        } else null
    } else null

    val iterableTypeParameter = IterableRegistry.typeParameter(name)
    val listLikeMode = name in LIST_LIKE
    if (listLikeMode) {
        requireNotNull(arrayType)
        requireNotNull(iterableTypeParameter)
    }

    if (arrayType != null && name != "Window" && !listLikeMode) {
        declaration += if (":" in declaration && name !in COLLECTIONS_WITH_BOUNDS) "," else ":"
        declaration += "\nArrayLike<$arrayType>"
    }

    val mapLikeParameters = if (iterableTypeParameter != null) {
        mapLikeParameters(iterableTypeParameter)
    } else null

    if (iterableTypeParameter != null) {
        val typeParameter = arrayType ?: iterableTypeParameter
        val iterableDeclaration = when {
            mapLikeParameters != null
            -> "MapLike<${mapLikeParameters.key}, ${mapLikeParameters.value}>"

            listLikeMode
            -> "ListLike<$typeParameter>"

            else -> "JsIterable<$typeParameter>"
        }

        declaration += if (":" in declaration && name != "NodeList") "," else ":"
        declaration += "\n$iterableDeclaration"
    }

    val additionalParent = IterableRegistry.additionalParent(name)
    if (additionalParent != null) {
        declaration += if (":" in declaration) "," else ":"
        declaration += "\n$additionalParent"
    }

    val typeProvider = TypeProvider(
        parentType = name,
        arrayType = arrayType,
        hideForEach = listLikeMode
                || mapLikeParameters != null
                || (additionalParent?.startsWith("ReadonlyMap<") ?: false)
    )

    val mainConstructor: String
    val additionalConstructors: String
    if (staticSource != null) {
        val constructors = getConstructors(name, staticSource)
        val firstConstructor = constructors.firstOrNull()
        mainConstructor = if (firstConstructor != null) {
            var result = firstConstructor.removePrefix("constructor")
            when {
                result.isEmpty()
                -> "()"

                "\n" !in result
                -> "(\n" + result.removeSurrounding("(", ")") + "\n)"

                else -> result
            }

        } else ""

        additionalConstructors = constructors
            .drop(1)
            .joinToString("\n")
    } else {
        mainConstructor = ""
        additionalConstructors = ""
    }

    if (mainConstructor.isNotEmpty()) {
        declaration = if (":\n" in declaration) {
            declaration.replaceFirst(":\n", "$mainConstructor:")
        } else declaration + mainConstructor
    }

    var members = if (memberSource.isNotEmpty()) {
        var result = memberSource
            .splitToSequence(";\n")
            .mapNotNull { convertMember(it, typeProvider) }
            .joinToString("\n")

        result = when (name) {
            "Node",
            -> result
                .replace("val ownerDocument:", "open val ownerDocument:")

            "CSSTransformValue",
            "CSSUnparsedValue",
            -> "override val length: Int\n$result"

            "DOMMatrixReadOnly",
            "DOMPointReadOnly",
            "DOMRectReadOnly",
            -> result
                .replace("val ", "open val ")

            "DOMMatrix",
            "DOMPoint",
            "DOMRect",
            -> result
                .replace("var ", "override var ")

            "Document",
            "DocumentFragment",
            "DocumentType",
            "Attr",
            "CharacterData",
            "Element",
            "ProcessingInstruction",
            -> result
                .replace("val ownerDocument:", "override val ownerDocument:")
                .replace("fun getElementById(", "override fun getElementById(")

            "HTMLSelectElement",
            -> result
                .replace("fun remove()", "override fun remove()")

            "HTMLOptionsCollection",
            -> result
                .replace("var length: Int", "override var length: Int")

            "CanvasPathDrawingStyles",
            -> result
                .replace("Number[]", "ReadonlyArray<Double>")
                .replace("<number>", "<Double>")

            "RTCInboundRtpStreamStats",
            -> result.replace("var kind:", "override var kind:")

            "TextDecoderStream",
            "TextEncoderStream",
            -> result
                .replace("val readable:", "override val readable:")
                .replace("val writable:", "override val writable:")

            "PerformanceEntry",
            -> result
                .replace("fun toJSON()", "open fun toJSON()")

            "PerformanceEventTiming",
            "PerformanceNavigationTiming",
            "PerformanceResourceTiming",
            -> result
                .replace("val target: Node?", "val target: EventTarget /* Node */?")
                .replace("fun toJSON()", "override fun toJSON()")

            else -> result
        }

        result
    } else ""

    val additionalOverrides = when {
        listLikeMode && name in FINAL_LIST_LIKE
        -> listLikeOverrides(iterableTypeParameter!!)

        mapLikeParameters != null && name in FINAL_MAP_LIKE
        -> mapLikeOverrides(mapLikeParameters.key, mapLikeParameters.value)

        else -> ""
    }

    members = sequenceOf(additionalConstructors, members, additionalOverrides)
        .filter { it.isNotEmpty() }
        .joinToString("\n\n")

    val modifier = when {
        name == "Animation" ||
                name == "DOMMatrixReadOnly" ||
                name == "DOMPointReadOnly" ||
                name == "DOMRectReadOnly" ||
                name == "Worker" ||
                name == "Credential" ||
                name == "WritableStream"
        -> "open"

        // TEMP WA
        name == "Text" ||
                name == "Comment"
        -> "sealed"

        name in CSSOM_INTERFACES ||
                name in DOM_PARSING_TYPES ||
                name == "DataTransfer" ||
                name == "FileReader" ||
                name == "FontFaceSource" ||
                name == "XPathEvaluatorBase" ||
                name == "ARIAMixin" ||
                name == "HTMLOrSVGElement" ||
                name == "InnerHTML" ||
                name == "DocumentOrShadowRoot" ||
                name == "XMLHttpRequest" ||
                name == "XMLHttpRequestUpload" ||
                name == "AbstractWorker" ||
                name.endsWith("Handlers") ||
                mainConstructor.isNotEmpty()
        -> ""

        name == "Element" ||
                name == "Document" ||
                name == "DocumentFragment" ||
                name == "Worklet"
        -> "abstract"

        type == "class" &&
                name.startsWith("HTML") &&
                name.endsWith("Element")
        -> "abstract"

        else -> "sealed"
    }

    val companion = if (staticSource != null && predefinedPkg != "js.intl") {
        getCompanion(name, staticSource)
    } else ""

    var body = sequenceOf(
        "$modifier external $declaration {",
        members,
        companion,
        "}",
    ).filter { it.isNotEmpty() }
        .joinToString("\n")

    when (name) {
        "Body",
        "CanvasPath",
        -> body = """@JsName("Object")""" + "\n" + body

        "MediaList",
        -> body = body.applyMediaListPatch()

        "MediaQueryList",

        "HTMLMetaElement",
        "HTMLSourceElement",
        "HTMLStyleElement",
        "SVGStyleElement",
        -> body = body.applyMediaQueryPatch()
    }

    val pkg = when {
        predefinedPkg != null -> predefinedPkg

        name == "Console" -> "web.console"

        name == "RemotePlayback" -> "web.remoteplayback"

        name.startsWith("Touch") -> "web.uievents"
        name in DOM_PARSING_TYPES -> "web.dom.parsing"
        name.startsWith("SVG") -> "web.svg"
        name.startsWith("MathML") -> "web.mathml"

        name.startsWith("CSS") -> "web.cssom"
        name.startsWith("StyleSheet") -> "web.cssom"
        name == "MediaList" -> "web.cssom"
        name == "MediaQueryList" -> "web.cssom"
        name.startsWith("FontFace") -> "web.fonts"

        name.startsWith("Canvas") -> "web.canvas"
        name in CANVAS_TYPES -> "web.canvas"

        name in ANIMATION_TYPES -> "web.animations"

        name.startsWith("Clipboard") -> "web.clipboard"

        name == "History" -> "web.history"
        name == "Location" -> "web.location"

        name.startsWith("DeviceMotion") -> "web.device"

        name.startsWith("FileSystem") -> "web.filesystem"
        name in FILE_SYSTEM_TYPES -> "web.filesystem"

        name.startsWith("File") -> "web.file"
        name.startsWith("Gamepad") -> "web.gamepad"

        name.startsWith("Geolocation") -> "web.geolocation"
        name.startsWith("Position") -> "web.geolocation"

        name.startsWith("Lock") -> "web.locks"
        name.startsWith("Navigator") -> "web.navigator"
        name.startsWith("Permission") -> "web.permissions"
        name.startsWith("Screen") -> "web.screen"

        name.startsWith("Speech") -> "web.speech"

        name == "ShareData" -> "web.share"
        name.startsWith("Storage") -> "web.storage"

        name.startsWith("EventSource") -> "web.sse"
        name in HTTP_TYPES -> "web.http"
        name.startsWith("XMLHttp") -> "web.xhr"

        name == "ARIAMixin" -> "web.aria"
        name == "Selection" -> "web.selection"

        name == "EventModifierInit" -> "web.uievents"

        name.startsWith("Document") -> "web.dom"
        name in DOM_TYPES -> "web.dom"
        name in CSSOM_TYPES -> "web.cssom"
        name in DOM_DATA_TYPES -> "web.data"
        name in DOM_GEOMETRY_TYPES -> "web.geometry"
        name == "XMLDocument" -> "web.xml"

        name.startsWith("XPath") -> "web.xpath"

        name in WEB_AUDIO_TYPES -> "web.audio"

        name.startsWith("RTC") -> "webrtc"
        name.startsWith("MediaKey") -> "web.media.key"
        name.startsWith("TextTrack") -> "webvtt"

        name.startsWith("MediaCapabilities") -> "web.media.capabilities"
        name in MEDIA_CAPABILITIES_TYPES -> "web.media.capabilities"

        name.startsWith("MediaDevice") -> "web.media.devices"
        name == "InputDeviceInfo" -> "web.media.devices"
        name == "DisplayMediaStreamOptions" -> "web.media.devices"
        name.startsWith("MediaRecorder") -> "web.media.recorder"

        name.startsWith("MediaStream") -> "web.media.streams"
        name.startsWith("MediaTrack") -> "web.media.streams"
        name.startsWith("Constrain") -> "web.media.streams"
        name in MEDIA_STREAM_TYPES -> "web.media.streams"

        name in MEDIA_SESSION_TYPES -> "web.media.session"

        name in MEDIA_SOURCE_TYPES -> "web.media.source"

        name.startsWith("Worklet") -> "web.worklets"
        name in WORKERS_TYPES -> "web.workers"

        name.startsWith("Notification") -> "web.notifications"
        name.startsWith("ServiceWorker") -> "web.serviceworker"
        name == "GetNotificationOptions" -> "web.serviceworker"
        name == "NavigationPreloadState" -> "web.serviceworker"
        name == "NavigationPreloadManager" -> "web.serviceworker"
        name == "RegistrationOptions" -> "web.serviceworker"

        name.startsWith("IntersectionObserver") -> "web.dom.observers"
        name.startsWith("MutationObserver") -> "web.dom.observers"
        name == "MutationRecord" -> "web.dom.observers"
        name.startsWith("ResizeObserver") -> "web.dom.observers"

        name.startsWith("TextEncode") -> "web.encoding"
        name.startsWith("TextDecode") -> "web.encoding"
        name == "GenericTransformStream" -> "web.encoding"

        name in STREAMS_TYPES -> "web.streams"

        name in WEB_AUTHN_TYPES -> "web.authn"
        name in CREDENTIALS_TYPES -> "web.credentials"
        name in WEB_CRYPTO_TYPES -> "web.crypto"

        name.startsWith("IDB") -> "web.idb"
        name.startsWith("Push") -> "web.push"

        name.startsWith("Performance") -> "web.performance"

        name.startsWith("Window") -> "web.window"
        name == "PictureInPictureWindow" -> "web.window"
        name == "VisualViewport" -> "web.viewport"

        name in SCROLL_TYPES -> "web.scroll"
        name in FULLSCREEN_TYPES -> "web.fullscreen"

        name == "BroadcastChannel" -> "web.broadcast"
        name == "WebSocket" -> "websockets"

        name in QUERY_TYPES -> "web.cache"
        name in CODECS_TYPES -> "web.codecs"

        name in USER_ACTIVATION_TYPES -> "web.useractivation"

        name.startsWith("MIDI") -> "web.midi"
        name.startsWith("WakeLock") -> "web.wakelock"

        name.startsWith("Payment") -> "web.payment"

        else -> "web.html"
    }

    return ConversionResult(
        name = name,
        body = body,
        pkg = pkg,
    )
}

internal fun getStaticSource(
    name: String,
    source: String,
): String? {
    val contentSource = sequenceOf("declare var", "var", "const")
        .map { source.substringAfter("\n$it $name: {\n", "") }
        .filter { it.isNotEmpty() }
        .singleOrNull()
        ?: return null

    return contentSource
        .substringBefore(";\n};")
        .trimIndent()
        .removePrefix("prototype: $name;\n")
        .takeIf { it.isNotEmpty() }
}

private fun getConstructors(
    name: String,
    source: String,
): List<String> {
    val constructorSources = source
        .split(";\n")
        .map { it.substringAfterLast("\n") }
        .filter { it.startsWith("new(") || it.startsWith("new<") }
        .map { it.substringAfter("(") }
        .map { it.substringBefore("): $name") }

    if (constructorSources.isEmpty())
        return emptyList()

    if (constructorSources.singleOrNull() == "")
        return emptyList()

    return constructorSources
        .map { convertConstructor(it) }
}

private fun getCompanion(
    name: String,
    source: String,
): String {
    val content = source
        .substringAfterLast("\nnew(")
        .substringAfterLast("\nnew<")
        .substringAfter(";\n", "")
        .ifEmpty { return "" }

    val typeProvider = TypeProvider(name)
    val members = content
        .splitToSequence(";\n")
        .mapNotNull { convertMember(it, typeProvider) }
        .joinToString("\n")
        .ifEmpty { return "" }

    return "companion object {\n$members\n}"
}

private fun convertConstructor(
    parametersSource: String,
): String {
    if (parametersSource == "")
        return ""

    var parameters = convertFunctionParameters(parametersSource)

    // FormData
    parameters = parameters
        .replace("form: HTMLFormElement", "form: EventTarget /* HTMLFormElement */")
        .replace("submitter: HTMLElement", "submitter: EventTarget /* HTMLElement */")

    return "constructor($parameters)"
}

internal fun convertMember(
    source: String,
    typeProvider: TypeProvider,
): String? {
    if ("\n" in source) {
        val comment = source.substringBeforeLast("\n")
            .replace(";--\n", ";\n") // RESTORE

        if ("@deprecated" in comment)
            return null

        val member = convertMember(source.substringAfterLast("\n"), typeProvider)
            ?: return null

        return comment + "\n" + member
    }

    when (source) {
        "createElement<K extends keyof HTMLElementTagNameMap>(tagName: K, options?: ElementCreationOptions): HTMLElementTagNameMap[K]",
        -> return """
        fun <T: HTMLElement> createElement(
            tagName: HtmlTagName<T>,
            options: ElementCreationOptions = definedExternally,
        ): T    
        """.trimIndent()

        "createElementNS(namespaceURI: SVG_NAMESPACE, qualifiedName: string): SVGElement",
        -> return """
        fun <T : SVGElement> createElementNS(
            namespaceURI: SVG_NAMESPACE,
            qualifiedName: SvgTagName<T>,
        ): T        
        """.trimIndent()

        "createElementNS(namespaceURI: MATHML_NAMESPACE, qualifiedName: string): MathMLElement",
        -> return """
        fun <T : MathMLElement> createElementNS(
            namespaceURI: MATHML_NAMESPACE,
            qualifiedName: MathMLTagName<T>,
        ): T    
        """.trimIndent()

        "closest<K extends keyof HTMLElementTagNameMap>(selector: K): HTMLElementTagNameMap[K] | null",
        -> return "fun <T: HTMLElement> closest(selector: HtmlTagName<T>): T?"

        "closest<K extends keyof SVGElementTagNameMap>(selector: K): SVGElementTagNameMap[K] | null",
        -> return "fun <T: SVGElement> closest(selector: SvgTagName<T>): T?"

        "closest<K extends keyof MathMLElementTagNameMap>(selector: K): MathMLElementTagNameMap[K] | null",
        -> return "fun <T: MathMLElement> closest(selector: MathMLTagName<T>): T?"

        "closest<E extends Element = Element>(selectors: string): E | null",
        -> return "fun closest(selector: String): Element?"

        "getElementsByTagName<K extends keyof HTMLElementTagNameMap>(qualifiedName: K): HTMLCollectionOf<HTMLElementTagNameMap[K]>",
        -> return "fun <T: HTMLElement> getElementsByTagName(qualifiedName: HtmlTagName<T>): HTMLCollectionOf<T>"

        "getElementsByTagName<K extends keyof SVGElementTagNameMap>(qualifiedName: K): HTMLCollectionOf<SVGElementTagNameMap[K]>",
        -> return "fun <T: SVGElement> getElementsByTagName(qualifiedName: SvgTagName<T>): HTMLCollectionOf<T>"

        "getElementsByTagName<K extends keyof MathMLElementTagNameMap>(qualifiedName: K): HTMLCollectionOf<MathMLElementTagNameMap[K]>",
        -> return "fun <T: MathMLElement> getElementsByTagName(qualifiedName: MathMLTagName<T>): HTMLCollectionOf<T>"

        "getElementsByTagNameNS(namespaceURI: SVG_NAMESPACE, localName: string): HTMLCollectionOf<SVGElement>",
        -> return """
        fun <T : SVGElement> getElementsByTagNameNS(
            namespaceURI: SVG_NAMESPACE,
            localName: SvgTagName<T>,
        ): HTMLCollectionOf<T>
        """.trimIndent()

        "getElementsByTagNameNS(namespaceURI: MATHML_NAMESPACE, localName: string): HTMLCollectionOf<MathMLElement>",
        -> return """
        fun <T : MathMLElement> getElementsByTagNameNS(
            namespaceURI: MATHML_NAMESPACE,
            localName: MathMLTagName<T>,
        ): HTMLCollectionOf<T>
        """.trimIndent()
    }

    when {
        source.startsWith("createElement<") -> return null
        source.startsWith("createElementNS") && ("namespaceURI:" in source) -> return null
        source.startsWith("getElementsByTagName<") -> return null
        source.startsWith("getElementsByTagNameNS(namespaceURI: \"") -> return null
        source.startsWith("get location(") -> return null
        source.startsWith("set location(") -> return null

        source.startsWith("addEventListener<") -> return null
        source.startsWith("addEventListener(") -> return null
        source.startsWith("removeEventListener<") -> return null
        source.startsWith("removeEventListener(") -> return null
        source.startsWith("createEvent(") -> return null
        source.startsWith("toString()") -> return null

        source.startsWith("importScripts(") -> return null

        // deprecated
        source.startsWith("initStorageEvent(") -> return null
    }

    if ((source.startsWith("on") && "(this: " in source)) {
        val handlerName = source.substringBefore(": ")
            .removeSuffix("?")

        var eventType = source
            .substringAfter(": ")
            .substringAfter("ev: ")
            .substringBefore(")")

        when (eventType) {
            "MessageEvent",
            "ProgressEvent",
            -> eventType += "<*>"
        }

        return sequenceOf(
            "var $handlerName: EventHandler<$eventType>?",
            VAR_PROPERTY_DE.takeIf { typeProvider.isDefined() },
        ).filterNotNull()
            .joinToString("\n")
    }

    if (source.startsWith("[index: number]:") && typeProvider.isArrayLike())
        return null

    if (source.startsWith("["))
        return "    // $source"

    if ("(" in source) {
        val isFun = if (": " in source) {
            source.indexOf(": ") > source.indexOf("(")
        } else true

        if (isFun)
            return convertFunction(source, typeProvider)
    }

    return convertProperty(source, typeProvider)
}

private val VAL_PROPERTY_DE = """
get() = definedExternally
""".trimIndent()

private val VAR_PROPERTY_DE = """
get() = definedExternally
set(value) = definedExternally
""".trimIndent()

private fun convertProperty(
    source: String,
    typeProvider: TypeProvider,
): String? {
    val isVal = source.startsWith("readonly ")
    val modifier = if (isVal) "val" else "var"

    var (name, type) = source.removePrefix("readonly ").let {
        it.substringBefore(": ", "") to it.substringAfter(": ", "")
    }

    val optional = type.endsWith(" | null") || type.endsWith(" | undefined")
    type = type
        .removeSuffix(" | null")
        .removeSuffix(" | undefined")

    val safeName = name.removeSuffix("?")
    if (!typeProvider.accepted(safeName))
        return null

    // Window
    if (safeName == "frames" && type == "WindowProxy")
        type = "ArrayLike<Window>"

    if (safeName == "formTarget" && type == "string")
        type = "WindowName"

    type = when (type) {
        "null" -> "Void"
        "undefined" -> "Void"

        "any" -> "Any?"
        "string" -> "String"
        "boolean" -> "Boolean"

        "false",
        "true",
        -> "Boolean /* $type */"

        "number",
        "number | string",
        -> typeProvider.numberType(safeName)

        "string | DOMHighResTimeStamp",
        -> "HighResTimeStamp /* | String */"

        "number | CSSNumericValue | string",
        -> "Any /* $type */"

        """"submit" | "reset" | "button"""",
        -> "ButtonType"

        // RTC
        "number[]",
        -> "ReadonlyArray<Number>"

        // IntersectionObserverInit
        "number | number[]",
        -> "ReadonlyArray<Double>"

        "string | string[]",
        -> "Any /* $type */"

        "Promise<undefined>",
        -> "Promise<Void>"

        "IDBObjectStore | IDBIndex",
        "IDBObjectStore | IDBIndex | IDBCursor",
        -> "Any /* $type */"

        "IDBRequest",
        "IDBRequest<any>",
        -> "IDBRequest<*>"

        "1 | 2 | 3",
        -> "Int /* $type */"

        "NodeList",
        "HTMLCollection",
        -> "$type<*>"

        "BufferSource | Blob | string",
        "Client | ServiceWorker | MessagePort",
        -> "Any /* $type */"

        "OnErrorEventHandler",
        -> "Function<Unit>? /* $type */"

        "ChildNode" -> "Node /* ChildNode */"
        "ParentNode" -> "Node /* ParentNode */"
        "NodeListOf<ChildNode>" -> "NodeListOf<Node /* ChildNode */>"
        "Element | Document" -> "Element /* | Document */"

        // Audio
        "number[] | Float32Array",
        -> "ReadonlyArray<Double> /* | Float32Array */"

        "Record<string, number>",
        -> "ReadonlyRecord<String, Double>"

        "Record<string, string>",
        -> "ReadonlyRecord<String, String>"

        "BufferSource | string",
        -> "BufferSource /* | String */"

        // TEMP
        "DocumentTimeline",
        -> "dynamic /* $type */"

        // TEMP
        "StaticRange[]",
        -> "ReadonlyArray<Any /* StaticRange */>"

        // TEMP
        "DateTimeFormatPartTypes",
        -> "String /* $type */"

        "AudioContextLatencyCategory | number",
        -> "Any /* $type */"

        // MediaStreamConstraints
        "boolean | MediaTrackConstraints",
        -> "MediaTrackConstraints /* | Boolean */"

        "ReadableStream" -> "ReadableStream<*>"
        "ReadableStream<string>" -> "ReadableStream<String>"
        "WritableStream" -> "WritableStream<*>"
        "WritableStream<string>" -> "WritableStream<String>"

        "Promise<any>" -> "Promise<*>"
        "DOMHighResTimeStamp" -> "HighResTimeStamp"
        "ReadonlyArray<string>" -> "ReadonlyArray<String>"
        "ReadonlyArray<number>" -> "ReadonlyArray<Double>"
        "MediaList | string" -> "Any /* MediaList | string */"
        "Element | ProcessingInstruction" -> "Any /* Element | ProcessingInstruction */"
        "string | CanvasGradient | CanvasPattern" -> "Any /* string | CanvasGradient | CanvasPattern */"
        "string | ArrayBuffer" -> "Any /* string | ArrayBuffer */"
        "HTMLCanvasElement | OffscreenCanvas" -> "Any /* HTMLCanvasElement | OffscreenCanvas */"
        "(WindowProxy & typeof globalThis)" -> "WindowProxy"

        "HTMLCollectionOf<HTMLAnchorElement | HTMLAreaElement>",
        -> "HTMLCollectionOf<HTMLElement /* HTMLAnchorElement | HTMLAreaElement */>"

        "typeof FileReader.EMPTY | typeof FileReader.LOADING | typeof FileReader.DONE",
        -> "Short"

        else -> when {
            type.startsWith("0x") -> "Short"
            type.toIntOrNull() != null -> "Short"

            (type.endsWith("[]") && " " !in type)
            -> {
                var arrayType = type.removeSuffix("[]")
                arrayType = when (arrayType) {
                    "string" -> "String"
                    "boolean" -> "Boolean"
                    else -> arrayType
                }

                "ReadonlyArray<$arrayType>"
            }

            type.startsWith("\"")
            -> "String /* $type */"

            type.startsWith("(controller: ")
            -> type
                .replace(") => any", ") -> Unit")
                .replace(
                    ") => void | PromiseLike<void>",
                    ") -> PromiseLike<Void>?"
                )

            else -> type
        }
    }

    if (name.endsWith("?") || optional) {
        name = safeName

        if (!type.endsWith("?") && type != "Void") {
            type += "?"
        }
    }

    name = when (name) {
        "is", "as",
        -> "`$name`"

        else -> name
    }

    val mixinSugar = if (typeProvider.isDefined()) {
        if (isVal) VAL_PROPERTY_DE else VAR_PROPERTY_DE
    } else null

    return sequenceOf(
        "$modifier $name: $type",
        mixinSugar,
    ).filterNotNull()
        .joinToString("\n")
}

private fun convertFunction(
    source: String,
    typeProvider: TypeProvider,
): String? {
    val nameSource = source.substringBefore("(")
    val name = nameSource.substringBefore("<")
    val typeParameters = nameSource
        .removePrefix(name)
        .replace(" extends ", " : ")
        .replace(" | null", "?")

    if (!typeProvider.accepted(name))
        return null

    val parametersSource = source
        .substringAfter("(")
        .substringBefore("):")

    val parameters = convertFunctionParameters(parametersSource)

    val result = (": " + source.substringAfter("): "))
        .removeSuffix(": void")
        .replace(
            ": [ReadableStream<R>, ReadableStream<R>]",
            ": JsTuple2<ReadableStream<R>, ReadableStream<R>>",
        )
        .replace(
            "SVGCircleElement | SVGEllipseElement | SVGImageElement | SVGLineElement | SVGPathElement | SVGPolygonElement | SVGPolylineElement | SVGRectElement | SVGTextElement | SVGUseElement",
            "SVGElement /* SVGCircleElement | SVGEllipseElement | SVGImageElement | SVGLineElement | SVGPathElement | SVGPolygonElement | SVGPolylineElement | SVGRectElement | SVGTextElement | SVGUseElement */"
        )
        .replace(
            ": ReadableStreamReader<R>",
            ": ReadableStreamReader",
        )
        .replace(": DOMHighResTimeStamp", ": HighResTimeStamp")
        .replace(": OffscreenRenderingContext", ": Any /* OffscreenRenderingContext */")
        .replace(": RadioNodeList | Element | null", ": Any? /* RadioNodeList | Element */")
        .replace(": Promise<any>", ": Promise<*>")
        .replace(": Promise<number>", ": Promise<Number>")
        .replace(": Promise<PlaneLayout[]>", ": Promise<ReadonlyArray<PlaneLayout>>")
        .replace(": Promise<FontFace[]>", ": Promise<ReadonlyArray<FontFace>>")
        .replace(": Promise<MediaDeviceInfo[]>", ": Promise<ReadonlyArray<MediaDeviceInfo>>")
        .replace(": Promise<Notification[]>", ": Promise<ReadonlyArray<Notification>>")
        .replace(": Promise<IDBDatabaseInfo[]>", ": Promise<ReadonlyArray<IDBDatabaseInfo>>")
        .replace(": Promise<CryptoKeyPair | CryptoKey>", ": Promise<Any /* CryptoKeyPair | CryptoKey */>")
        .replace(": Promise<CryptoKeyPair | CryptoKey>", ": Promise<Any /* CryptoKeyPair | CryptoKey */>")
        .replace("<string[]", "<ReadonlyArray<String>")
        .replace(": StaticRange[]", ": ReadonlyArray<Any /* StaticRange */>")
        .replace(": (Gamepad | null)[]", ": ReadonlyArray<Gamepad?>")
        .replace(": RelativeTimeFormatPart[]", ": ReadonlyArray<dynamic /* RelativeTimeFormatPart */>")
        .replace(Regex("""\: (\w+?)\[\]"""), ": ReadonlyArray<$1>")
        .replace(
            """: { type: "element" | "literal", value: string; }[]""",
            ": ReadonlyArray<dynamic /* { type; value; } */>",
        )
        .replace(
            ": `\${string}-\${string}-\${string}-\${string}-\${string}`",
            ": String"
        )
        .replace(": IDBRequest<undefined>", ": IDBRequest<Void>")
        .replace(": IDBRequest<any>", ": IDBRequest<*>")
        .replace(": IDBRequest<number>", ": IDBRequest<Int>")
        .replace(": IDBRequest<string>", ": IDBRequest<String>")
        .replace(": IDBRequest<IDBValidKey[]>", ": IDBRequest<ReadonlyArray<IDBValidKey>>")
        .replace(": IDBRequest<any[]>", ": IDBRequest<ReadonlyArray<*>>")
        .replace(": number", ": Number")
        .replace(": string", ": String")
        .replace("<string>", "<String>")
        .replace(": boolean", ": Boolean")
        .replace("<boolean>", "<Boolean>")
        .replace(": any", ": Any")
        .replace("<void>", "<Void>")
        .replace(" | null", "?")
        .replace(" | undefined", "?")

    val safeName = when (name) {
        "continue" -> "`$name`"
        else -> name
    }

    val parameterCount = parameters.count { it == ':' }
    val isOperator = when (name) {
        "get" -> parameterCount == 1 && ":" in result
        "set" -> parameterCount == 2 && ":" !in result
                && "vararg " !in parameters
                && " = definedExternally" !in parameters

        else -> false
    }

    val modifier = if (isOperator) "operator" else ""

    val mixinSugar = if (typeProvider.isDefined()) {
        sequenceOf(
            ": Unit".takeIf { result.isEmpty() },
            " = definedExternally",
        ).filterNotNull()
            .joinToString("")
    } else ""

    return "$modifier fun $typeParameters$safeName($parameters)$result$mixinSugar"
}

private fun convertFunctionParameters(
    source: String,
): String {
    val parameters = when (source) {
        "...data: any[]",
        "condition?: boolean, ...data: any[]",
        "label?: string, ...data: any[]",
        -> listOf(source.substringBefore(", ", ""))
            .filter { it.isNotEmpty() }
            .map { convertFunctionParameters(it) }
            .plus("vararg data: Any?")

        "...nodes: (Element | Text)[]",
        -> listOf(
            "vararg nodes: Element /* | Text */",
        )

        "...nodes: (Node | string)[]",
        -> listOf(
            "vararg nodes: Any /* Node | string */",
        )

        "property: string, ...values: (CSSStyleValue | string)[]",
        -> listOf(
            "property: String",
            "vararg values: Any /* CSSStyleValue | string */",
        )

        "...args: CSSNumberish[]",
        "...value: CSSNumberish[]",
        "...values: CSSNumberish[]",
        "...units: string[]",

        "...text: string[]",
        "...tokens: string[]",
        "...streams: MediaStream[]",
        -> listOf(
            source
                .replace("...args", "...values")
                .replace("...", "vararg ")
                .removeSuffix("[]")
                .replace(": string", ": String")
        )

        "track: MediaStreamTrack, ...streams: MediaStream[]",
        -> listOf(
            "track: MediaStreamTrack",
            "vararg streams: MediaStream",
        )

        "items: Record<string, string | Blob | PromiseLike<string | Blob>>, options?: ClipboardItemOptions",
        -> listOf(
            "items: ReadonlyRecord<String, Any /* String | Blob | PromiseLike<String | Blob> */>",
            "options: ClipboardItemOptions = definedExternally",
        )

        "transform: ReadableWritablePair<T, R>, options?: StreamPipeOptions",
        -> listOf(
            "transform: ReadableWritablePair<T, R>",
            "options: StreamPipeOptions = definedExternally",
        )

        "transformer?: Transformer<I, O>, writableStrategy?: QueuingStrategy<I>, readableStrategy?: QueuingStrategy<O>",
        -> listOf(
            "transformer: Transformer<I, O> = definedExternally",
            "writableStrategy: QueuingStrategy<I> = definedExternally",
            "readableStrategy: QueuingStrategy<O> = definedExternally",
        )

        "action: (item: AudioParam) => void",
        "action: (item: CSSNumericValue) => void",
        "action: (item: CSSTransformComponent) => void",
        "action: (item: CSSUnparsedSegment) => void",
        "action: (item: CSSStyleValue[]) => void",
        "action: (item: FontFace) => void",
        "action: (item: T) => void",
        "action: (item: string) => void",
        "action: (item: FormDataEntryValue) => void",
        "action: (item: MediaKeyStatus) => void",
        "action: (item: MIDIInput) => void",
        "action: (item: MIDIOutput) => void",
        "action: (item: any) => void",
        -> listOf(
            source
                .replace(": string", ": String")
                .replace(": any", ": Any?")
                .replace(": CSSStyleValue[]", ": ReadonlyArray<CSSStyleValue>")
                .replace(" => void", " -> Unit"),
        )

        else -> source
            .splitToSequence(", ")
            .filter { it.isNotEmpty() }
            .map {
                var (pname, ptype) = it.split(": ")
                ptype = getParameterType(pname.removeSuffix("?"), ptype)

                if (pname.endsWith("?")) {
                    pname = pname.removeSuffix("?")
                    ptype += " = definedExternally"
                }

                pname = when {
                    pname.endsWith("InitDict") ||
                            pname.endsWith("EntryInit")
                    -> "init"

                    pname == "when"
                    -> "`$pname`"

                    else -> pname
                }

                "$pname: $ptype"
            }
            .toList()
    }

    return if (parameters.size > 1) {
        parameters.joinToString(",\n", "\n", ",\n")
    } else parameters.joinToString("\n")
}

private fun getParameterType(
    name: String,
    source: String,
): String {
    if (source.endsWith(" | null")) {
        var type = getParameterType(name, source.removeSuffix(" | null"))
        if ("? /* " !in type)
            type += "?"

        return type
    }

    return when {
        source == "number | DOMPointInit | (number | DOMPointInit)[]"
        -> "Any /* $source */"

        source == "File | string | FormData"
        -> "Any /* File | String | FormData */"

        source == "DateTimeFormatPartTypes"
        -> "String /* $source */"

        // TEMP
        source == "AlgorithmIdentifier"
        -> "Any /* $source */"

        source == "Iterable<string>"
        -> "JsIterable<String>"

        source.startsWith("\"")
        -> "String /* $source */"

        source == "any"
        -> "Any?"

        source == "string"
        -> "String"

        source == "bigint"
        -> "BigInt"

        source == "number"
        -> when (name) {
            "code" -> "Short"
            "index" -> "Int"
            else -> "Number"
        }

        source == "boolean"
        -> "Boolean"

        source == "Promise<any>"
        -> "Promise<*>"

        source == "PromiseLike<PaymentDetailsUpdate>"
        -> "Promise<PaymentDetailsUpdate> /* PromiseLike */"

        source == "ReadableStream"
        -> "ReadableStream<*>"

        source == "DOMHighResTimeStamp"
        -> "HighResTimeStamp"

        source.endsWith("[]") -> {
            var atype = source.removeSuffix("[]")
            if (atype == "string")
                atype = "String"

            if (atype == "number")
                atype = "Double"

            "ReadonlyArray<$atype>"
        }

        else -> source
    }
}
