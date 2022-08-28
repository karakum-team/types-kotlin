// Automatically generated - do not modify!

package webgl

sealed external interface WEBGL_multi_draw {
    fun multiDrawArraysInstancedWEBGL(
        mode: GLenum,
        firstsList: Any, /* Int32Array | GLint[] */
        firstsOffset: GLuint,
        countsList: Any, /* Int32Array | GLsizei[] */
        countsOffset: GLuint,
        instanceCountsList: Any, /* Int32Array | GLsizei[] */
        instanceCountsOffset: GLuint,
        drawcount: GLsizei,
    )

    fun multiDrawArraysWEBGL(
        mode: GLenum,
        firstsList: Any, /* Int32Array | GLint[] */
        firstsOffset: GLuint,
        countsList: Any, /* Int32Array | GLsizei[] */
        countsOffset: GLuint,
        drawcount: GLsizei,
    )

    fun multiDrawElementsInstancedWEBGL(
        mode: GLenum,
        countsList: Any, /* Int32Array | GLint[] */
        countsOffset: GLuint,
        type: GLenum,
        offsetsList: Any, /* Int32Array | GLsizei[] */
        offsetsOffset: GLuint,
        instanceCountsList: Any, /* Int32Array | GLsizei[] */
        instanceCountsOffset: GLuint,
        drawcount: GLsizei,
    )

    fun multiDrawElementsWEBGL(
        mode: GLenum,
        countsList: Any, /* Int32Array | GLint[] */
        countsOffset: GLuint,
        type: GLenum,
        offsetsList: Any, /* Int32Array | GLsizei[] */
        offsetsOffset: GLuint,
        drawcount: GLsizei,
    )
}
