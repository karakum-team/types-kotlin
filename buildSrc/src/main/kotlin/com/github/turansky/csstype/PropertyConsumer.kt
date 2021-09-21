package com.github.turansky.csstype

internal class PropertyConsumer : ParentConsumer {
    override fun apply(
        items: List<ConversionResult>,
    ): List<ConversionResult> {
        val typeMap = sequenceOf(
            COLOR_PROPERTY,
            GRID_LINE_PROPERTY,
            LINE_STYLE_PROPERTY,
            LINE_WIDTH_PROPERTY,
            BLEND_MODE_PROPERTY,

            "GlobalsType"
        ).associateBy {
            it.removeSuffix("Property")
                .removeSuffix("Type")
        }

        val oldTypes = items.filter { typeMap.containsKey(it.name) }
        val newTypes = oldTypes.map { source ->
            val type = source.name
            val parentType = typeMap.getValue(type)
            source.copy(body = source.body.replaceFirst(" $type", " $type: $parentType"))
        }

        return items - oldTypes + newTypes
    }
}
