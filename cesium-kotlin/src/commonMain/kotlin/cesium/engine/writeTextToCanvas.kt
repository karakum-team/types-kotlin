// Automatically generated - do not modify!

@file:JsModule("@cesium/engine")

package cesium.engine

import js.objects.JsPlainObject
import web.html.HTMLCanvasElement

/**
 * Writes the given text into a new canvas.  The canvas will be sized to fit the text.
 * If text is blank, returns undefined.
 * @param [text] The text to write.
 * @return A new canvas with the given text drawn into it.  The dimensions object
 *   from measureText will also be added to the returned canvas. If text is
 *   blank, returns undefined.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/global.html#writeTextToCanvas">Online Documentation</a>
 */
external fun writeTextToCanvas(
    text: String,
    options: WriteTextToCanvasOptions? = definedExternally,
): HTMLCanvasElement?

/**
 * @property [font] The CSS font to use.
 *   Default value - `'10px sans-serif'`
 * @property [fill] Whether to fill the text.
 *   Default value - `true`
 * @property [stroke] Whether to stroke the text.
 *   Default value - `false`
 * @property [fillColor] The fill color.
 *   Default value - [Color.WHITE]
 * @property [strokeColor] The stroke color.
 *   Default value - [Color.BLACK]
 * @property [strokeWidth] The stroke width.
 *   Default value - `1`
 * @property [backgroundColor] The background color of the canvas.
 *   Default value - [Color.TRANSPARENT]
 * @property [padding] The pixel size of the padding to add around the text.
 *   Default value - `0`
 */
@JsPlainObject
external interface WriteTextToCanvasOptions {
    val font: String?
    val fill: Boolean?
    val stroke: Boolean?
    val fillColor: Color?
    val strokeColor: Color?
    val strokeWidth: Double?
    val backgroundColor: Color?
    val padding: Double?
}
