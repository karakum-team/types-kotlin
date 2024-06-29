package karakum.browser

import karakum.common.loadContent
import java.net.URI

private val WEB_GPU_URI =
    URI("https://raw.githubusercontent.com/denoland/deno/main/cli/tsc/dts/lib.deno_webgpu.d.ts")

internal val WEB_GPU_CONTENT by lazy {
    loadContent(WEB_GPU_URI)
        .replace("/**\n * @category GPU\n * @experimental\n */\n", "")
        // TEMP?
        .replace("\n  // extended from spec", "")
        .replace(" =\n  | ", " = ")
        .replace("\n  | ", " | ")
        .replace("\n  extends ", " extends ")
        .replace("\n  implements ", " implements ")
        .replace(Regex("""(\(\n)( +)"""), "(")
        .replace(Regex("""(,\n)( +\):)"""), "):")
        .replace(Regex("""(,\n)( +)"""), ", ")
        .replace("): undefined;", "): void;")
        .replace(",)", ")")
        .replace(";\n\n  ", ";\n  ")
        .replace(" {}\n", " {\n}\n")
        .replace(Regex("""(class \w+) implements """), "$1 extends ")
        .replace(Regex("""(extends \w+) implements """), "$1, ")
        .replace("type GPUFlagsConstant = number;", "interface GPUUsage {\n}")
        .replace("usage: GPUFlagsConstant", "usage: GPUUsage")
        .replace(Regex("""(: GPU\w+)Flags"""), "$1")
        .replace(Regex("""type GPU\w+Flags = number;\n"""), "")
        .replace("\ndeclare type ", "type ")
        .replace(": Promise<undefined>", ": Promise<void>")
        .replace(""": "srgb" | "display-p3"""", ": PredefinedColorSpace")
}

private val WEB_GPU_NUMBER_ALIASES = mapOf(
    "GPUBufferDynamicOffset" to "unsigned long",
    "GPUStencilValue" to "unsigned long",
    "GPUSampleMask" to "unsigned long",
    "GPUDepthBias" to "long",

    "GPUSize64" to "unsigned long long",
    "GPUIntegerCoordinate" to "unsigned long",
    "GPUIndex32" to "unsigned long",
    "GPUSize32" to "unsigned long",
    "GPUSignedOffset32" to "long",

    "GPUSize64Out" to "unsigned long long",
    "GPUIntegerCoordinateOut" to "unsigned long",
    "GPUSize32Out" to "unsigned long",
)

internal val WEB_GPU_NUMBER_TYPES = WEB_GPU_NUMBER_ALIASES.keys

internal fun webGpuDeclarations(): Sequence<ConversionResult> {
    return webGpuDeclarations(WEB_GPU_CONTENT)
}

private fun webGpuDeclarations(
    content: String,
): Sequence<ConversionResult> {
    val types = convertTypes(
        content = content,
        getPkg = { "web.gpu" },
    )

    // TODO: strict flags

    val interfaces = Regex("""(interface|class) .+? \{[\s\S]*?\n}""")
        .findAll(content)
        .map { it.value }
        .mapNotNull { source ->
            when {
                " static " in source -> {
                    val name = source
                        .removePrefix("class ")
                        .substringBefore(" ")

                    val constants = source
                        .substringAfter("{\n")
                        .substringBefore("\n}")
                        .trimIndent()
                        .splitToSequence("\n")
                        .joinToString("\n") { line ->
                            val (cn, cv) = line.removePrefix("static ")
                                .removeSuffix(";")
                                .split(": ")

                            "val $cn: $name".let { it + (" ".repeat(40 - it.length)) } + "// $cv"
                        }

                    val parent = if (name.endsWith("Usage")) {
                        ":\nGPUUsage"
                    } else ""

                    val body = """
                    external interface $name $parent {
                        companion object {
                            $constants
                        } 
                    }
                    """.trimIndent()

                    ConversionResult(
                        name = name,
                        body = body,
                        pkg = "web.gpu",
                    )
                }

                source.startsWith("class GPUSupportedFeatures ")
                -> ConversionResult(
                    name = "GPUSupportedFeatures",
                    body = "sealed external class GPUSupportedFeatures : ReadonlySet<GPUFeatureName>",
                    pkg = "web.gpu",
                )

                source.startsWith("class GPUOutOfMemoryError ")
                        || source.startsWith("class GPUInternalError ")
                        || source.startsWith("class GPUPipelineError ")
                        || source.startsWith("class GPUValidationError ")
                -> {
                    val name = source
                        .removePrefix("class ")
                        .substringBefore(" ")

                    val parentType = source
                        .substringAfter(" extends ")
                        .substringBefore(" {")

                    val constructorParameters = source
                        .substringAfter("constructor(")
                        .substringBefore(")")
                        .replace(
                            "message?: string, options?: GPUPipelineErrorInit",
                            "message: String? = definedExternally,\noptions: GPUPipelineErrorInit? = definedExternally",
                        )
                        .replace(": string", ": String")

                    val body = """
                    external class $name(
                        $constructorParameters,
                    ): $parentType
                    """.trimIndent()

                    ConversionResult(
                        name = name,
                        body = body,
                        pkg = "web.gpu",
                    )
                }

                else -> {
                    convertInterface(
                        source = source,
                        getStaticSource = { if (source.startsWith("class")) "" else null },
                        predefinedPkg = "web.gpu",
                    )?.withComment(fullSource = content, source = source)
                }
            }
        }

    val aliases = ConversionResult(
        name = "Aliases",
        body = WEB_GPU_NUMBER_ALIASES.entries.joinToString("\n") { (name, idlType) ->
            "typealias $name = ${NUMBER_TYPE_MAP.getValue(idlType)}"
        },
        pkg = "web.gpu",
    )

    return types + interfaces + aliases +
            sequenceOf(
                ConversionResult(
                    name = "GPUUncapturedErrorEvent",
                    body = """
                    external class GPUUncapturedErrorEvent(
                        override val type: EventType<GPUUncapturedErrorEvent, EventTarget>,
                        init: GPUUncapturedErrorEventInit,                        
                    ): Event {
                        val error: GPUError
                    }
                    """.trimIndent(),
                    pkg = "web.gpu",
                ),
                ConversionResult(
                    name = "GPUUncapturedErrorEventInit",
                    body = """
                    @JsPlainObject
                    external interface GPUUncapturedErrorEventInit :
                        EventInit {
                        val error: GPUError
                    }
                    """.trimIndent(),
                    pkg = "web.gpu",
                ),
            )
}
