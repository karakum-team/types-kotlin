package com.github.turansky.csstype

internal const val COLOR_PROPERTY = "ColorProperty"

internal class ColorConsumer : ParentConsumer {
    private val marker: String = "// $COLOR_PROPERTY\n"

    override fun apply(
        items: List<ConversionResult>,
    ): List<ConversionResult> {
        val parentTypes = items.asSequence()
            .filter { marker in it.body }
            .map { it.name }
            .plus(BORDER)
            .sorted()
            .joinToString(",\n")

        val result = items.map { it.copy(body = it.body.replace(marker, "")) }

        val oldType = result.first { it.name == COLOR_PROPERTY }
        val newBody = oldType.body
            .replaceFirst("// Globals | DataType.Color\n", "")
            .replaceFirst(COLOR_PROPERTY, "$COLOR_PROPERTY:\n$parentTypes")
        val newType = oldType.copy(body = newBody
        )
        return result - oldType + newType
    }
}
