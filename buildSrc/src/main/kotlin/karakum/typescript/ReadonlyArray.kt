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

// language=Kotlin
private val JS_ITERATOR_BODY = """
sealed external interface JsIteratorResult<out T> {
    val value: T
    val done: Boolean
}

/** ES6 Iterator type. */
sealed external interface JsIterator<out T> {
    fun next(): JsIteratorResult<T>
}

operator fun <T> JsIterator<T>.iterator(): Iterator<T> =
    JsIteratorAdapter(this)

private class JsIteratorAdapter<T>(
    private val source: JsIterator<T>,
) : Iterator<T> {
    private var lastResult = source.next()

    override fun next(): T {
        check(!lastResult.done)
        val value = lastResult.value

        lastResult = source.next()
        return value
    }

    override fun hasNext(): Boolean =
        !lastResult.done
}
""".trimIndent()

internal fun arrayHelpers(): Sequence<ConversionResult> =
    sequenceOf(
        ConversionResult(
            name = "ReadonlyArray",
            body = "typealias ReadonlyArray<T> = Array<out T>",
        ),
        ConversionResult(
            name = "ReadonlyArrayAdapter",
            body = READONLY_ARRAY_ADAPTER_BODY,
        ),
        ConversionResult(
            name = "JsIterator",
            body = JS_ITERATOR_BODY,
        ),
    )
