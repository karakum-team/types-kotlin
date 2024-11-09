package karakum.browser

import karakum.common.withSuspendAdapter
import karakum.events.EventDataRegistry

internal const val VIDEO_FRAME_REQUEST_ID = "VideoFrameRequestId"
internal const val RENDERING_CONTEXT_ID = "RenderingContextId"
internal const val GEOLOCATION_WATCH_ID = "GeolocationWatchId"

private val DEPRECATED = setOf(
    "HTMLAllCollection",
)

private val ANIMATION_TYPES = setOf(
    "Animation",
    "AnimationEffect",
    "AnimationTimeline",

    "ComputedEffectTiming",
    "EffectTiming",
    "OptionalEffectTiming",

    "ComputedKeyframe",
    "Keyframe",
    "KeyframeAnimationOptions",
    "KeyframeEffect",
    "KeyframeEffectOptions",
    "PropertyIndexedKeyframes",

    "DocumentTimeline",
    "DocumentTimelineOptions",
)

private val RANGES_TYPES = setOf(
    "AbstractRange",
    "Range",
    "StaticRange",
    "StaticRangeInit",
)

private val DOM_TYPES = setOf(
    // separate package?
    "CaretPosition",
    "CaretPositionFromPointOptions",

    "DOMStringList",
    "DOMStringMap",
    "DOMTokenList",

    "Animatable",
    "GetAnimationsOptions",

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
    "Text",
    "NodeIterator",

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
    "GetHTMLOptions",

    "DocumentAndElementEventHandlers",
    "GlobalEventHandlers",

    // separate package?
    "PointerLockOptions",
)

private val WEB_COMPONENTS_TYPES = setOf(
    "AssignedNodesOptions",

    "CustomElementRegistry",
    "CustomElementConstructor",
    "ElementDefinitionOptions",

    "CustomStateSet",
    "ElementInternals",
    "HTMLSlotElement",
    "HTMLTemplateElement",

    "ShadowRoot",
    "ShadowRootInit",
    "Slottable",
)

private val VALIDATION_TYPES = setOf(
    "ValidityState",
    "ValidityStateFlags",
)

private val EVENTS_TYPES = setOf(
    "AddEventListenerOptions",
    "EventListenerOptions",
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

private val VIEW_TRANSITION_TYPES = listOf(
    "ViewTransition",
    "ViewTransitionTypeSet",
)

private val NAVIGATION_TYPES = listOf(
    "Navigation",
    "NavigationActivation",
    "NavigationDestination",
    "NavigationHistoryEntry",
    "NavigationTransition",
)

private val HIGHLIGHT_TYPES = listOf(
    "Highlight",
    "HighlightRegistry",
)

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

private val IMAGES_TYPES = listOf(
    "ImageData",
    "ImageDataSettings",

    "ImageBitmap",
    "ImageBitmapOptions",
    "ImageBitmapRenderingContext",
    "ImageBitmapRenderingContextSettings",
)

private val CANVAS_TYPES = listOf(
    "ImageEncodeOptions",

    "Path2D",
    "TextMetrics",

    "OffscreenCanvas",
    "OffscreenCanvasRenderingContext2D",
)

private val FORM_TYPES = listOf(
    "FormData",
)

private val HTTP_TYPES = listOf(
    "Body",
    "Request",
    "RequestInit",
    "Response",
    "ResponseInit",

    "Headers",
    "HeadersInit",
)

private val MEDIA_STREAM_TYPES = listOf(
    "CanvasCaptureMediaStreamTrack",
    "OverconstrainedError",

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
    "MediaSourceHandle",
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

private val WORKLETS_TYPES = listOf(
    "Worklet",
    "WorkletOptions",
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
    "AuthenticationExtensionsPRFInputs",
    "AuthenticationExtensionsPRFOutputs",
    "AuthenticationExtensionsPRFValues",
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
    "AuthenticationExtensionsClientInputsJSON",
    "PublicKeyCredentialCreationOptionsJSON",
    "PublicKeyCredentialDescriptorJSON",
    "PublicKeyCredentialRequestOptionsJSON",
    "PublicKeyCredentialUserEntityJSON",
)

private val CREDENTIALS_TYPES = listOf(
    "Credential",
    "CredentialCreationOptions",
    "CredentialRequestOptions",
    "CredentialsContainer",
)

private val ABORT_TYPES = listOf(
    "AbortController",
    "AbortSignal",
)

private val ERROR_TYPES = listOf(
    DOM_EXCEPTION,
)

private val SCHEDULING_TYPES = listOf(
    "IdleDeadline",
    "IdleRequestOptions",
)

private val WEB_SERIALIZATION = listOf(
    "StructuredSerializeOptions"
)

private val MESSAGING_TYPES = listOf(
    "MessageChannel",
    "MessagePort",
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
    "AudioData",
    "AudioDataCopyToOptions",
    "AudioDataInit",
    "AudioDecoder",
    "AudioDecoderConfig",
    "AudioDecoderInit",
    "AudioDecoderSupport",
    "AudioEncoder",
    "AudioEncoderConfig",
    "AudioEncoderInit",
    "AudioEncoderSupport",
    "AvcEncoderConfig",
    "EncodedAudioChunk",
    "EncodedAudioChunkInit",
    "EncodedAudioChunkMetadata",
    "EncodedVideoChunk",
    "EncodedVideoChunkInit",
    "EncodedVideoChunkMetadata",
    "ImageDecodeOptions",
    "ImageDecoder",
    "ImageDecodeResult",
    "ImageDecoderInit",
    "ImageTrack",
    "ImageTrackList",
    "OpusEncoderConfig",
    "PlaneLayout",
    "VideoColorSpace",
    "VideoColorSpaceInit",
    "VideoDecoder",
    "VideoDecoderConfig",
    "VideoDecoderInit",
    "VideoDecoderSupport",
    "VideoEncoder",
    "VideoEncoderConfig",
    "VideoEncoderEncodeOptions",
    "VideoEncoderEncodeOptionsForAvc",
    "VideoEncoderInit",
    "VideoEncoderSupport",
    "VideoFrame",
    "VideoFrameBufferInit",
    "VideoFrameCopyToOptions",
    "VideoFrameInit",
)

private val FILE_SYSTEM_TYPES = listOf(
    "WriteParams",
)

private val STREAMS_TYPES = listOf(
    "ByteLengthQueuingStrategy",
    "CountQueuingStrategy",
    "GenericTransformStream",
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
    "ReadableStreamIteratorOptions",
    "ReadableStreamReadDoneResult",
    "ReadableStreamReadResult",
    "ReadableStreamReadValueResult",
    "ReadableWritablePair",
    "StreamPipeOptions",
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

private val COMPRESSION_STREAMS_TYPES = listOf(
    "CompressionStream",
    "DecompressionStream",
)

private val PAYMENT_TYPES = listOf(
    "PayerErrors",
    "AddressErrors",
)

private val URL_TYPES = listOf(
    "FragmentDirective",
    "URL",
    "URLSearchParams",
)

private val REPORTING_TYPES = listOf(
    "CSPViolationReportBody",
    "DeprecationReportBody",
    "InterventionReportBody",
    "Report",
    "ReportBody",
    "ReportError",
    "ReportingObserver",
    "ReportingObserverOptions",
)

private val XSLT_PROCESSOR = "XSLTProcessor"

internal fun htmlDeclarations(
    source: String,
): Sequence<ConversionResult> {
    val content = source.replace(";\n     *", ";--\n     *")

    val getStaticSource = { name: String ->
        getStaticSource(name, content)
    }

    val interfaces =
        Regex("""\ninterface .+? \{[\s\S]*?\n}""")
            .findAll(content)
            .map { it.value.removePrefix("\n") }
            .mapNotNull { src ->
                convertInterface(src, getStaticSource)
                    ?.withComment(fullSource = content, source = src)
            }
            // TEMP (WebGL)
            .filter { "_" !in it.name }
            // custom
            .filter { it.name != "EventCounts" }
            .filter { it.name != "NodeListOf" }
            // duplicates
            .filter { it.name != "ClientQueryOptions" }
            .filter { it.name != "FileSystemFileHandle" }

    return interfaces
        .plus(formTypes())
        .plus(customElementTypes())
        .plus(
            ConversionResult(
                RENDERING_CONTEXT_ID,
                "sealed interface $RENDERING_CONTEXT_ID<T: Any, O: Any>",
                "web.rendering",
            )
        )
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
            sequenceOf(
                "web.dom" to NODE_TYPE,
                "web.events" to EVENT_PHASE,
                "web.html" to VIDEO_FRAME_REQUEST_ID,
                "web.geolocation" to GEOLOCATION_WATCH_ID,
                "web.uievents" to KEY_LOCATION,
                "web.uievents" to DELTA_MODE,
            ).map { (pkg, name) ->
                ConversionResult(
                    name = name,
                    body = "sealed external interface $name",
                    pkg = pkg,
                )
            }
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
        .plus(Abortable())
        .plus(abortInternal())
        .plus(Ed25519())
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
        // TEMP
        name.startsWith("WebGL") -> return null

        name in KNOWN_MISSED_TYPES -> return null
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
        val newSource = source
            .replace(" type: string;", " type: InputType;")

        return convertInterface(
            source = newSource,
            getStaticSource = getStaticSource,
            predefinedPkg = predefinedPkg,
        )
    }

    when (name) {
        "Global",
        "GlobalDescriptor",
            -> {
            if ("<T extends ValueType = ValueType>" in source) {
                val newSource = source
                    .replace("<T extends ValueType = ValueType>", "<T>")
                    .replace("value: T", "value: ValueType<T>")
                    .replace(": ValueTypeMap[T]", ": T")

                val newGetStaticSource: (String) -> String? = {
                    getStaticSource(it)?.replace(": ValueTypeMap[T]", ": T")
                }
                return convertInterface(
                    source = newSource,
                    getStaticSource = newGetStaticSource,
                    predefinedPkg = predefinedPkg,
                )
            }
        }
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
        DOM_EXCEPTION,
            -> declaration.replace(" extends Error", " :\nJsError")

        "AbstractWorker",
        "GlobalEventHandlers",
        "WindowEventHandlers",
            -> "$declaration :\n$EVENT_TARGET"

        // Details - https://github.com/microsoft/TypeScript-DOM-lib-generator/issues/1845
        "Screen",
            -> "$declaration :\n$EVENT_TARGET"

        in COLLECTIONS_WITH_BOUNDS,
            -> declaration
            .replaceFirst(" extends HTMLCollectionBase", " :\nHTMLCollectionBase<T>")
            .replaceFirst(" extends ", " : ")
            .replace(" extends ", " :\n")

        "CountQueuingStrategy",
            -> declaration
            .replaceFirst(" extends QueuingStrategy", " :\nQueuingStrategy<Void>")

        "HTMLFormControlsCollection",
            -> declaration
            .replaceFirst(" extends HTMLCollectionBase", " :\nHTMLCollectionBase<Element>")

        "ElementDefinitionOptions",
            -> declaration.replaceFirst("ElementDefinitionOptions", "ElementDefinitionOptions<T : HTMLElement>")

        "RadioNodeList",
            -> declaration.replaceFirst(" extends NodeList", " :\nNodeList<HTMLElement>")

        else -> {
            declaration
                .replace(" extends ", " :\n")
                .let { if (":" in it) it.replace(", ", ",\n") else it }
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
            body = "typealias $name<T /* : HTMLElement */> = JsClass<T>",
            pkg = "web.components",
        )

    val typeGuardSource = sequenceOf(
        "done: false",
        "done: true",
    ).firstOrNull { memberSource.startsWith("$it;") }

    if (typeGuardSource != null) {
        declaration = declaration.replace("interface ", "class ")
        memberSource = memberSource.removePrefix("$typeGuardSource;\n")
    }

    val hasTypeGuard = typeGuardSource != null

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

    val abortable = memberSource.substringAfter("signal?: AbortSignal", "-")
        .removePrefix(" | null")
        .let { it.startsWith(";") || it.isEmpty() }

    val mapLikeParameters = if (iterableTypeParameter != null) {
        mapLikeParameters(iterableTypeParameter)
    } else null

    if (iterableTypeParameter != null) {
        val typeParameter = arrayType ?: iterableTypeParameter
        val iterableDeclaration = when {
            mapLikeParameters != null
                -> {
                val mapLikeType = when (name) {
                    "URLSearchParams",
                    "MediaKeyStatusMap",
                        -> "ReadonlyMap"

                    else -> "MapLike"
                }

                val result = "$mapLikeType<${mapLikeParameters.key}, ${mapLikeParameters.value}>"
                if (name == "StylePropertyMapReadOnly") {
                    "$result,\nReadonlySetLike<${mapLikeParameters.key}>"
                } else result
            }

            listLikeMode
                -> "ListLike<$typeParameter>"

            else -> "JsIterable<$typeParameter>"
        }

        declaration += if (":" in declaration && name != "NodeList") "," else ":"
        declaration += "\n$iterableDeclaration"
    }

    sequenceOf(MarkerRegistry.additionalParents(name))
        .filterNotNull()
        .flatten()
        .plus(ABORTABLE.takeIf { abortable })
        .filterNotNull()
        .forEach { additionalParent ->
            declaration += if (":" in declaration) "," else ":"
            declaration += "\n$additionalParent"
        }

    val additionalIterableParent = IterableRegistry.additionalParent(name)
    if (additionalIterableParent != null) {
        declaration += if (":" in declaration) "," else ":"
        declaration += "\n$additionalIterableParent"
    }

    when (name) {
        "ElementInternals" -> declaration += ",\n$VALIDATION_TARGET"
        in WELL_KNOWN_FORM_CONTROL -> declaration += ",\n$FORM_CONTROL"
    }

    val hideForEach = when {
        listLikeMode -> true
        mapLikeParameters != null -> true
        additionalIterableParent == null -> false
        additionalIterableParent.startsWith("ReadonlyMap<") -> true
        additionalIterableParent.startsWith("MutableMapLike<") -> true
        additionalIterableParent.startsWith("MutableSetLike<") -> true
        else -> false
    }

    val typeProvider = TypeProvider(
        parentType = name,
        arrayType = arrayType,
        hideForEach = hideForEach
    )

    var mainConstructor: String
    val additionalConstructors: String
    if (staticSource != null) {
        val constructors = getConstructors(name, staticSource, typeProvider)
        val firstConstructor = constructors.firstOrNull()
        mainConstructor = if (firstConstructor != null) {
            val result = firstConstructor.removePrefix("constructor")
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

    if (name == DOM_EXCEPTION) {
        mainConstructor = mainConstructor.replace("name: String", "name: JsErrorName")
    }

    val isHtmlElementClass = IDLRegistry.hasHtmlConstructor(name)
    val isSvgClass = type == "class" && name.startsWith("SVG")
    val isSvgElementClass = isSvgClass && name.endsWith("Element")

    if (isHtmlElementClass || isSvgElementClass || hasTypeGuard) {
        require(mainConstructor.isEmpty())
        require(":\n" in declaration)

        val modifier = if (isHtmlElementClass) "protected" else "private"
        declaration = declaration.replaceFirst(":\n", "\n$modifier constructor():\n")
    }

    val hasPrivateConstructor = type == "class"
            && mainConstructor.isEmpty()
            && (
            name.startsWith("CSS")
                    || name.startsWith("GPU")
                    || name.startsWith("IDB")
                    || name.startsWith("FileSystem")
                    || name.startsWith("MIDI")
                    || name.startsWith("RTC")
            )

    if (isSvgClass && !isSvgElementClass && !name.endsWith("List") || hasPrivateConstructor) {
        mainConstructor = "\nprivate constructor()\n"
    }

    if (mainConstructor.isNotEmpty()) {
        declaration = if (":\n" in declaration) {
            val suffix = if ("()" in mainConstructor) "\n" else ""
            declaration.replaceFirst(":\n", "$mainConstructor:$suffix")
        } else declaration + mainConstructor
    }

    var members = if (memberSource.isNotEmpty()) {
        var result = memberSource
            .splitToSequence(";\n")
            .mapNotNull { convertMember(it, typeProvider) }
            .joinToString("\n")

        result = when (name) {
            DOM_EXCEPTION,
                -> result
                .replace("val message: String", "override val message: String")
                .replace("val name: String", "val name: JsErrorName")
                .splitToSequence("\n")
                .filter { !it.endsWith(": Short") }
                .joinToString("\n")

            "URLSearchParams",
            "MediaKeyStatusMap",
                -> result
                .replace("val size: Int", "override val size: Int")
                .replaceFirst("fun get(", "override fun get(")
                .replaceFirst("fun has(", "override fun has(")

            "StylePropertyMapReadOnly",
                -> result
                .replace("val size: Int", "override val size: Int")
                .replaceFirst("fun has(", "override fun has(")

            "Highlight",
                -> result + "\n\n" + mutableSetLikeOverrides("AbstractRange")

            "Node",
                -> result
                .replace("val ownerDocument:", "open val ownerDocument:")

            in WELL_KNOWN_FORM_CONTROL,
                -> result.applyFormControlPatch().let {
                if (name == "HTMLSelectElement") {
                    it.replace("fun remove()", "override fun remove()")
                } else it
            }

            "ElementInternals",
                -> result.applyValidationTargetPatch()

            in LENGTH_REQUIRED,
                -> result
                .replace("val length: Int", "override val length: Int")

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

            "HTMLOptionsCollection",
                -> result
                .replace("var length: Int", "override var length: Int")

            "CustomElementRegistry",
                -> result
                .replace("CustomElementConstructor", "CustomElementConstructor<T>")
                .replace("ElementDefinitionOptions", "ElementDefinitionOptions<P>")
                .replaceFirst("fun define(", "fun <T: P, P : HTMLElement> define(")
                .replace("fun get(", "fun <T : HTMLElement> get(")
                .replace("fun getName(", "fun <T : HTMLElement> getName(")
                .replace("fun whenDefined(", "fun <T : HTMLElement> whenDefined(")
                .replace("fun whenDefinedAsync(", "fun <T : HTMLElement> whenDefinedAsync(")
                .replace(": String", ": HtmlTagName<T>")

            "ElementDefinitionOptions",
                -> result.replace(": String", ": HtmlTagName<T>")

            "CanvasPathDrawingStyles",
                -> result
                .replace("Number[]", "ReadonlyArray<Double>")
                .replace("<number>", "<Double>")

            "GPUBindGroup",
            "GPUBindGroupLayout",
            "GPUBuffer",
            "GPUCommandBuffer",
            "GPUCommandEncoder",
            "GPUDevice",
            "GPUPipelineLayout",
            "GPUQuerySet",
            "GPUQueue",
            "GPURenderBundle",
            "GPUSampler",
            "GPUShaderModule",
            "GPUTexture",
            "GPUTextureView",
                -> result
                .replace("var label: String", "override var label: String")

            "GPUComputePipeline",
            "GPURenderPipeline",
                -> result
                .replace("var label: String", "override var label: String")
                .replace("fun getBindGroupLayout(", "override fun getBindGroupLayout(")

            "GPUComputePassEncoder",
            "GPURenderBundleEncoder",
            "GPURenderPassEncoder",
                -> {
                var overriddenMethods = setOf(
                    "draw",
                    "drawIndexed",
                    "drawIndexedIndirect",
                    "drawIndirect",
                    "insertDebugMarker",
                    "popDebugGroup",
                    "pushDebugGroup",
                    "setBindGroup",
                    "setIndexBuffer",
                    "setPipeline",
                    "setVertexBuffer",
                )

                if (name == "GPUComputePassEncoder")
                    overriddenMethods = overriddenMethods - "setPipeline"

                val initial = result.replace("var label: String", "override var label: String")
                overriddenMethods.fold(initial) { acc, methodName ->
                    Regex("""fun $methodName\(.*?\)""", RegexOption.DOT_MATCHES_ALL).replace(acc) {
                        "override " + it.value.replace(" = definedExternally", "")
                    }
                }
            }

            "RTCInboundRtpStreamStats",
                -> result.replace("var kind:", "override var kind:")

            "CompressionStream",
            "DecompressionStream",
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
            "LargestContentfulPaint",
                -> result
                .replace("val target: Node?", "val target: EventTarget /* Node */?")
                .replace("fun toJSON()", "override fun toJSON()")

            "QueuingStrategy",
                -> result.replace("var ", "val ")

            "ByteLengthQueuingStrategy",
            "CountQueuingStrategy",
                -> result.replace("val ", "override val ")

            "FileSystemHandle",
                -> result.replace("val kind:", "open val kind:")

            "FileSystemDirectoryHandle",
            "FileSystemFileHandle",
                -> result.replace("val kind:", "override val kind:")

            else -> {
                if (abortable) {
                    result.replace("val signal: AbortSignal?", "override val signal: AbortSignal?")
                } else result
            }
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

    val annotations = if (IDLRegistry.isPlainObjectInterface(name)) {
        require(declaration.startsWith("interface ")) {
            "JSO `$name` should be interface"
        }

        require("= definedExternally" !in members) {
            "JSO `$name` should not contain default properties"
        }

        when (name) {
            "QueuingStrategy",
                -> "// @JsPlainObject"

            else -> "@JsPlainObject"
        }
    } else {
        ""
    }

    val modifier = when {
        name == DOM_EXCEPTION ||
                name == "Animation" ||
                name == "Blob" ||
                name == "DOMMatrixReadOnly" ||
                name == "DOMPointReadOnly" ||
                name == "DOMRectReadOnly" ||
                name == "Worker" ||
                name == "Credential" ||
                name == "WritableStream" ||
                name == "AudioWorkletNode" ||
                name == "OfflineAudioContext" ||
                name == "OfflineAudioContext" ||
                name == "BroadcastChannel" ||
                name == "OffscreenCanvas" ||
                name == "AudioDecoder" ||
                name == "AudioEncoder" ||
                name == "VideoDecoder" ||
                name == "VideoEncoder" ||
                name == "MediaRecorder" ||
                name == "MediaSource" ||
                name == "MediaStream" ||
                name == "Notification" ||
                name == "PaymentRequest" ||
                name == "RTCPeerConnection" ||
                name == "WebSocket" ||
                name == "SpeechSynthesisUtterance" ||
                name == "EventSource" ||
                name == "SharedWorker" ||
                name == "AudioContext" ||

                // with empty constructor
                name == "Text" ||
                name == "Comment" ||
                name == "DataTransfer" ||
                name == "FileReader" ||
                name == "XMLHttpRequest" ||
                name == "TextEncoder" ||
                name == "TextEncoderStream" ||
                name == "MessageChannel" ||
                name == "AbortController" ||
                name == XSLT_PROCESSOR ||

                name == "Document" ||
                name == "DocumentFragment" ||

                name == "CSSRule" ||
                name == "CSSConditionRule" ||
                name == "CSSGroupingRule" ||
                name == "CSSMathValue" ||
                name == "CSSNumericValue" ||
                name == "CSSStyleValue" ||
                name == "CSSTransformComponent" ||

                name == "GPUError" ||
                name == "GPUInternalError" ||
                name == "GPUOutOfMemoryError" ||
                name == "GPUValidationError" ||

                name == "IDBCursor" ||
                name == "IDBRequest" ||

                name == "MIDIPort" ||

                name == "FileSystemEntry" ||
                name == "FileSystemHandle" ||

                isHtmlElementClass ||
                isSvgElementClass
            -> "open"

        // TEMP
        hasPrivateConstructor && (
                name == "FileSystemDirectoryHandle"
                        || name == "MIDIInputMap"
                        || name == "MIDIOutputMap"
                        || name == "RTCStatsReport"
                )
            -> "sealed /* final */\n"

        annotations.startsWith("@") ||
                name in CSSOM_INTERFACES ||
                name == "AudioWorkletProcessorImpl" ||
                name == "AbstractWorker" ||
                name == "FontFaceSource" ||
                name == "XPathEvaluatorBase" ||
                name == "ARIAMixin" ||
                name == "HTMLOrSVGElement" ||
                name == "DocumentOrShadowRoot" ||
                name == "Slottable" ||
                name.endsWith("Handlers") ||

                mainConstructor.isNotEmpty() ||
                IDLRegistry.hasEmptyConstructor(name) ||
                hasPrivateConstructor ||
                hasTypeGuard
            -> ""

        name == "PerformanceEntry" ||
                name == "Element" ||
                name == "NodeList" ||
                name == "WorkerGlobalScope" ||
                name == "Worklet" ||
                name == "WorkletGlobalScope" ||
                name == "AudioWorkletProcessor"
            -> "abstract"

        else -> "sealed"
    }

    val idDeclaration = RenderingContextRegistry.getIdDeclaration(name)
    val companion = if (staticSource != null) {
        val companionContent = getCompanion(name, staticSource)
        when {
            name == DOM_EXCEPTION -> {
                companionContent
                    .splitToSequence("\n")
                    .filter { !it.endsWith(": Short") }
                    .joinToString("\n")
                    .replaceFirst("\n}", domExceptionErrorNames() + "\n}")
            }

            idDeclaration != null -> {
                require(companionContent.isEmpty())
                """
                companion object {
                    $idDeclaration
                }    
                """.trimIndent()
            }

            else -> companionContent
        }
    } else {
        require(idDeclaration == null)
        ""
    }

    val typeGuard = if (typeGuardSource != null) {
        val (property, value) = typeGuardSource.split(": ")
        """
        @JsTypeGuard(
            property = "$property",
            value = "$value",
        )
        """.trimIndent()
    } else ""

    val additionalAliases = getAdditionalAliasNames(name)
        ?.joinToString("\n") { "sealed interface $it" }
        ?: ""

    var body = sequenceOf(
        typeGuard,
        annotations,
        "$modifier external $declaration {",
        members,
        companion,
        additionalAliases,
        "}",
    ).filter { it.isNotEmpty() }
        .joinToString("\n")

    if (memberSource == "new (options: any): AudioWorkletProcessorImpl")
        body = "typealias $name = JsClass<out AudioWorkletProcessorImpl>"

    when (name) {
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

        name in WEB_SERIALIZATION -> "web.serialization"

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

        name in IMAGES_TYPES -> "web.images"

        name.startsWith("Canvas") && name != "CanvasCaptureMediaStreamTrack" -> "web.canvas"
        name in CANVAS_TYPES -> "web.canvas"

        name in ANIMATION_TYPES -> "web.animations"

        name.startsWith("Clipboard") -> "web.clipboard"

        name == "History" -> "web.history"
        name == "Location" -> "web.location"

        name.startsWith("DeviceMotion") -> "web.device"

        name.startsWith("FileSystem") -> "web.fs"
        name in FILE_SYSTEM_TYPES -> "web.fs"

        name.startsWith("Blob") -> "web.blob"

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
        name in FORM_TYPES -> "web.form"
        name in HTTP_TYPES -> "web.http"
        name.startsWith("XMLHttp") -> "web.xhr"

        name == "ARIAMixin" -> "web.aria"
        name == "Selection" -> "web.selection"

        name == "EventModifierInit" -> "web.uievents"

        name.startsWith("Document") -> "web.dom"
        name in RANGES_TYPES -> "web.ranges"
        name in DOM_TYPES -> "web.dom"
        name in WEB_COMPONENTS_TYPES -> "web.components"
        name in VALIDATION_TYPES -> "web.validation"
        name in CSSOM_TYPES -> "web.cssom"
        name in NAVIGATION_TYPES -> "web.navigation"
        name in VIEW_TRANSITION_TYPES -> "web.viewtransition"
        name in HIGHLIGHT_TYPES -> "web.highlight"
        name in DOM_DATA_TYPES -> "web.data"
        name in DOM_GEOMETRY_TYPES -> "web.geometry"
        name == "XMLDocument" -> "web.xml"

        name.startsWith("XPath") -> "web.xpath"

        name in WEB_AUDIO_TYPES -> "web.audio"

        name.startsWith("RTC") -> "web.rtc"
        name.startsWith("MediaKey") -> "web.media.key"

        name.startsWith("VTT") -> "web.vtt"
        name.startsWith("TextTrack") -> "web.vtt"

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

        name in WORKLETS_TYPES -> "web.worklets"
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

        name in STREAMS_TYPES -> "web.streams"
        name in COMPRESSION_STREAMS_TYPES -> "web.compression"
        name.startsWith("WebTransport") -> "web.transport"

        name in WEB_AUTHN_TYPES -> "web.authn"
        name in CREDENTIALS_TYPES -> "web.credentials"
        name in ABORT_TYPES -> "web.abort"
        name in ERROR_TYPES -> "web.errors"
        name in SCHEDULING_TYPES -> "web.scheduling"
        name in MESSAGING_TYPES -> "web.messaging"
        name in WEB_CRYPTO_TYPES -> "web.crypto"

        name.startsWith("IDB") -> "web.idb"
        name.startsWith("Push") -> "web.push"

        name.startsWith("Performance") -> "web.performance"
        name == "LargestContentfulPaint" -> "web.performance"

        name.startsWith("Window") -> "web.window"
        name == "BarProp" -> "web.window"
        name == "PictureInPictureWindow" -> "web.pip"
        name == "VisualViewport" -> "web.viewport"

        name in EVENTS_TYPES -> "web.events"
        name in SCROLL_TYPES -> "web.scroll"
        name in FULLSCREEN_TYPES -> "web.fullscreen"

        name == "BroadcastChannel" -> "web.broadcast"
        name == "WebSocket" -> "web.sockets"

        name in QUERY_TYPES -> "web.cache"
        name in CODECS_TYPES -> "web.codecs"

        name in USER_ACTIVATION_TYPES -> "web.useractivation"

        name.startsWith("MIDI") -> "web.midi"
        name.startsWith("WakeLock") -> "web.wakelock"

        name in PAYMENT_TYPES -> "web.payment"
        name.startsWith("Payment") -> "web.payment"

        name in URL_TYPES -> "web.url"
        name in REPORTING_TYPES -> "web.reporting"
        name == XSLT_PROCESSOR -> "web.xslt"

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
    if (name == "FontFaceSet")
        return null

    val contentSourceList = sequenceOf("declare var", "var", "const")
        .map { source.substringAfter("\n$it $name: {\n", "") }
        .plus(source.substringAfter("\ninterface ${name}Constructor {\n", ""))
        .filter { it.isNotEmpty() }
        .toList()

    val contentSource = when (name) {
        "AudioWorkletProcessor" -> contentSourceList.first()
        else -> {
            require(contentSourceList.size <= 1)
            contentSourceList.singleOrNull()
                ?: return null
        }
    }

    return contentSource
        .substringBefore(";\n};")
        .substringBefore(";\n}\n")
        .trimIndent()
        .removePrefix("prototype: $name;\n")
        .removeSuffix("\nreadonly prototype: $name")
        .takeIf { it.isNotEmpty() }
}

private fun getConstructors(
    name: String,
    source: String,
    typeProvider: TypeProvider,
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
        .map { convertConstructor(it, typeProvider) }
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
        .trim()
        .ifEmpty { return "" }

    return "companion object {\n$members\n}"
}

private fun convertConstructor(
    parametersSource: String,
    typeProvider: TypeProvider,
): String {
    if (parametersSource == "")
        return ""

    var parameters = convertFunctionParameters(parametersSource, typeProvider)

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
            .let(::formatComment)

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

        "getContext(contextId: string, options?: any): RenderingContext | null",
            -> return """
        fun <T : RenderingContext, O : Any> getContext(
            contextId: RenderingContextId<T, O>, 
            options: O? = definedExternally,
        ): T?
        """.trimIndent()

        "getContext(contextId: OffscreenRenderingContextId, options?: any): OffscreenRenderingContext | null",
            -> return """
        fun <T : OffscreenRenderingContext, O : Any> getContext(
            contextId: RenderingContextId<T, O>, 
            options: O? = definedExternally,
        ): T?
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

        val currentTarget = source
            .substringAfter("(this: ", "")
            .substringBefore(", ", "")

        val target = EventDataRegistry.getTarget(
            currentTarget = currentTarget,
            eventType = handlerName.removePrefix("on"),
        )

        var eventType = source
            .substringAfter(": ")
            .substringAfter("ev: ")
            .substringBefore(")")

        eventType += when (eventType) {
            "MessageEvent" -> "<*>"
            else -> ""
        }

        return sequenceOf(
            "var $handlerName: EventHandler<$eventType, $currentTarget, $target>?",
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

        if (isFun) {
            val result = convertFunction(source, typeProvider)
                ?: return null

            return withSuspendAdapter(result)
                .joinToString("\n\n")
        }
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
            || typeProvider.readonlyMode
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
            -> "DOMHighResTimeStamp /* | String */"

        "number | CSSNumericValue | string",
            -> "Any /* $type */"

        "\"file\"",
        "\"directory\"",
            -> "FileSystemHandleKind.${type.removeSurrounding("\"")}"

        "GPUPipelineLayout | GPUAutoLayoutMode",
            -> "Any /* $type */"

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

        "QueuingStrategySize",
            -> "QueuingStrategySize<Void>"

        "CompositeOperationOrAuto | CompositeOperationOrAuto[]",
            -> "ReadonlyArray<CompositeOperationOrAuto> /* | CompositeOperationOrAuto */"

        "number | (number | null)[]",
            -> "ReadonlyArray<Double?> /* | Double */"

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

        "Element | Document" -> "ParentNode /* Element | Document */"

        // Audio
        "number[] | Float32Array",
            -> "ReadonlyArray<Double> /* | Float32Array */"

        "Record<string, number>",
            -> "ReadonlyRecord<String, Double>"

        "Record<string, string>",
            -> "ReadonlyRecord<String, String>"

        "Record<string, AuthenticationExtensionsPRFValues>",
            -> "Record<String, AuthenticationExtensionsPRFValues>"

        "BufferSource | string",
            -> "BufferSource /* | String */"

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
        "ReadonlyArray<string>" -> "ReadonlyArray<String>"
        "ReadonlyArray<number>" -> "ReadonlyArray<Double>"
        "MediaList | string" -> "Any /* MediaList | string */"
        "Element | ProcessingInstruction" -> "Any /* Element | ProcessingInstruction */"
        "string | CanvasGradient | CanvasPattern" -> "Any /* string | CanvasGradient | CanvasPattern */"
        "string | ArrayBuffer" -> "Any /* string | ArrayBuffer */"
        "HTMLCanvasElement | OffscreenCanvas" -> "EventTarget /* $type */"
        "(WindowProxy & typeof globalThis)" -> "WindowProxy"

        "HTMLCollectionOf<HTMLAnchorElement | HTMLAreaElement>",
            -> "HTMLCollectionOf<HTMLElement /* HTMLAnchorElement | HTMLAreaElement */>"

        "typeof FileReader.EMPTY | typeof FileReader.LOADING | typeof FileReader.DONE",
            -> "ReadyState"

        else -> when {
            type.startsWith("1 | 2 | 5 | 10")
                -> "Int /* $type */"

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

            (type.startsWith("(") && type.endsWith(" | null)[]"))
                -> {
                val arrayType = type
                    .removePrefix("(")
                    .removeSuffix(" | null)[]")

                "ReadonlyArray<$arrayType?>"
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

    val parameters = convertFunctionParameters(parametersSource, typeProvider)

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
        .replace(": Promise<WritableStream>", ": Promise<WritableStream<*>>")
        .replace("<string[]", "<ReadonlyArray<String>")
        .replace(": StaticRange[]", ": ReadonlyArray<StaticRange>")
        .replace(": (Gamepad | null)[]", ": ReadonlyArray<Gamepad?>")
        .replace(": RelativeTimeFormatPart[]", ": ReadonlyArray<dynamic /* RelativeTimeFormatPart */>")
        .replace(": PointerEvent[]", ": ReadonlyArray<PointerEvent>")
        .replace(Regex(""": (\w+?)\[]"""), ": ReadonlyArray<$1>")
        .replace(
            """: { type: "element" | "literal"; value: string; }[]""",
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
        .let {
            if (it.startsWith(": number")) {
                it.replace(": number", ": ${typeProvider.getReturnType(name)}")
            } else it
        }
        .replace(": string", ": String")
        .replace("<string>", "<String>")
        .replace(": boolean", ": Boolean")
        .replace("<boolean>", "<Boolean>")
        .replace(": any", ": Any")
        .replace("<void>", "<Void>")
        .replace(" | null", "?")
        .replace(" | undefined", "?")

    var safeName = when (name) {
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

    val jsName: String?
    when (name) {
        "createComputePipeline",
        "createRenderPipeline",
            -> {
            jsName = """@JsName("$name")"""
            safeName = "${name}Sync"
        }

        else -> jsName = null
    }

    return listOfNotNull(
        jsName,
        "$modifier fun $typeParameters$safeName($parameters)$result$mixinSugar",
    ).joinToString("\n")
}

private fun convertFunctionParameters(
    source: String,
    typeProvider: TypeProvider,
): String {
    val parameters = when (source) {
        "...data: any[]",
        "condition?: boolean, ...data: any[]",
        "label?: string, ...data: any[]",
            -> listOf(source.substringBefore(", ", ""))
            .filter { it.isNotEmpty() }
            .map { convertFunctionParameters(it, typeProvider) }
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

        "init: Record<string, string>",
            -> listOf(
            "init: ReadonlyRecord<String, String>",
        )

        // URL
        "obj: Blob | MediaSource",
            -> listOf(
            "obj: Blob /* | MediaSource */"
        )

        "...args: CSSNumberish[]",
        "...value: CSSNumberish[]",
        "...values: CSSNumberish[]",
        "...units: string[]",

        "...text: string[]",
        "...tokens: string[]",
        "...streams: MediaStream[]",
        "...initialRanges: AbstractRange[]",
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

        "inputs: Float32Array[][], outputs: Float32Array[][], parameters: Record<string, Float32Array>",
            -> listOf(
            "inputs: ReadonlyArray<ReadonlyArray<Float32Array>>",
            "outputs: ReadonlyArray<ReadonlyArray<Float32Array>>",
            "parameters: Record<String, Float32Array>",
        )

        "action: (item: AudioParam) => void",
        "action: (item: CSSNumericValue) => void",
        "action: (item: CSSTransformComponent) => void",
        "action: (item: CSSUnparsedSegment) => void",
        "action: (item: CSSStyleValue[]) => void",
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
            .let { if (it.count { char -> char == ':' } == 1) sequenceOf(it) else it.splitToSequence(", ") }
            .filter { it.isNotEmpty() }
            .map {
                var (pname, ptype) = it.split(": ")
                ptype = getParameterType(pname.removeSuffix("?"), ptype, typeProvider)

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
    typeProvider: TypeProvider,
): String {
    if (source.endsWith(" | null") || source.endsWith(" | undefined")) {
        val typeSource = source
            .removeSuffix(" | null")
            .removeSuffix(" | undefined")
        var type = getParameterType(name, typeSource, typeProvider)
        if ("? /* " !in type)
            type += "?"

        return type
    }

    return when {
        source == "\"$ED25519\""
            -> ED25519

        source == """ReadonlyArray<"sign" | "verify">"""
            -> """ReadonlyArray<KeyUsage /* "sign" | "verify" */>"""

        source == "number | DOMPointInit | (number | DOMPointInit)[]"
            -> "Any /* $source */"

        source == "File | string | FormData"
            -> "Any /* $source */"

        source == "DateTimeFormatPartTypes"
            -> "String /* $source */"

        // TEMP
        source == "AlgorithmIdentifier"
            -> "Any /* $source */"

        source == "Iterable<string>"
            -> "JsIterable<String>"

        // URL
        source == "string[][]"
            -> "ReadonlyArray<JsTuple2<String, String>>"

        source.startsWith("\"")
            -> "String /* $source */"

        source == "any"
            -> "Any?"

        source == "string"
            -> "String"

        source == "bigint"
            -> "BigInt"

        source == "number"
            -> typeProvider.getParameterType(name)

        source == "boolean"
            -> "Boolean"

        source == "Promise<any>"
            -> "Promise<*>"

        source == "ReadableStream"
            -> "ReadableStream<*>"

        source.endsWith("[]") -> {
            var atype = source.removeSuffix("[]")
            if (atype == "any")
                atype = "*"

            if (atype == "string")
                atype = "String"

            if (atype == "number")
                atype = "Double"

            if (atype == "[string, string]")
                atype = "JsTuple2<String, String>"

            "ReadonlyArray<$atype>"
        }

        else -> source
    }
}
