package com.github.turansky.react

import java.io.File

internal data class ConversionResult(
    val name: String,
    val body: String,
)

internal fun convertDefinitions(
    definitionFile: File,
): List<ConversionResult> {
    val content = definitionFile.readText()
        .replace("\r\n", "\n")
        .substringAfter("declare namespace React {\n")
        .substringBefore("\n}\n")
        .trimIndent()

    return emptyList()
}

private fun props(propsName: String): String =
    "external interface $propsName: react.RProps"
