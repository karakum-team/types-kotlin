package karakum.popper

internal fun nameTypes(): Sequence<ConversionResult> =
    sequenceOf(
        ConversionResult(
            name = "Name",
            body = "external interface Name"
        ),
        ConversionResult(
            name = "ModifierName",
            body = """
                import kotlinx.js.jso
                
                external interface ModifierName<Options>: Name
                
                inline fun <Options> ModifierName<Options>.modifier(
                    block: Modifier<Options>.() -> Unit,
                ): Modifier<Options> = 
                    jso {
                        name = this@modifier
                        block()
                    }
            """.trimIndent()
        ),
        ConversionResult(
            name = "Popper",
            body = """
                @JsName("'popper'")
                external val Popper: Name
            """.trimIndent()
        ),
    )
