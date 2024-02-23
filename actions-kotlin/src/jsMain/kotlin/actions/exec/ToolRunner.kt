// Automatically generated - do not modify!

@file:JsModule("@actions/exec")

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

external class ToolRunner : node.events.EventEmitter {
constructor(toolPath: String,
args: ReadonlyArray<String> = definedExternally,
options: ExecOptions = definedExternally)
/**
 * Exec a tool.
 * Output will be streamed to the live console.
 * Returns promise with return code
 *
 * @param     tool     path to tool to exec
 * @param     options  optional exec options.  See ExecOptions
 * @returns   number
 */
fun exec(): Promise<Number>
}
