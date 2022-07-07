package karakum.virtual

import java.io.File

internal data class ConversionResult(
    val name: String,
    val body: String,
)

internal fun convertDefinitions(
    definitionFile: File,
): Sequence<ConversionResult> {
    println("SIZE: " + definitionFile.readLines().size)

    return emptySequence()
}
