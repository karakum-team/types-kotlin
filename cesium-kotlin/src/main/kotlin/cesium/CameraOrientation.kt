// Automatically generated - do not modify!

package cesium

sealed external interface CameraOrientation

fun CameraOrientation(
    heading: Double,
    pitch: Double,
    roll: Double,
): CameraOrientation {
    val o: HeadingPitchRollValues = js("({})")
    o.heading = heading
    o.pitch = pitch
    o.roll = roll
    return o
}    
