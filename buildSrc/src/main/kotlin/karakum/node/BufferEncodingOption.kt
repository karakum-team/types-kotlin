package karakum.node

import karakum.common.ConversionResult

internal fun BufferEncodingOption(): ConversionResult =
    ConversionResult(
        "BufferEncodingOption",
        """
            @JsName("'buffer'")
            external object BufferEncodingOption
        """.trimIndent()
    )
