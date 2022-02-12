package karakum.typescript

// language=Kotlin
private val READONLY_ARRAY_ADAPTER_BODY = """
sealed external interface ReadonlyArrayAdapter<out T>

inline fun <T> ReadonlyArrayAdapter<T>.asArray(): ReadonlyArray<T> =
    unsafeCast<ReadonlyArray<T>>()

inline fun <T> ReadonlyArrayAdapter<T>.asSequence(): Sequence<T> =
    asArray().asSequence()

inline operator fun <T> ReadonlyArrayAdapter<T>.iterator(): Iterator<T> =
    asArray().iterator()
""".trimIndent()

internal fun arrayHelpers(): Sequence<ConversionResult> =
    sequenceOf(
        ConversionResult(
            name = "ReadonlyArrayAdapter",
            body = READONLY_ARRAY_ADAPTER_BODY,
        ),
    )
