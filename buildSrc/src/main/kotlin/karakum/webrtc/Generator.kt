package karakum.webrtc

import karakum.common.GENERATOR_COMMENT
import karakum.common.Suppress
import karakum.common.fileSuppress
import java.io.File

private val DEFAULT_IMPORTS = """
import kotlinx.js.ReadonlyArray 
import web.events.Event 
import org.w3c.dom.MessageEvent 
""".trimIndent()

fun generateKotlinDeclarations(
    definitionsFile: File,
    sourceDir: File,
) {
    val targetDir = sourceDir
        .resolve("webrtc")
        .also { it.mkdirs() }

    convertDefinitions(definitionsFile.readText())
        .plus(ConversionResult("VoidFunction", "typealias VoidFunction = () -> Unit"))
        .plus(ConversionResult("EpochTimeStamp", "typealias EpochTimeStamp = Double"))
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
    var result = sequenceOf(
        "// $GENERATOR_COMMENT",
        annotations,
        "package webrtc",
        DEFAULT_IMPORTS,
        body,
    ).filter { it.isNotEmpty() }
        .joinToString("\n\n")

    if (!result.endsWith("\n"))
        result += "\n"

    return result
}
