package com.github.turansky.react

interface TypeConverter {
    fun convert(
        type: String,
        name: String,
    ): String
}

class SimpleTypeConverter : TypeConverter {
    override fun convert(
        type: String,
        name: String,
    ): String {
        return kotlinType(type, name)
    }
}
