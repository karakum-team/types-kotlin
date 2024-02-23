// Automatically generated - do not modify!

@file:JsModule("@actions/glob")

package actions.glob

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

external class Path {
var segments: ReadonlyArray<String>
/**
 * Constructs a Path
 * @param itemPath Path or array of segments
 */
constructor(itemPath: String)

constructor(itemPath: ReadonlyArray<String>)
/**
 * Converts the path to it's string representation
 */
override fun toString(): String
}
