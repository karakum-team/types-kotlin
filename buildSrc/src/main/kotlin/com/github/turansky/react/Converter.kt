package com.github.turansky.react

import java.io.File

internal data class ConversionResult(
    val name: String,
    val body: String,
    val pkg: Package = Package.DOM,
)

internal fun convertDefinitions(
    definitionFile: File,
): Sequence<ConversionResult> {
    val content = definitionFile.readText()
        .replace("HTMLTableHeaderCellElement", "HTMLTableCellElement")
        .replace("HTMLTableDataCellElement", "HTMLTableCellElement")
        .replace("HTMLWebViewElement", "HTMLElement")
        .replace("\r\n", "\n")

    val reactContent = content
        .substringAfter("declare namespace React {\n")
        .substringBefore("\n}\n")
        .trimIndent() + INPUT_TYPE + DANGEROUSLY_SET_INNER_HTML

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
