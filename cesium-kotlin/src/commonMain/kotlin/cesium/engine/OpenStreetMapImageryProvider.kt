// Automatically generated - do not modify!

@file:JsModule("@cesium/engine")

package cesium.engine

import js.objects.JsPlainObject

/**
 * An imagery provider that provides tiled imagery hosted by OpenStreetMap
 * or another provider of Slippy tiles.  The default url connects to OpenStreetMap's volunteer-run
 * servers, so you must conform to their
 * [Tile Usage Policy](http://wiki.openstreetmap.org/wiki/Tile_usage_policy).
 * ```
 * const osm = new OpenStreetMapImageryProvider({
 *     url : 'https://tile.openstreetmap.org/'
 * });
 * ```
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/OpenStreetMapImageryProvider.html">Online Documentation</a>
 */
external class OpenStreetMapImageryProvider(
    options: ConstructorOptions,
) {
    /**
     * Initialization options for the OpenStreetMapImageryProvider constructor
     * @property [url] The OpenStreetMap server url.
     *   Default value - `'https://tile.openstreetmap.org'`
     * @property [fileExtension] The file extension for images on the server.
     *   Default value - `'png'`
     * @property [retinaTiles] When true, request tiles at the 2x resolution for retina displays.
     *   Default value - `false`
     * @property [rectangle] The rectangle of the layer.
     *   Default value - [Rectangle.MAX_VALUE]
     * @property [minimumLevel] The minimum level-of-detail supported by the imagery provider.
     *   Default value - `0`
     * @property [maximumLevel] The maximum level-of-detail supported by the imagery provider, or undefined if there is no limit.
     * @property [ellipsoid] The ellipsoid.  If not specified, the WGS84 ellipsoid is used.
     * @property [credit] A credit for the data source, which is displayed on the canvas.
     *   Default value - `'MapQuest, Open Street Map and contributors, CC-BY-SA'`
     * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/OpenStreetMapImageryProvider.html#.ConstructorOptions">Online Documentation</a>
     */
    @JsPlainObject
    interface ConstructorOptions {
        val url: String?
        val fileExtension: String?
        val retinaTiles: Boolean?
        val rectangle: Rectangle?
        val minimumLevel: Int?
        val maximumLevel: Int?
        val ellipsoid: Ellipsoid?
        val credit: Credit?
    }
}
