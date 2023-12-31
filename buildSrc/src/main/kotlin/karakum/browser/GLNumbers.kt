package karakum.browser

private val TYPES = listOf(
    "GLbitfield" to "Number",
    "GLboolean" to "Boolean",
    "GLclampf" to "Number",
    "GLenum" to "Short",
    "GLfloat" to "Number",
    "GLint" to "Number",
    "GLint64" to "Number",
    "GLintptr" to "Number",
    "GLsizei" to "Number",
    "GLsizeiptr" to "Number",
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
