// Automatically generated - do not modify!

package dom.html

import kotlin.js.Promise

sealed external class HTMLImageElement :
    HTMLElement {
    var align: String
    var alt: String
    var border: String
    val complete: Boolean
    var crossOrigin: String?
    val currentSrc: String
    var decoding: String /* "async" | "sync" | "auto" */
    var height: Number
    var hspace: Number
    var isMap: Boolean
    var loading: String /* "eager" | "lazy" */
    var longDesc: String
    var lowsrc: String
    var name: String
    val naturalHeight: Number
    val naturalWidth: Number
    var referrerPolicy: String
    var sizes: String
    var src: String
    var srcset: String
    var useMap: String
    var vspace: Number
    var width: Number
    val x: Number
    val y: Number
    fun decode(): Promise<void>
}
