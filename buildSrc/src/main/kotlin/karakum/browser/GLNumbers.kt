package karakum.browser

private val TYPES = listOf(
    "GLbitfield" to "Number",
    "GLboolean" to "Boolean",
    "GLclampf" to "Number",
    "GLenum" to "Short",
    "GLfloat" to "Number",
    "GLint" to "Int",
    "GLint64" to "Number",
    "GLintptr" to "Int",
    "GLsizei" to "Int",
    "GLsizeiptr" to "Int",
    "GLuint" to "Number",
    "GLuint64" to "Number",
)

internal fun GLNumbers(): ConversionResult =
    ConversionResult(
        name = "Aliases",
        body = TYPES.joinToString("\n") { (type, alias) ->
            "typealias $type = $alias"
        },
    )
