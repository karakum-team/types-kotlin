package karakum.webrtc

import karakum.common.GENERATOR_COMMENT
import java.io.File

fun generateKotlinDeclarations(
    definitionsFile: File,
    sourceDir: File,
) {
    // TODO
}

private fun fileContent(
    annotations: String = "",
    body: String,
): String {
    var result = sequenceOf(
        "// $GENERATOR_COMMENT",
        annotations,
        "package webrtc",
        body,
    ).filter { it.isNotEmpty() }
        .joinToString("\n\n")

    if (!result.endsWith("\n"))
        result += "\n"

    return result
}
