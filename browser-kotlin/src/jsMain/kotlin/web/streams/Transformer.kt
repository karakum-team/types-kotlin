// Automatically generated - do not modify!

package web.streams

sealed external interface Transformer<
        I,
        O,
        > {
    var flush: TransformerFlushCallback<O>?
    var readableType: undefined?
    var start: TransformerStartCallback<O>?
    var transform: TransformerTransformCallback<I, O>?
    var writableType: undefined?
}
