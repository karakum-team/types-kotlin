package karakum.browser

import java.io.File

internal val SEALED_MARKER_DECLARATIONS = setOf(
    "ReadableStreamController<T>",
    "ReadableStreamReadResult<T>",
)

internal val NATIVE_ONLY_MARKER_DECLARATIONS = setOf(
    "MessageEventSource",

    "Transferable",

    "OffscreenRenderingContext",
    "RenderingContext",
) + SEALED_MARKER_DECLARATIONS

internal val MARKER_DECLARATIONS = setOf(
    "CanvasImageSource",
    "TexImageSource",

    "HTMLOrSVGImageElement",
    "HTMLOrSVGScriptElement",
).plus(NATIVE_ONLY_MARKER_DECLARATIONS)
    .plus(SEALED_MARKER_DECLARATIONS)

private val BASE_TYPES = listOf(
    "Blob",
)

// TODO: read from definitions
private val ALIASES = mapOf(
    "WindowProxy" to "Window",
)

internal object MarkerRegistry {
    private lateinit var map: Map<String, List<String>>

    fun fill(
        definitionFile: File,
    ) {
        val content = definitionFile.readText()
        map = MARKER_DECLARATIONS.asSequence()
            .flatMap { name -> findParentTypes(content = content, interfaceDeclaration = name) }
            .groupBy(keySelector = { it.first }, valueTransform = { it.second })
    }

    fun additionalParents(
        type: String,
    ): List<String>? =
        map[type]

    fun nonProcessedChildTypes(
        type: String,
    ): List<String> =
        BASE_TYPES.filter {
            val parentTypes = map[it]
            parentTypes != null && type in parentTypes
        }
}

private fun findParentTypes(
    content: String,
    interfaceDeclaration: String,
): Sequence<Pair<String, String>> =
    content.substringAfter("type $interfaceDeclaration = ", "")
        .substringBefore(";\n")
        .splitToSequence(" | ")
        .map { it.substringBefore("<") }
        .map { ALIASES[it] ?: it }
        .map { type ->
            val parentType = when (type) {
                "ReadableByteStreamController" -> interfaceDeclaration.replace("<T>", "<Void>")
                "ReadableStreamDefaultController" -> interfaceDeclaration.replace("<T>", "<R>")
                else -> interfaceDeclaration
            }

            type to parentType
        }
