package karakum.cesium

// language=Kotlin
private const val CODE: String = """
@file:Suppress(
    "UNUSED_TYPEALIAS_PARAMETER",
)    
    
package cesium

typealias Record<K, V> = Any    
"""

internal object Record : Declaration() {
    override val name: String = "Record"

    override val source: Definition
        get() = throw UnsupportedOperationException()

    override fun toCode(): String = CODE.removePrefix("\n")
}
