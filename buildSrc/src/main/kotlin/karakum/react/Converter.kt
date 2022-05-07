package karakum.react

import java.io.File

internal data class ConversionResult(
    val name: String,
    val body: String,
    val pkg: Package = Package.HTML,
)

internal fun convertDefinitions(
    definitionFile: File,
): Sequence<ConversionResult> {
    val content = definitionFile.readText()
        .applyFocusEventPatch()
        .replace("HTMLTableHeaderCellElement", "HTMLTableCellElement")
        .replace("HTMLTableDataCellElement", "HTMLTableCellElement")
        .replace("HTMLWebViewElement", "HTMLElement")
        .replace("HTMLAttributeAnchorTarget", "AnchorTarget")
        .replace("HTMLAttributeReferrerPolicy", "ReferrerPolicy")
        .replace("HTMLInputTypeAttribute", "InputType")
        .replace("    autoComplete?: string | undefined;", "    autoComplete?: AutoComplete | undefined;")
        .replace(" |  undefined", " | undefined")
        .replace("\r\n", "\n")

    val svgTypes = content.substringAfter("    interface IntrinsicElements {\n")
        .substringAfter("// SVG\n")
        .substringBefore("\n        }")
        .replace("\n\n", "\n")
        .replaceIndent("        ")

    val reactContent = content
        .substringAfter("declare namespace React {\n")
        .substringBefore("\n}\n")
        .replace(Regex("""( ReactSVG \{\n).+?(\n\s+})""", RegexOption.DOT_MATCHES_ALL)) {
            "${it.groupValues[1]}$svgTypes${it.groupValues[2]}"
        }
        .trimIndent()
        .plus(AUTO_COMPLETE)
        .plus(ADDITIONAL_TYPES)

    return convertInterfaces(reactContent)
        .plus(convertUnions(reactContent))
        .plus(convertNativeEvents(content))
        .plus(convertEventHandlers(reactContent))
}

private fun convertUnions(
    content: String,
): Sequence<ConversionResult> =
    content.splitToSequence("\ntype ")
        .drop(1)
        .map { it.substringBefore(";") }
        .mapNotNull {
            convertUnion(
                name = it.substringBefore(" ="),
                source = it.substringAfter(" =")
            )
        }

private fun convertInterfaces(
    content: String,
): Sequence<ConversionResult> =
    content.splitToSequence("\ninterface ")
        .drop(1)
        .map { it.substringBefore("\n}\n") }
        .flatMap {
            convertInterface(
                name = it.substringBefore(" ")
                    .substringBefore("<"),
                source = it,
            )
        }
