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
    val inits = filter { it.name in FACTORY_REQUIRED }
    val factories = inits.map {
        val newBody = it.body
            .replace("EventInit", "EventInitMutable")
            .replace("\nval ", "\nvar ")

        it.copy(
            name = it.name + ".temp",
            body = newBody,
        )
    }

    return this + factories
}
