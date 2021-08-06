package com.github.turansky.react

import java.io.File

private const val GENERATOR_COMMENT = "Automatically generated - do not modify!"

private enum class Suppress {
    NOTHING_TO_INLINE,
    UNUSED_TYPEALIAS_PARAMETER,
    NON_EXTERNAL_DECLARATION_IN_INAPPROPRIATE_FILE,

    ;
}

// language=Kotlin
private const val PACKAGE = "package react"

// language=Kotlin
private const val INTRINSIC_TYPE = """
external interface IntrinsicType<P: react.RProps>: ElementType<P>

inline fun <P: react.RProps> IntrinsicType(
    tag: String,
): IntrinsicType<P> =
    tag.unsafeCast<IntrinsicType<P>>()
"""

fun generateKotlinDeclarations(
    definitionsFile: File,
    sourceDir: File,
) {
    val targetDir = sourceDir.resolve("react")
        .also { it.mkdirs() }

    val intrinsicType = fileContent(
        annotations = """@file:Suppress("${Suppress.NOTHING_TO_INLINE.name}")""",
        body = INTRINSIC_TYPE,
    )
    targetDir.resolve("IntrinsicType.kt")
        .writeText(intrinsicType)

    for ((name, body) in convertDefinitions(definitionsFile)) {
        targetDir.resolve("${name}.kt")
            .writeText(fileContent(body = body))
    }
}

private fun fileContent(
    annotations: String = "",
    body: String,
) =
    sequenceOf(
        "// $GENERATOR_COMMENT",
        annotations,
        PACKAGE,
        body,
    ).filter { it.isNotEmpty() }
        .joinToString("\n\n")
