package com.github.turansky.react

interface TypeConverter {
    fun convert(
        type: String,
        name: String,
    ): String
}

internal class SimpleTypeConverter(
    private val parentName: String,
) : TypeConverter {
    var unions: List<ConversionResult> = emptyList()
        private set

    override fun convert(
        type: String,
        name: String,
    ): String {
        if (type.endsWith(" | undefined"))
            return convert(type.removeSuffix(" | undefined"), name)

        if (type.startsWith("'") || type.startsWith("\"") || type.startsWith("boolean | '"))
            return unionType(type, name)

        return kotlinType(type, name)
    }

    private fun unionType(
        type: String,
        propertyName: String,
    ): String {
        if (parentName == "SVGAttributes")
            return "$STRING // $type"

        val name = unionName(propertyName)
        val values = type.splitToSequence(" | ")
            .filter { it != "boolean" }
            .map { it.removeSurrounding("'") }
            .map { it.removeSurrounding("\"") }
            .toList()

        unions = unions + convertUnion(name, values)

        return name
    }

    private fun unionName(
        propertyName: String,
    ): String =
        when {
            propertyName == "enterKeyHint"
            -> propertyName.capitalize()

            propertyName.startsWith("aria-")
            -> propertyName.ariaPropertyName().capitalize()

            parentName.endsWith("HTMLAttributes") ->
                parentName.removeSuffix("HTMLAttributes") + propertyName.capitalize()

            else -> propertyName.capitalize()
        }
}
