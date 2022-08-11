package karakum.node

internal const val RECORD_TEMP = "Record.temp"

// language=kotlin
private val RECORD_BODY = """
// TODO: remove after next wrappers release    

import kotlinx.js.jso    

external interface Record<in K : Any, V : Any>

inline operator fun <K : Any, V : Any> Record<K, V>.get(
    key: K,
): V? =
    asDynamic()[key]

inline operator fun <K : Any, V : Any> Record<K, V>.set(
    key: K,
    value: V,
) {
    asDynamic()[key] = value
}

fun <K : Any, V : Any> Record(): Record<K, V> =
    jso()

fun <K : Any, V : Any> Record(
    block: Record<K, V>.() -> Unit,
): Record<K, V> =
    jso(block)
""".trimIndent()

internal fun Record(): ConversionResult =
    ConversionResult(
        RECORD_TEMP,
        RECORD_BODY,
    )
