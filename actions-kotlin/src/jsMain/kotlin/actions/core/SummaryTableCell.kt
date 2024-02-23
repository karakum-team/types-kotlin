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

sealed external interface SummaryTableCell {
    /**
     * Cell content
     */
    var data: String
    /**
     * Render cell as header
     * (optional) default: false
     */
    var header: Boolean?
    /**
     * Number of columns the cell extends
     * (optional) default: '1'
     */
    var colspan: String?
    /**
     * Number of rows the cell extends
     * (optional) default: '1'
     */
    var rowspan: String?
}
