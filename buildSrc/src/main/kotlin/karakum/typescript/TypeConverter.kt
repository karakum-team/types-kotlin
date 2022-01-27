package karakum.typescript

interface TypeConverter {
    fun convert(
        type: String,
        name: String,
    ): String
}

internal class SimpleTypeConverter(
    private val parentName: String,
) : TypeConverter {
    override fun convert(
        type: String,
        name: String,
    ): String {
        return kotlinType(type, name)
    }
}
