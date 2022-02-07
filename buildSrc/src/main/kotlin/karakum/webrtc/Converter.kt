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
        .filter { it.name != "Window" }

private fun convertInterface(
    source: String,
): ConversionResult {
    val name = source.substringBefore(" ")
        .substringBefore("<")
        .substringBefore("(")
        .substringBefore(":")

    val declaration = name // source.substringBefore(" {\n")
    val body = convertMembers(
        source.substringAfter(" {\n")
            .let { if (it.startsWith("}")) "" else it }
            .substringBefore("\n}")
    )

    return ConversionResult(
        name = name,
        body = "external sealed interface $declaration {\n$body\n}",
    )
}
