package karakum.cesium

// language=Kotlin
private const val CODE: String = """
package cesium

external interface CameraOrientation {
    var heading: Double
    var pitch: Double
    var roll: Double
}

fun CameraOrientation(
    heading: Double,
    pitch: Double,
    roll: Double,
): CameraOrientation {
    val o: CameraOrientation = js("({})")
    o.heading = heading
    o.pitch = pitch
    o.roll = roll
    return o
}    
"""

internal object CameraOrientation : Declaration() {
    override val name: String = "CameraOrientation"

    override val source: Definition
        get() = throw UnsupportedOperationException()

    override fun toCode(): String = CODE.removePrefix("\n")
}
