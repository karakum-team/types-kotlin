// Automatically generated - do not modify!

package actions.core

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

sealed external interface AnnotationProperties {
    /**
     * A title for the annotation.
     */
    var title: String?
    /**
     * The path of the file for which the annotation should be created.
     */
    var file: String?
    /**
     * The start line for the annotation.
     */
    var startLine: Number?
    /**
     * The end line for the annotation. Defaults to `startLine` when `startLine` is provided.
     */
    var endLine: Number?
    /**
     * The start column for the annotation. Cannot be sent when `startLine` and `endLine` are different values.
     */
    var startColumn: Number?
    /**
     * The end column for the annotation. Cannot be sent when `startLine` and `endLine` are different values.
     * Defaults to `startColumn` when `startColumn` is provided.
     */
    var endColumn: Number?
}
