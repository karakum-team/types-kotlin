package com.github.turansky.react

import java.io.File

internal data class ConversionResult(
    val name: String,
    val body: String,
)

internal fun convertDefinitions(
    definitionFile: File,
): Sequence<ConversionResult> {
    val content = definitionFile.readText()
        .replace("\r\n", "\n")
        .substringAfter("declare namespace React {\n")
        .substringBefore("\n}\n")
        .trimIndent()

    return content.splitToSequence("\ninterface ")
        .drop(1)
        .map { it.substringBefore("\n}\n") }
        .map {
            it.substringBefore(" ")
                .substringBefore("<")
        }
        .filter { it.endsWith("Event") }
        .map { name ->
            ConversionResult(name, "external interface $name")
        }
}

private fun props(propsName: String): String =
    "external interface $propsName: react.RProps"
