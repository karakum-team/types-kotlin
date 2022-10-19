package karakum.webrtc

import karakum.common.GENERATOR_COMMENT
import karakum.common.Suppress
import karakum.common.fileSuppress
import java.io.File

private val DEFAULT_IMPORTS = """
import kotlinx.js.ReadonlyArray
import kotlinx.js.EpochTimeStamp
import media.stream.MediaStream
import media.stream.MediaStreamTrack
import web.events.Event 
import web.messaging.MessageEvent
import websockets.BinaryType
""".trimIndent()

private val ALIAS_MAP = mapOf(
    "media.stream.MediaStream" to "org.w3c.dom.mediacapture.MediaStream",
    "media.stream.MediaStreamTrack" to "org.w3c.dom.mediacapture.MediaStreamTrack",
)

fun generateKotlinDeclarations(
    definitionsFile: File,
    sourceDir: File,
) {
    convertDefinitions(definitionsFile.readText())
        .plus(ConversionResult("VoidFunction", "typealias VoidFunction = () -> Unit"))
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

            val pkg = "webrtc"

            val targetDir = sourceDir
                .resolve(pkg)
                .also { it.mkdirs() }

            targetDir.resolve("$name.kt")
                .also { check(!it.exists()) { "Duplicated file: ${it.name}" } }
                .writeText(fileContent(annotations = annotations, body = body, pkg = pkg))
        }

    for ((type, alias) in ALIAS_MAP) {
        val name = type.substringAfterLast(".")
        val pkg = type.substringBeforeLast(".")

        val body = "typealias $name = $alias"

        val targetDir = sourceDir
            .resolve(pkg.replace(".", "/"))
            .also { it.mkdirs() }

        targetDir.resolve("$name.kt")
            .also { check(!it.exists()) { "Duplicated file: ${it.name}" } }
            .writeText(fileContent(body = body, pkg = pkg))
    }
}

private fun fileContent(
    annotations: String = "",
    body: String,
    pkg: String,
): String {
    var result = sequenceOf(
        "// $GENERATOR_COMMENT",
        annotations,
        "package $pkg",
        DEFAULT_IMPORTS,
        body,
    ).filter { it.isNotEmpty() }
        .joinToString("\n\n")

    if (!result.endsWith("\n"))
        result += "\n"

    return result
}
