// Automatically generated - do not modify!

@file:JsModule("@actions/artifact")

package actions.artifact

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

external fun getUploadHeaders(contentType: String,
isKeepAlive: Boolean = definedExternally,
isGzip: Boolean = definedExternally,
uncompressedLength: Number = definedExternally,
contentLength: Number = definedExternally,
contentRange: String = definedExternally,
digest: StreamDigest = definedExternally): OutgoingHttpHeaders
