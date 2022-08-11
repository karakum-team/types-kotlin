package karakum.node

internal fun rootVal(
    name: String,
    type: String,
): ConversionResult =
    ConversionResult(
        name = name,
        body = """
            @JsModule("node:$name")
            @JsNonModule
            external val $name: $type
        """.trimIndent()
    )
