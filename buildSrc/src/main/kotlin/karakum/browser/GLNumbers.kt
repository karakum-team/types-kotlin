package karakum.browser

private val TYPES = listOf(
    "GLbitfield" to "Number",
    "GLboolean" to "Boolean",
    "GLclampf" to "Number",
    "GLfloat" to "Number",
    "GLint" to "Int",
    "GLint64" to "Number",
    "GLintptr" to "Int",
    "GLsizei" to "Int",
    "GLsizeiptr" to "Int",
    "GLuint" to "Number",
    "GLuint64" to "Number",
)

internal fun GLNumbers(): Sequence<ConversionResult> =
    sequenceOf(
        ConversionResult(
            name = "Aliases",
            body = TYPES.joinToString("\n") { (type, alias) ->
                "typealias $type = $alias"
            },
        ),
        ConversionResult(
            name = "GLenum",
            body = "sealed external interface GLenum",
        ),
    )
