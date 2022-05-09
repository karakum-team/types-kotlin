package karakum.cesium

// language=Kotlin
private const val CODE: String = """
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

fun CameraOrientation(
    var direction: Cartesian3
    var up: Cartesian3
): CameraOrientation {
    val o: DirectionUp = js("({})")
    o.direction = direction
    o.up = up
    return o
}
"""

internal object CameraOrientation : Declaration() {
    override val name: String = "CameraOrientation"

    override val source: Definition
        get() = throw UnsupportedOperationException()

    override fun toCode(): String = CODE.removePrefix("\n")
}
