package karakum.browser

private val FACTORY_REQUIRED = setOf(
    "EventInit",
    "UIEventInit",
)

internal fun List<ConversionResult>.withFactories(): List<ConversionResult> {
    val inits = filter { it.name in FACTORY_REQUIRED }
    val factories = inits.map {
        val newBody = it.body
            .replace("EventInit", "EventInitMutable")
            .replace("\nval ", "\nvar ")

        it.copy(
            name = it.name + ".factory",
            body = newBody,
        )
    }

    return this + factories
}
