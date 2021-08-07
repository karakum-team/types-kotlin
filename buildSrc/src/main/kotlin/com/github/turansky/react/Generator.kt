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
private const val PACKAGE = "package react.dom"

// language=Kotlin
private const val REACT_STUBS = """
// $GENERATOR_COMMENT    
    
@file:Suppress("NOTHING_TO_INLINE")    

package react

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
    val targetDir = sourceDir.resolve("react/dom")
        .also { it.mkdirs() }

    sourceDir.resolve("react/Stubs.kt")
        .writeText(REACT_STUBS)

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
