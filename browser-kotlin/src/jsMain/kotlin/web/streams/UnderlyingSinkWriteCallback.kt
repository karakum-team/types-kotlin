// Automatically generated - do not modify!

package web.streams

import js.core.Void
import js.promise.Promise
import js.promise.PromiseLike
import web.streams.WritableStream

typealias UnderlyingSinkWriteCallback<W> = (
chunk: W,
controller: WritableStreamDefaultController) -> PromiseLike<Void>?
