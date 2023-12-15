package karakum.browser

import karakum.common.sealedUnionBody
import java.io.File

internal fun intlDeclarations(
    definitionsDir: File,
): Sequence<ConversionResult> {
    val rawContent = intlContent(definitionsDir)
    val (content, unions) = extractUnions(rawContent)

    val types = convertTypes(
        content = content,
        getPkg = { "js.intl" },
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
        .mapNotNull {
            convertInterface(
                source = it,
                getStaticSource = { getStaticSource(it, content) },
                predefinedPkg = "js.intl",
            )
        }

    return (unions + types + interfaces)
        .plus(
            ConversionResult(
                name = "LocalesArgument",
                body = "typealias LocalesArgument = Any",
                pkg = "js.intl",
            )
        )
        .asSequence()
}

private fun intlContent(
    definitionsDir: File,
): String =
    definitionsDir.listFiles()!!
        .filter { it.name.endsWith(".intl.d.ts") || it.name == "lib.es5.d.ts" }
        .sortedBy { file ->
            file.name
                .removePrefix("lib.es")
                .substringBefore(".")
                .toIntOrNull()
                ?: 3000
        }
        .map {
            it.readText()
                .replace("\r\n", "\n")
                .substringAfter("\ndeclare namespace Intl {\n")
                .substringBefore("\n}")
                .trimIndent()
                .splitUnion("string | string[]")
                .splitUnion("number | bigint")
                .splitUnion("Date | number | bigint")
                .splitUnion("Date | number")
                .splitUnion("BCP47LanguageTag | Locale")
                .splitUnion("BCP47LanguageTag | BCP47LanguageTag[]")
                .replace("new (", "new(")
                .replace("=\n    | ", "= ")
                .replace("\n    | ", " | ")
                .replace("type ES2018NumberFormatPartType = ", "type NumberFormatPartType = ")
                .replace(";\ntype ES2020NumberFormatPartType = ", " | ")
                .replace("\ntype NumberFormatPartTypes = ES2018NumberFormatPartType | ES2020NumberFormatPartType;", "")
                .replace("NumberFormatPartTypes", "NumberFormatPartType")
                .replace(""""basic" | "best fit" | "best fit"""", """"best fit" | "basic"""")
                .replace("\n\n", "\n")
                // WA for `DateTimeFormatPartTypesRegistry`
                .replace("\n }\n", "\n}\n")

        }
        .joinToString("\n")

private val PROPERTIES = setOf(
    "sensitivity",
    "collation",

    "timeZoneName",
    "formatMatcher",
    "dateStyle",
    "timeStyle",
    "hourCycle",
    "dayPeriod",

    "compactDisplay",
    "notation",
    "signDisplay",
    "unitDisplay",

    "granularity",
    "localeMatcher",
)

private fun extractUnions(
    content: String,
): Pair<String, List<ConversionResult>> {
    val unionRawMap = Regex("""(\w+)\??: (".+?);""")
        .findAll(content)
        .distinct()
        .associate { it.groupValues[1] to it.groupValues[2].removeSuffix(" | undefined") }

    val unionMap = PROPERTIES.associate { propertyName ->
        val values = unionRawMap.getValue(propertyName)
            .split(" | ")
            .map { it.removeSurrounding("\"") }

        val name = propertyName.replaceFirstChar(Char::uppercase)
        val union = ConversionResult(
            name = name,
            body = sealedUnionBody(name, values),
            pkg = "js.intl",
        )

        propertyName to union
    }

    val newContent = unionMap.entries.fold(content) { acc, (propertyName, result) ->
        val rawValue = unionRawMap.getValue(propertyName)
        acc.replace("$propertyName: $rawValue;", "$propertyName: ${result.name};")
            .replace("$propertyName?: $rawValue;", "$propertyName?: ${result.name};")
            .replace("$propertyName?: $rawValue | undefined;", "$propertyName?: ${result.name} | undefined;")
    }

    return newContent to unionMap.values.toList()
}
