package karakum.webrtc

import karakum.common.unionBody

internal data class ConversionResult(
    val name: String,
    val body: String,
)

internal fun convertDefinitions(
    source: String,
): Sequence<ConversionResult> {
    val interfaces = source
        .splitToSequence("\ninterface ")
        .drop(1)
        .map { convertInterface(it) }
        .filter { it.name != "Window" }

    val types = source
        .splitToSequence("\ntype ")
        .drop(1)
        .map { it.substringBefore(";\n") }
        .filter { " | '" in it }
        .map { convertType(it) }

    return interfaces + types
}

private fun convertInterface(
    source: String,
): ConversionResult {
    val name = source.substringBefore(" ")
        .substringBefore("<")
        .substringBefore("(")
        .substringBefore(":")

    val declaration = name // source.substringBefore(" {\n")
    val body = convertMembers(
        name = name,
        source = source.substringAfter(" {\n")
            .let { if (it.startsWith("}")) "" else it }
            .substringBefore("\n}")
    )

    return ConversionResult(
        name = name,
        body = "external sealed interface $declaration {\n$body\n}",
    )
}

private fun convertType(
    source: String,
): ConversionResult {
    val (name, body) = source
        .split(" =")

    val values = body.removePrefix(" ")
        .splitToSequence(" | ", "\n    | ")
        .map { it.removeSurrounding("'") }
        .toList()

    return ConversionResult(
        name = name,
        body = unionBody(name, values),
    )
}
