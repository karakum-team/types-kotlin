package karakum.node

internal fun rootVal(
    name: String,
    type: String,
): ConversionResult =
    ConversionResult(
        name = "$name.export",
        body = rootModuleAnnotaion(name) +
                "\n" +
                "external val $name: $type"
    )

internal fun rootModuleAnnotaion(
    name: String,
): String =
    """
    @JsModule("node:$name")
    @JsNonModule
    """.trimIndent()
