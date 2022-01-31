package karakum.webrtc

import java.io.File

internal data class ConversionResult(
    val name: String,
    val body: String,
)

internal fun convertDefinitions(
    definitionsFile: File,
): Sequence<ConversionResult> =
    definitionsFile.readText()
        .splitToSequence("\ninterface ")
        .drop(1)
        .map { convertInterface(it) }

private fun convertInterface(
    source: String,
): ConversionResult {
    val name = source.substringBefore(" ")
        .substringBefore("<")
        .substringBefore("(")
        .substringBefore(":")

    val declaration = name // source.substringBefore(" {\n")
    var body = source.substringAfter(" {\n")
        .substringBefore("\n}")

    body = "/*\n$body\n*/"

    return ConversionResult(
        name = name,
        body = "external sealed interface $declaration {\n$body\n}",
    )
}
