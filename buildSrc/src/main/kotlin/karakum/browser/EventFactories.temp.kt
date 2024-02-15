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
    return toList().let { list ->
        val init = list.first { it.name == "EventModifierInit" }
        val optionsName = init.name.replace("Init", "Options")
        val options = init.copy(
            name = optionsName,
            body = init.body.replaceFirst("Init", "Options")
                .splitToSequence("\n")
                .filter { "val modifier" !in it }
                .joinToString("\n"),
        )

        val newInit = init.copy(
            body = init.body.replaceFirst("{", ",\n$optionsName {")
                .splitToSequence("\n")
                .joinToString("\n") { line ->
                    if (line.startsWith("val ") && !line.startsWith("val modifier")) {
                        "override $line"
                    } else line
                }
        )

        list - init + newInit + options + toFactory(init)
    }
}

private fun toFactory(source: ConversionResult): ConversionResult {
    val name = source.name
    val nameMutable = name + "Mutable"

    var newBody = source.body
        .replace("EventInit", "EventInitMutable")
        .replace("ModifierInit", "ModifierInitMutable")
        .replace("\nval ", "\noverride var ")
        .let { body ->
            if (name == "EventInit") {
                body.replaceFirst(nameMutable, "$nameMutable:\n$name")
            } else {
                body.replaceFirst(":", ":\n$name,")
            }
        }

    if (name.endsWith("EventInit")) {
        val factory = """
        inline fun $name(
            block: $nameMutable.() -> Unit,
        ): $name =
            jso(block)
        """.trimIndent()

        newBody = "$factory\n\n$newBody"
    }

    return source.copy(
        name = "$name.temp",
        body = newBody,
    )
}
