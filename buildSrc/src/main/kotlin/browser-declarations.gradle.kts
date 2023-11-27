plugins {
    id("declarations")
}

tasks.named("generateDeclarations") {
    doLast {
        val sourceDir = projectDir.resolve("src/jsMain/kotlin")

        delete(sourceDir)

        val definitionsDir = nodeModules.resolve("typescript/lib")
        val webDefinitionsDir = nodeModules.resolve("@types/web")
        val serviceworkerDefinitionsDir = nodeModules.resolve("@types/serviceworker")

        karakum.browser.generateKotlinDeclarations(
            definitionsDir = definitionsDir,
            webDefinitionsFile = webDefinitionsDir.resolve("index.d.ts"),
            webIterableDefinitionsFile = webDefinitionsDir.resolve("iterable.d.ts"),
            serviceworkerDefinitionsFile = serviceworkerDefinitionsDir.resolve("index.d.ts"),
            serviceworkerIterableDefinitionsFile = serviceworkerDefinitionsDir.resolve("iterable.d.ts"),
            sourceDir = sourceDir,
        )
    }
}

val KNOWN_MISSED_TYPES = setOf(
    "AddEventListenerOptions",
    "Animatable",
    "AnimationFrameProvider",
    "AudioProcessingEvent",
    "AudioProcessingEventInit",
    "BarProp",
    "ByteLengthQueuingStrategy",
    "CanvasCaptureMediaStreamTrack",
    "ClientRect",
    "ComputedKeyframe",
    "CountQueuingStrategy",
    "DOMException",
    "DocumentTimeline",
    "DocumentTimelineOptions",
    "EventListener",
    "EventListenerObject",
    "EventListenerOptions",
    "EventTarget",
    "External",
    "FrameRequestCallback",
    "GetAnimationsOptions",
    "HTMLAllCollection",
    "HTMLDirectoryElement",
    "HTMLFontElement",
    "HTMLFrameElement",
    "HTMLFrameSetElement",
    "HTMLMarqueeElement",
    "HTMLTableDataCellElement",
    "HTMLTableHeaderCellElement",
    "IdleDeadline",
    "IdleRequestCallback",
    "IdleRequestOptions",
    "ImportMeta",
    "Keyframe",
    "KeyframeAnimationOptions",
    "KeyframeEffect",
    "KeyframeEffectOptions",
    "MimeType",
    "MimeTypeArray",
    "MutationEvent",
    "OnBeforeUnloadEventHandlerNonNull",
    "OnErrorEventHandlerNonNull",
    "OverconstrainedError",
    "Plugin",
    "PluginArray",
    "PropertyIndexedKeyframes",
    "PushEvent",
    "PushEventInit",
    "PushMessageData",
    "RadioNodeList",
    "ScriptProcessorNode",
    "ServiceWorkerGlobalScope",
    "StaticRange",
    "StaticRangeInit",
    "StyleMedia",
    "VTTCue",
    "VTTRegion",
    "WindowLocalStorage",
    "WindowOrWorkerGlobalScope",
    "WindowSessionStorage",
    "XSLTProcessor",
)

val findMissedTypes by tasks.creating {
    doLast {
        val sourceDir = projectDir.resolve("src/jsMain/kotlin")

        val generatedInterfaces = fileTree(sourceDir)
            .mapNotNull { it.name.removeSuffix(".kt") }
            .flatMap { if (it.endsWith("Event")) sequenceOf(it, "${it}Init") else sequenceOf(it) }
            .toSet()

        val declaredInterfaces = sequenceOf(
            "@types/web",
            "@types/serviceworker",
        ).map { nodeModules.resolve("$it/index.d.ts") }
            .flatMap { it.readLines() }
            .zipWithNext { comment, line ->
                if (line.startsWith("interface ") && "@deprecated" !in comment) line else null
            }
            .filterNotNull()
            .map { it.removePrefix("interface ").substringBefore(" ").substringBefore("<") }
            .filter { !it.endsWith("EventMap") }
            .filter { !it.endsWith("NameMap") }
            .toSet()

        val missedTypes = (declaredInterfaces - generatedInterfaces).sorted() // - KNOWN_MISSED_TYPES
        println("MISSED TYPES: ${missedTypes.size}")
        println(missedTypes.joinToString("\n"))
    }
}
