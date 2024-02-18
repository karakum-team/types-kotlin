package karakum.browser

import java.io.File

internal fun decoratorsDeclarations(
    definitionsDir: File,
): Sequence<ConversionResult> {
    val content = decoratorsContent(definitionsDir)

    return content.splitToSequence("\n/**\n")
        .drop(1)
        .map { "/**\n$it".trim() }
        .filter { "\ninterface " in it }
        .map { source -> decoratorInterface(source) }
}

private fun decoratorInterface(
    source: String,
): ConversionResult {
    val (comment, bodySource) = source.split("\ninterface ")
    val name = bodySource
        .substringBefore(" ")
        .substringBefore("<")

    val body = comment +
            "\nsealed external interface " +
            bodySource

    return ConversionResult(
        name = name,
        body = body,
        pkg = "js.decorators",
    )
}

private fun decoratorsContent(
    definitionsDir: File,
): String =
    definitionsDir
        .resolve("lib.decorators.d.ts")
        .readText()
        .replace("\r\n", "\n")
        .substringAfter("""/// <reference no-default-lib="true"/>""")
