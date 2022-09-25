// Automatically generated - do not modify!

package node.stream.web

sealed external interface TransformerTransformCallback<I, O> {
    fun  /* native */ invoke(
        chunk: I,
        controller: TransformStreamDefaultController<O>,
    ): Any /* void | PromiseLike<void> */
}
