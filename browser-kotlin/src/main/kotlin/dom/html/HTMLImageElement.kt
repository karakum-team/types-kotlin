// Automatically generated - do not modify!

package dom.html

import kotlinx.js.Void
import kotlin.js.Promise

sealed external class HTMLImageElement :
    HTMLElement {
    var alt: String
    val complete: Boolean
    var crossOrigin: String?
    val currentSrc: String
    var decoding: String /* "async" | "sync" | "auto" */
    var height: Number
    var isMap: Boolean
    var loading: String /* "eager" | "lazy" */
    val naturalHeight: Number
    val naturalWidth: Number
    var referrerPolicy: String
    var sizes: String
    var src: String
    var srcset: String
    var useMap: String
    var width: Number
    val x: Number
    val y: Number
    fun decode(): Promise<Void>
}
