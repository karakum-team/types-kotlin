package karakum.typescript

import java.io.File

internal data class ConversionResult(
    val name: String,
    val body: String,
)

internal fun convertDefinitions(
    definitionsFile: File,
): Sequence<ConversionResult> {
    val types = definitionsFile.readText()
    println("TS source: " + types.split("\n").size)

    return emptySequence()
}
