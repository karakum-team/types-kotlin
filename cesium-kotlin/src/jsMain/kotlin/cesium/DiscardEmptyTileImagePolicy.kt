// Automatically generated - do not modify!

@file:JsModule("cesium")

package cesium

import js.promise.Promise
import web.canvas.ImageData
import web.dom.Document
import web.dom.Element
import web.html.HTMLCanvasElement
import web.html.HTMLElement
import web.html.HTMLIFrameElement
import web.html.HTMLImageElement
import web.html.HTMLVideoElement
import web.xml.XMLDocument
import js.buffer.ArrayBuffer
import js.objects.jso
import js.array.ReadonlyArray
import js.objects.ReadonlyRecord
import js.core.Void
import js.errors.JsError
import js.typedarrays.Float32Array
import js.typedarrays.Float64Array
import js.typedarrays.Uint16Array
import js.typedarrays.Uint8Array
import web.blob.Blob

/**
 * A policy for discarding tile images that contain no data (and so aren't actually images).
 * This policy discards [DiscardEmptyTileImagePolicy.EMPTY_IMAGE], which is
 * expected to be used in place of any empty tile images by the image loading code.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/DiscardEmptyTileImagePolicy.html">Online Documentation</a>
 */
external  class DiscardEmptyTileImagePolicy ()  : TileDiscardPolicy {
/**
 * Determines if the discard policy is ready to process images.
 * @return True if the discard policy is ready to process images; otherwise, false.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/DiscardEmptyTileImagePolicy.html#isReady">Online Documentation</a>
 */
override  fun isReady (): Boolean

/**
 * Given a tile image, decide whether to discard that image.
 * @param [image] An image to test.
 * @return True if the image should be discarded; otherwise, false.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/DiscardEmptyTileImagePolicy.html#shouldDiscardImage">Online Documentation</a>
 */
override  fun shouldDiscardImage ( image: HTMLImageElement): Boolean

companion object  {
/**
 * Default value for representing an empty image.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/DiscardEmptyTileImagePolicy.html#.EMPTY_IMAGE">Online Documentation</a>
 */
val EMPTY_IMAGE: HTMLImageElement
}
}
