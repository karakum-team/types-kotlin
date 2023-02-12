package karakum.node

import karakum.common.ConversionResult

internal fun mergeBuffers(
    source: Sequence<ConversionResult>,
): Sequence<ConversionResult> {
    val results = source.toList()
    val buffer = results.first { it.name == "Buffer" }
    val bufferConstructor = results.first { it.name == "BufferConstructor" }

    val constructorBody = bufferConstructor.body
        .substringAfter("{\n")
        .substringBeforeLast("\n}")

    val companionBody = constructorBody
        .substringAfterLast("constructor(")
        .substringAfter(")\n")

    val constructors = constructorBody
        .substringBefore(companionBody)

    val finalBody = buffer.body
        .replaceFirst("{\n", "{\n$constructors")
        .substringBeforeLast("\n}")
        .plus("\n\ncompanion object {\n$companionBody\n}\n}")

    val finalBuffer = buffer.copy(body = finalBody)
    return results.asSequence()
        .minus(buffer)
        .minus(bufferConstructor)
        .plus(finalBuffer)
}
