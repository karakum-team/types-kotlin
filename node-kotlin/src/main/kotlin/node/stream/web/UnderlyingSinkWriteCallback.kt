// Automatically generated - do not modify!

package node.stream.web

sealed external interface UnderlyingSinkWriteCallback<W> {
    fun  /* native */ invoke(
        chunk: W,
        controller: WritableStreamDefaultController,
    ): Any /* void | PromiseLike<void> */
}
