package karakum.webrtc

import karakum.common.GENERATOR_COMMENT
import java.io.File

fun generateKotlinDeclarations(
    definitionsDir: File,
    sourceDir: File,
) {
    val targetDir = sourceDir
        .resolve("webrtc")
        .also { it.mkdirs() }

    sequenceOf("MediaStream.d.ts", "RTCPeerConnection.d.ts")
        .map { definitionsDir.resolve(it) }
        .flatMap { convertDefinitions(it) }
        .forEach { (name, body) ->
            targetDir.resolve("$name.kt")
                .also { check(!it.exists()) { "Duplicated file: ${it.name}" } }
                .writeText(fileContent(body = body))
        }
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
