// Automatically generated - do not modify!

package cesium.engine

import js.array.ReadonlyArray
import js.objects.JsPlainObject

/**
 * @property [entries] A list of elevation entries. They will automatically be sorted from lowest to highest. If there is only one entry and `extendsDownards` and `extendUpwards` are both `false`, they will both be set to `true`.
 * @property [extendDownwards] If `true`, the band's minimum elevation color will extend infinitely downwards.
 *   Default value - `false`
 * @property [extendUpwards] If `true`, the band's maximum elevation color will extend infinitely upwards.
 *   Default value - `false`
 */
@JsPlainObject
external interface createElevationBandMaterialBand {
    val entries: ReadonlyArray<createElevationBandMaterialEntry>
    val extendDownwards: Boolean?
    val extendUpwards: Boolean?
}
