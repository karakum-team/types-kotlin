package com.github.turansky.react

interface TypeConverter {
    fun convert(
        type: String,
        name: String,
    ): String
}

class SimpleTypeConverter(
    private val parentName: String,
) : TypeConverter {
    override fun convert(
        type: String,
        name: String,
    ): String {
        if (type.endsWith(" | undefined"))
            return convert(type.removeSuffix(" | undefined"), name)

        if (type.startsWith("'") || type.startsWith("\"") || type.startsWith("boolean | '"))
            return "String // $type"

        return kotlinType(type, name)
    }
}
