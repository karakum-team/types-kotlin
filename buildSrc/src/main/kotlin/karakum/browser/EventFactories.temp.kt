package karakum.browser

private val FACTORY_REQUIRED = setOf(
    "EventInit",
    "ClipboardEventInit",
    "UIEventInit",
    "KeyboardEventInit",
    "MouseEventInit",
    "TouchEventInit",
)

internal fun List<ConversionResult>.withEventInitFactories(): List<ConversionResult> {
    val factories = filter { it.name in FACTORY_REQUIRED }
        .map(::toFactory)

    return this + factories
}

internal fun Sequence<ConversionResult>.withMutableEventModifiersInit(): List<ConversionResult> {
    return toList().let { list -> list + toFactory(list.first { it.name == "EventModifierInit" }) }
}

private fun toFactory(source: ConversionResult): ConversionResult {
    val name = source.name
    val newBody = source.body
        .replace("EventInit", "EventInitMutable")
        .replace("ModifierInit", "ModifierInitMutable")
        .replace("\nval ", "\noverride var ")
        .let { body ->
            if (name == "EventInit") {
                val nameMutable = name + "Mutable"
                body.replaceFirst(nameMutable, "$nameMutable:\n$name")
            } else {
                body.replaceFirst(":", ":\n$name,")
            }
        }

    return source.copy(
        name = name + ".temp",
        body = newBody,
    )
}
