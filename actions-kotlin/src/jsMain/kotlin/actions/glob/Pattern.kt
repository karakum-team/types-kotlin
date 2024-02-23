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

external class Pattern {
/**
 * Indicates whether matches should be excluded from the result set
 */
val negate: Boolean
/**
 * The directory to search. The literal path prior to the first glob segment.
 */
val searchPath: String
/**
 * The path/pattern segments. Note, only the first segment (the root directory)
 * may contain a directory separator character. Use the trailingSeparator field
 * to determine whether the pattern ended with a trailing slash.
 */
val segments: ReadonlyArray<String>
/**
 * Indicates the pattern should only match directories, not regular files.
 */
val trailingSeparator: Boolean
constructor(pattern: String)
constructor(pattern: String,
isImplicitPattern: Boolean,
segments: Void,
homedir: String)
constructor(negate: Boolean,
isImplicitPattern: Boolean,
segments: ReadonlyArray<String>,
homedir: String = definedExternally)
/**
 * Matches the pattern against the specified path
 */
fun match(itemPath: String): MatchKind
/**
 * Indicates whether the pattern may match descendants of the specified path
 */
fun partialMatch(itemPath: String): Boolean
companion object {
/**
 * Escapes glob patterns within a path
 */
fun globEscape(s: String): String
}
}
