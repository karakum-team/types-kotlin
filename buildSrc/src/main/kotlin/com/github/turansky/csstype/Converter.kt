package com.github.turansky.csstype

import java.io.File

internal data class ConversionResult(
    val name: String,
    val body: String,
)

internal fun convertDefinitions(
    definitionsFile: File,
): Sequence<ConversionResult> {
    return definitionsFile.readText()
        .removePrefix("export {};\n")
        .splitToSequence("\nexport ", "\ndeclare ")
        .drop(1)
        .flatMap { content ->
            val name = content.substringAfter(" ")
                .substringBefore(" ")
                .substringBefore("<")

            when {
                name.startsWith("Obsolete") -> emptySequence()
                name.startsWith("Vendor") -> emptySequence()
                name.contains("Hyphen") -> emptySequence()
                content.startsWith("namespace ") -> convertNamespace(content)
                else -> sequenceOf(ConversionResult(name, content))
            }
        }
}

internal fun convertNamespace(
    source: String,
): Sequence<ConversionResult> {
    return source
        .substringAfter("{\n")
        .substringBefore("\n}")
        .trimIndent()
        .splitToSequence("\nexport ")
        .drop(1)
        .mapNotNull { content ->
            val name = content.substringAfter(" ")
                .substringBefore(" ")
                .substringBefore("<")

            when {
                name.startsWith("Moz") -> null
                name.startsWith("Ms") -> null
                name.startsWith("Webkit") -> null
                name.contains("Hyphen") -> null
                else -> ConversionResult(name, content)
            }
        }
}
