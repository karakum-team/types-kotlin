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
        .replace("\ndeclare type ", "type ")
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

    return types

    /*
    val interfaces = Regex("""interface .+? \{[\s\S]+?\n}""")
        .findAll(content)
        .map { it.value }
        .mapNotNull { source ->
            convertInterface(
                source = source,
                getStaticSource = { getStaticSource(it, content) },
                predefinedPkg = "web.gpu",
            )?.withComment(fullSource = content, source = source)
        }

    return interfaces
    */
}
