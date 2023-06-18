// Automatically generated - do not modify!

package web.streams

sealed external interface UnderlyingSink<W> {
    var abort: UnderlyingSinkAbortCallback?
    var close: UnderlyingSinkCloseCallback?
    var start: UnderlyingSinkStartCallback?
    var type: undefined?
    var write: UnderlyingSinkWriteCallback<W>?
}
