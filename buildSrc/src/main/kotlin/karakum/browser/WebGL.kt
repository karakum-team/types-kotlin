package karakum.browser

import java.io.File

internal fun webglDeclarations(
    definitionsFile: File,
): Sequence<ConversionResult> =
    Regex("""interface EXT_.+? \{\n    "[\s\S]+?\n\}""")
        .findAll(definitionsFile.readText())
        .map { it.value }
        .map { source ->
            val name = source
                .substringAfter(" ")
                .substringBefore(" ")

            val body = "external " + source
                .replace("readonly ", "var ")
                .replace(";", "")

            ConversionResult(
                name = name,
                body = body,
            )
        }
        .plus(Aliases())
        .plus(Numbers())
