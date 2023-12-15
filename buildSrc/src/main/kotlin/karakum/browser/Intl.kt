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

    return unions.asSequence()
        .plus(types)
        .distinct()
        .plus(interfaces)
        .plus(
            ConversionResult(
                name = "LocalesArgument",
                body = "typealias LocalesArgument = Any",
                pkg = "js.intl",
            )
        )
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

                // TODO: strict align
                .replace("type RelativeTimeFormatLocaleMatcher = ", "type LocaleMatcher = ")
                .replace(": RelativeTimeFormatLocaleMatcher;", ": LocaleMatcher;")
                .replace("type LocaleCollationCaseFirst = ", "type CaseFirst = ")
                .replace(": LocaleCollationCaseFirst;", ": CaseFirst;")
                .replace("type LocaleHourCycleKey = ", "type HourCycle = ")
                .replace(": LocaleHourCycleKey;", ": HourCycle;")

                .replace("type ES2018NumberFormatPartType = ", "type NumberFormatPartType = ")
                .replace(";\ntype ES2020NumberFormatPartType = ", " | ")
                .replace("\ntype NumberFormatPartTypes = ES2018NumberFormatPartType | ES2020NumberFormatPartType;", "")
                .replace("NumberFormatPartTypes", "NumberFormatPartType")
                .replace(""""basic" | "best fit" | "best fit"""", """"best fit" | "basic"""")
                .replace(""": "best fit" | "lookup" | undefined;""", """: "lookup" | "best fit" | undefined;""")
                .replace(""" = "h12" | "h23" | "h11" | "h24";""", """ = "h11" | "h12" | "h23" | "h24";""")
                .replace("\n\n", "\n")
                // WA for `DateTimeFormatPartTypesRegistry`
                .replace("\n }\n", "\n}\n")

        }
        .joinToString("\n")

private val FORMAT_PROPERTIES = setOf(
    "weekday",
    "era",
    "year",
    "month",
    "day",
    "hour",
    "minute",
    "second",
    "timeZoneName",
)

private val PROPERTIES = setOf(
    "source",

    "caseFirst",
    "collation",
    "sensitivity",

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

    val unionMap = (FORMAT_PROPERTIES + PROPERTIES).associate { propertyName ->
        val values = unionRawMap.getValue(propertyName)
            .split(" | ")
            .map { it.removeSurrounding("\"") }

        var name = propertyName.replaceFirstChar(Char::uppercase)
        name = when (propertyName) {
            "source" -> "PartSource"
            in FORMAT_PROPERTIES -> "${name}Format"
            else -> name
        }

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
