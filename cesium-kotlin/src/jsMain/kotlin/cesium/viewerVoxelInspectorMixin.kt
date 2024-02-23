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
 * A mixin which adds the [VoxelInspector] widget to the [Viewer] widget.
 * Rather than being called directly, this function is normally passed as
 * a parameter to [Viewer.extend], as shown in the example below.
 * ```
 * var viewer = new Viewer('cesiumContainer');
 * viewer.extend(viewerVoxelInspectorMixin);
 * ```
 * @param [viewer] The viewer instance.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/global.html#viewerVoxelInspectorMixin">Online Documentation</a>
 */
external  fun viewerVoxelInspectorMixin ( viewer: Viewer)