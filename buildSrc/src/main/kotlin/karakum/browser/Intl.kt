package karakum.browser

import java.io.File

internal fun intlDeclarations(
    definitionsDir: File,
): Sequence<ConversionResult> {
    val content = intlContent(definitionsDir)

    val types = convertTypes(
        content = content,
        getPkg = { "web.intl" },
    )

    val interfaces = Regex("""interface .+? \{[\s\S]+?\n\}""")
        .findAll(content)
        .map { it.value }
        .groupBy { it.substringBefore(" {\n") }
        .map { (declaration, sourceParts) ->
            sourceParts.singleOrNull() ?: run {
                val body = sourceParts
                    .asSequence()
                    .map {
                        it.replace(": any\n", ": any;\n")
                            .substringAfter(" {\n")
                            .substringBefore("\n}")
                            .trimIndent()
                    }
                    .joinToString("\n")
                    .splitToSequence("\n")
                    .distinct()
                    .joinToString("\n")
                    .prependIndent("    ")

                "$declaration {\n$body\n}"
            }
        }
        .mapNotNull { convertInterface(it, { null }, "web.intl") }

    return types + interfaces
}

private fun intlContent(
    definitionsDir: File,
): String {
    var content = definitionsDir.listFiles()!!
        .filter { it.name.endsWith(".intl.d.ts") }
        .sortedBy { it.name }
        .map {
            it.readText()
                .replace("\r\n", "\n")
                .substringAfter("\ndeclare namespace Intl {\n")
                .substringBefore("\n}")
                .trimIndent()
                .replace("=\n    | ", "= ")
                .replace("\n    | ", " | ")
                .replace("type ES2018NumberFormatPartType = ", "type NumberFormatPartType = ")
                .replace(";\ntype ES2020NumberFormatPartType = ", " | ")
                .replace("\ntype NumberFormatPartTypes = ES2018NumberFormatPartType | ES2020NumberFormatPartType;", "")
                .replace("NumberFormatPartTypes", "NumberFormatPartType")
                .replace("\n\n", "\n")
                // WA for `DateTimeFormatPartTypesRegistry`
                .replace("\n }\n", "\n}\n")

        }
        .joinToString("\n")

    val typeMap = content
        .splitToSequence("\ntype ")
        .drop(1)
        .map { it.substringBefore(";") }
        .filter { " = \"" in it }
        .associate {
            val (name, value) = it.split(" = ")
            name to value
        }

    for ((name, value) in typeMap) {
        content = content
            .replace(": $value;", ": $name;")
            .replace(": $value | undefined;", ": $name | undefined;")
    }

    return content
}
