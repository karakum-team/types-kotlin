package karakum.browser

internal fun BufferSource(): ConversionResult =
    ConversionResult(
        name = "BufferSource",
        body = "typealias BufferSource = Any /* ArrayBufferView | ArrayBuffer */",
    )
