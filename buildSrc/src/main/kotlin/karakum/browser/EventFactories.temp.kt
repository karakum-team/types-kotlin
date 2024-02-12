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

private fun toFactory(it: ConversionResult): ConversionResult {
    val newBody = it.body
        .replace("EventInit", "EventInitMutable")
        .replace("ModifierInit", "ModifierInitMutable")
        .replace("\nval ", "\nvar ")

    return it.copy(
        name = it.name + ".temp",
        body = newBody,
    )
}
