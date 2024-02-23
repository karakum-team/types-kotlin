// Automatically generated - do not modify!

package actions.exec

import js.promise.Promise
import js.promise.await
import js.collections.ReadonlyMap
import js.core.BigInt
import js.core.JsLong
import js.objects.Record
import js.array.ReadonlyArray
import js.core.Void
import js.errors.JsError
import node.buffer.Buffer
import node.http.IncomingHttpHeaders
import node.http.OutgoingHttpHeaders
import web.url.URL

import actions.http.client.HttpClient
import actions.http.client.HttpClientResponse

import seskar.js.JsIntValue
import seskar.js.JsVirtual
import seskar.js.JsValue

sealed external interface ExecOptions {
    /** optional working directory.  defaults to current */
    var cwd: String?
    /** optional envvar dictionary.  defaults to current process's env */
    var env: Record<String, String>?
    /** optional.  defaults to false */
    var silent: Boolean?
    /** optional out stream to use. Defaults to process.stdout */
    var outStream: node.stream.Writable?
    /** optional err stream to use. Defaults to process.stderr */
    var errStream: node.stream.Writable?
    /** optional. whether to skip quoting/escaping arguments if needed.  defaults to false. */
    var windowsVerbatimArguments: Boolean?
    /** optional.  whether to fail if output to stderr.  defaults to false */
    var failOnStdErr: Boolean?
    /** optional.  defaults to failing on non zero.  ignore will not fail leaving it up to the caller */
    var ignoreReturnCode: Boolean?
    /** optional. How long in ms to wait for STDIO streams to close after the exit event of the process before terminating. defaults to 10000 */
    var delay: Number?
    /** optional. input to write to the process on STDIN. */
    var input: Buffer?
    /** optional. Listeners for output. Callback functions that will be called on these events */
    var listeners: ExecListeners?
}
