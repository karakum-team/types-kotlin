package com.github.turansky.popper

internal data class ConversionResult(
    val name: String,
    val body: String,
)

internal fun convertDefinitions(
    source: String,
): Sequence<ConversionResult> {
    return source
        .splitToSequence("\nexport declare type ")
        .drop(1)
        .mapNotNull { content ->
            if (" = {\n" !in content)
                return@mapNotNull null

            val name = content.substringBefore(" = {\n")
            val body = content.substringAfter(" = {\n")
                .substringBefore(";\n};\n")
                .removeSuffix(";\n};")
            // .trimIndent()

            convertInterface(name, body)
        }
}
