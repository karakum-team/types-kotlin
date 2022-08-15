// Automatically generated - do not modify!

package node.stream

import node.events.Abortable

sealed external interface StreamOptions<T : Stream> : Abortable {
    var emitClose: Boolean?
    var highWaterMark: Number?
    var objectMode: Boolean?

    /* this: T */
    val construct: ((callback: (error: Error?) -> Unit) -> Unit)?

    /* this: T */
    val destroy: ((
        error: Error?,
        callback: (error: Error?) -> Unit,
    ) -> Unit)?
    var autoDestroy: Boolean?
}
