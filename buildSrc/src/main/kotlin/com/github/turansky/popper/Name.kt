package com.github.turansky.popper

internal fun nameTypes(): Sequence<ConversionResult> =
    sequenceOf(
        ConversionResult(
            name = "Name",
            body = "external interface Name"
        ),
        ConversionResult(
            name = "ModifierName",
            body = "external interface ModifierName<Options>: Name"
        ),
        ConversionResult(
            name = "Popper",
            body = """
                @JsName("'popper'")
                external val Popper: Name
            """.trimIndent()
        ),
    )
