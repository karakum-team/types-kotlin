package karakum.browser

import java.io.File

private const val DATE = "Date"

internal fun dateDeclarations(
    definitionsDir: File,
): Sequence<ConversionResult> {
    val rawMembers = dateRawContent(definitionsDir, DATE)
    val rawStaticMembers = dateRawContent(definitionsDir, "DateConstructor")

    val body = """
    external class $DATE() {
        $rawMembers
        $rawStaticMembers
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

private fun dateRawContent(
    definitionsDir: File,
    interfaceName: String,
): String =
    definitionsDir.listFiles()!!
        .filter { it.name.endsWith(".date.d.ts") || it.name.endsWith(".core.d.ts") || it.name == "lib.es5.d.ts" }
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
        .replace("new (value: number | string | Date): Date;\n", "")
        .replace("new (value: number | string): Date;\n", "new (value: number | string | Date): Date;\n")
        .replace("\n(): string;\n", "\n")
        .replace("readonly prototype: Date;\n", "")
        .splitUnion("number | string | Date")
        .splitUnion(
            "LocalesArgument",
            "UnicodeBCP47LocaleIdentifier | Locale | UnicodeBCP47LocaleIdentifier[] | Locale[]",
        )
