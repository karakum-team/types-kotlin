package karakum.browser

import java.io.File

private const val DATE = "Date"

internal fun dateDeclarations(
    definitionsDir: File,
): Sequence<ConversionResult> {
    val members = dateMembers(dateRawContent(definitionsDir, DATE))
    val staticMembers = dateMembers(dateRawContent(definitionsDir, "DateConstructor"))
    val constructors = staticMembers.filter { "constructor(" in it }

    val body = """
    external class $DATE() {
        ${constructors.joinToString("\n")}
        ${members.joinToString("\n")}

        companion object {
            ${(staticMembers - constructors).joinToString("\n")}
        }
    }
    """.trimIndent()

    return sequenceOf(
        ConversionResult(
            name = DATE,
            body = body,
            pkg = "js.date",
        )
    )
}

private fun dateMembers(
    content: String,
): List<String> =
    content.splitToSequence(";\n")
        .map { it.trim() }
        .filter { it.isNotEmpty() }
        .mapNotNull { dateMember(it) }
        .toList()

private fun dateMember(
    content: String,
): String? {
    if ("\n" in content) {
        val comment = content.substringBeforeLast("\n")
        val member = dateMember(content.substringAfterLast("\n"))
            ?: return null

        return comment + "\n" + member
    }

    if (content.startsWith("new (")) {
        return content
            .replace("new (", "constructor(")
            .replace("): $DATE", ")")
    }

    return "fun " + content
}

private fun dateRawContent(
    definitionsDir: File,
    interfaceName: String,
): String =
    definitionsDir.listFiles()!!
        .filter { it.name.endsWith(".date.d.ts") || it.name.endsWith(".core.d.ts") || it.name == "lib.es5.d.ts" }
        .filter { it.name != "lib.es2017.date.d.ts" }
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
                .substringAfter("\ninterface $interfaceName {\n", "")
                .substringBefore("\n}")
                .trimIndent()
                .replace("\n\n", "\n")
        }
        .filter { it.isNotEmpty() }
        .joinToString("\n")
        .replace("new (): Date;\n", "")
        .replace("\nnew (value: number | string | Date): Date;", "")
        .replace("new (value: number | string): Date;\n", "new (value: number | string | Date): Date;\n")
        .replace("\n(): string;\n", "\n")
        .replace("readonly prototype: Date;\n", "")
        .splitUnion("number | string | Date")
        .splitUnion(
            "LocalesArgument",
            "UnicodeBCP47LocaleIdentifier | Locale | UnicodeBCP47LocaleIdentifier[] | Locale[]",
        )
