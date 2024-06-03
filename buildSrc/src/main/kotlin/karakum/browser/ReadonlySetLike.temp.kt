package karakum.browser

internal fun readonlySetLike(): ConversionResult =
    ConversionResult(
        name = "ReadonlySetLike",
        body = "typealias ReadonlySetLike<T> = SetLike<T>",
        pkg = "js.collections",
    )
