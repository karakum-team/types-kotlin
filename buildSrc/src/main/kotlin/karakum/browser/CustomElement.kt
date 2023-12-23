package karakum.browser

internal const val ATTRIBUTE_CHANGED_CALLBACK = "AttributeChangedCallback"
internal const val CUSTOM_ELEMENT_CALLBACKS = "CustomElementCallbacks"
internal const val CUSTOM_ELEMENT_COMPANION = "CustomElementCompanion"

private val CALLBACKS = mapOf(
    "connectedCallback" to "() -> Unit",
    "disconnectedCallback" to "() -> Unit",
    "adoptedCallback" to "() -> Unit",
    "attributeChangedCallback" to ATTRIBUTE_CHANGED_CALLBACK,
)

internal fun customElementTypes(): Sequence<ConversionResult> =
    sequenceOf(
        ConversionResult(
            name = ATTRIBUTE_CHANGED_CALLBACK,
            body = """
            typealias $ATTRIBUTE_CHANGED_CALLBACK = (
                name: String,
                oldValue: Any?,
                newValue: Any?,
            ) -> Unit
            """.trimIndent(),
            pkg = "web.components"
        ),
        CustomElementCallbacks(),
        CustomElementCompanion(),
    )

private fun CustomElementCallbacks(): ConversionResult {
    val members = CALLBACKS.entries.joinToString("\n") { (name, type) ->
        val comment = """
        /**
         * [MDN Reference](https://developer.mozilla.org/en-US/docs/Web/API/Web_components#${name.lowercase()})
         */
        """.trimIndent()

        val propertyType = if (type.startsWith("(")) "($type)?" else "$type?"
        val declaration = """
        var $name: $propertyType
            get() = definedExternally
            set(value) = definedExternally
        """.trimIndent()

        "$comment\n$declaration"
    }

    val body = "external interface $CUSTOM_ELEMENT_CALLBACKS {\n" +
            members +
            "\n}"

    return ConversionResult(
        name = CUSTOM_ELEMENT_CALLBACKS,
        body = body,
        pkg = "web.components"
    )
}

private fun CustomElementCompanion(): ConversionResult {
    val body = """
    external interface $CUSTOM_ELEMENT_COMPANION {
        /**
         * [MDN Reference](https://developer.mozilla.org/en-US/docs/Web/API/Web_components/Using_custom_elements#responding_to_attribute_changes)
         */
        val observedAttributes: ReadonlyArray<String> 
    }
    """.trimIndent()

    return ConversionResult(
        name = CUSTOM_ELEMENT_COMPANION,
        body = body,
        pkg = "web.components"
    )
}
