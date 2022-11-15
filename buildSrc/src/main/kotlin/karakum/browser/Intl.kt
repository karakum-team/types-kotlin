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
                .replace("type ES2018NumberFormatPartType = ", "type NumberFormatPartType = ")
                .replace(";\ntype ES2020NumberFormatPartType = ", " | ")
                .replace("\ntype NumberFormatPartTypes = ES2018NumberFormatPartType | ES2020NumberFormatPartType;", "")

        }
        .joinToString("\n")

    val types = convertTypes(
        content = content,
        getPkg = { "web.intl" },
    )

    return types
}
