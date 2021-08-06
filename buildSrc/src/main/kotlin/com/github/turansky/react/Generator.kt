package com.github.turansky.react

import java.io.File

private const val GENERATOR_COMMENT = "Automatically generated - do not modify!"

private enum class Suppress {
    UNUSED_TYPEALIAS_PARAMETER,
    NON_EXTERNAL_DECLARATION_IN_INAPPROPRIATE_FILE,

    ;
}

// language=Kotlin
private const val PACKAGE = "package react"

// language=Kotlin
private const val INTRINSIC_TYPE = "external interface IntrinsicType<P: react.RProps>: ElementType<P>"

fun generateKotlinDeclarations(
    definitionsFile: File,
    sourceDir: File,
) {
    val targetDir = sourceDir.resolve("react")
        .also { it.mkdirs() }

    targetDir.resolve("IntrinsicType.kt")
        .writeText(fileContent(body = INTRINSIC_TYPE))
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
