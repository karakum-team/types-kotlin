package karakum.browser

private val TYPES = listOf(
    "GLbitfield",
    "GLboolean",
    "GLclampf",
    "GLenum",
    "GLfloat",
    "GLint",
    "GLint64",
    "GLintptr",
    "GLsizei",
    "GLsizeiptr",
    "GLuint",
    "GLuint64",
)

internal fun GLNumbers(): ConversionResult =
    ConversionResult(
        name = "Numbers",
        body = TYPES.joinToString("\n") { type ->
            "typealias $type = Number"
        },
    )
