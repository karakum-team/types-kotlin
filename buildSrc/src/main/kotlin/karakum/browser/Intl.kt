package karakum.browser

import java.io.File

internal fun intlDeclarations(
    definitionsDir: File,
): Sequence<ConversionResult> {
    val content = definitionsDir.listFiles()!!
        .filter { it.name.endsWith(".intl.d.ts") }
        .map {
            it.readText()
                .replace("\r\n", "\n")
                .substringAfter("\ndeclare namespace Intl {\n")
                .substringBefore("\n}")
                .trimIndent()
                .replace("=\n    | ", " ")
                .replace("\n    | ", " | ")
        }
        .joinToString("\n")

    val types = convertTypes(
        content = content,
        getPkg = { "web.intl" },
    )

    return types
}
