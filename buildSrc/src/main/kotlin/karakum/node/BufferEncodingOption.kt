package karakum.node

internal fun BufferEncodingOption(): ConversionResult =
    ConversionResult(
        "BufferEncodingOption",
        """
            @JsName("'buffer'")
            external object BufferEncodingOption
        """.trimIndent()
    )
