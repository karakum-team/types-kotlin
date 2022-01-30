package karakum.typescript

internal fun arrayHelpers(): Sequence<ConversionResult> =
    sequenceOf(
        ConversionResult(
            name = "ReadonlyArray",
            body = "typealias ReadonlyArray<T> = Array<out T>"
        )
    )
