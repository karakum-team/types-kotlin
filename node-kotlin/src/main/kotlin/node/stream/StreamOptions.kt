// Automatically generated - do not modify!

package node.stream

sealed external interface StreamOptions<T : Stream> : Abortable {
    var emitClose: Boolean?
    var highWaterMark: Number?
    var objectMode: Boolean?
    val construct: ((
        this: T,
        callback: (error: Error?) -> Unit,
    ) -> Unit)?
    val destroy: ((
        this: T,
        error: Error?,
        callback: (error: Error?) -> Unit,
    ) -> Unit)?
    var autoDestroy: Boolean?
}
