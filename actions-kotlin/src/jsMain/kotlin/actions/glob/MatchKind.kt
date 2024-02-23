// Automatically generated - do not modify!

@file:Suppress(
"NESTED_CLASS_IN_EXTERNAL_INTERFACE",
)

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

        @JsVirtual
        sealed external interface MatchKind {
            companion object {
            /** Not matched */
@JsIntValue(0)
val None: MatchKind
/** Matched if the path is a directory */
@JsIntValue(1)
val Directory: MatchKind
/** Matched if the path is a regular file */
@JsIntValue(2)
val File: MatchKind
/** Matched */
@JsIntValue(3)
val All: MatchKind
            }
        }
