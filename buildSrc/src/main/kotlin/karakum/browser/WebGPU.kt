package karakum.browser

import java.net.URL

private val WEB_GPU_URL =
    URL("https://raw.githubusercontent.com/denoland/deno/main/cli/tsc/dts/lib.deno_webgpu.d.ts")

internal val WEB_GPU_CONTENT by lazy {
    WEB_GPU_URL.openStream()
        .use { stream -> String(stream.readAllBytes()) }
        .replace("/** @category WebGPU */\n", "")
        // TEMP?
        .replace("\n  // extended from spec", "")
        .replace(" =\n  | ", " = ")
        .replace("\n  | ", " | ")
        .replace("\n  extends ", " extends ")
        // .replace("\n  implements ", " implements ")
        .replace(Regex("""(\(\n)( +)"""), "(")
        .replace(Regex("""(,\n)( +\):)"""), "):")
        .replace(Regex("""(,\n)( +)"""), ", ")
        .replace("): undefined;", "): void;")
        .replace(",)", ")")
        .replace(";\n\n  ", ";\n  ")
        .replace(" {}\n", " {\n}\n")
        .replace(Regex("""(class \w+) implements """), "$1 extends ")
        .replace(Regex("""(extends \w+) implements """), "$1, ")
        .replace("\ndeclare type ", "type ")
        .replace(": Promise<undefined>", ": Promise<void>")
}

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
        // TEMP
        .filter { " static " !in it }
        .mapNotNull { source ->
            if (source.startsWith("class GPUSupportedFeatures ")) {
                ConversionResult(
                    name = "GPUSupportedFeatures",
                    body = "sealed external class GPUSupportedFeatures : ReadonlySet<GPUFeatureName>",
                    pkg = "web.gpu",
                )
            } else {
                convertInterface(
                    source = source,
                    getStaticSource = { if (source.startsWith("class")) "" else null },
                    predefinedPkg = "web.gpu",
                )?.withComment(fullSource = content, source = source)
            }
        }

    return types + interfaces
}
