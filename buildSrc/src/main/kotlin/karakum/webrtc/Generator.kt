package karakum.webrtc

import karakum.common.GENERATOR_COMMENT
import karakum.common.Suppress
import karakum.common.fileSuppress
import java.io.File

private val DEFAULT_IMPORTS = "import kotlinext.js.ReadonlyArray"

fun generateKotlinDeclarations(
    definitionsDir: File,
    sourceDir: File,
) {
    val targetDir = sourceDir
        .resolve("webrtc")
        .also { it.mkdirs() }

    sequenceOf("MediaStream.d.ts", "RTCPeerConnection.d.ts")
        .map { definitionsDir.resolve(it) }
        .map { it.readText() }
        .plus(RTC_ICE_CANDIDATE)
        .flatMap { convertDefinitions(it) }
        .plus(unions())
        .forEach { (name, body) ->
            val suppresses = mutableListOf<Suppress>().apply {
                if ("JsName(\"\"\"(" in body)
                    add(Suppress.NAME_CONTAINS_ILLEGAL_CHARS)
            }.toTypedArray()

            val annotations = when {
                suppresses.isNotEmpty() ->
                    fileSuppress(*suppresses)

                else -> ""
            }

            targetDir.resolve("$name.kt")
                .also { check(!it.exists()) { "Duplicated file: ${it.name}" } }
                .writeText(fileContent(annotations = annotations, body = body))
        }
}

private fun fileContent(
    annotations: String = "",
    body: String,
): String {
    val defaultImports = if ("ReadonlyArray" in body) {
        DEFAULT_IMPORTS
    } else ""

    var result = sequenceOf(
        "// $GENERATOR_COMMENT",
        annotations,
        "package webrtc",
        defaultImports,
        body,
    ).filter { it.isNotEmpty() }
        .joinToString("\n\n")

    if (!result.endsWith("\n"))
        result += "\n"

    return result
}
