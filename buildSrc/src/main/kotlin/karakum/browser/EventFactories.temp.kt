package karakum.browser

private val FACTORY_REQUIRED = setOf(
    "EventInit",
    "ClipboardEventInit",
    "UIEventInit",
    "KeyboardEventInit",
    "MouseEventInit",
    "TouchEventInit",
)

internal fun List<ConversionResult>.withFactories(): List<ConversionResult> {
    val factories = filter { it.name in FACTORY_REQUIRED }
        .map(::toFactory)

    return this + factories
}

private fun toFactory(it: ConversionResult): ConversionResult {
    val newBody = it.body
        .replace("EventInit", "EventInitMutable")
        .replace("\nval ", "\nvar ")

    return it.copy(
        name = it.name + ".temp",
        body = newBody,
    )
}
