package com.github.turansky.router

import com.github.turansky.common.GENERATOR_COMMENT
import java.io.File

fun generateKotlinDeclarations(
    historyFile: File,
    routerFile: File,
    routerDomFile: File,
    sourceDir: File,
) {
    generate(historyFile, sourceDir, Package.HISTORY)
    generate(routerFile, sourceDir, Package.ROUTER)
    generate(routerDomFile, sourceDir, Package.ROUTER_DOM)
}

private fun generate(
    definitionsFile: File,
    sourceDir: File,
    pkg: Package,
) {
    val targetDir = sourceDir.resolve(pkg.path)
        .also { it.mkdirs() }

    val source = definitionsFile.readText()
        .replace("\r\n", "\n")
        .substringAfter("""export type { Location, Path, To, NavigationType };""" + "\n")
        .substringAfter("""export { UNSAFE_NavigationContext, UNSAFE_LocationContext, UNSAFE_RouteContext } from "react-router";""" + "\n")
        .substringBefore("\n/** @internal */\nexport { NavigationContext as UNSAFE_NavigationContext")

    for ((name, body) in convertDefinitions(source)) {
        targetDir.resolve("$name.ts")
            .writeText(body)
    }
}

private fun fileContent(
    pkg: Package,
    annotations: String,
    body: String,
): String {
    return sequenceOf(
        "// $GENERATOR_COMMENT",
        annotations,
        pkg.pkg,
        body,
    ).filter { it.isNotEmpty() }
        .joinToString("\n\n")
}
