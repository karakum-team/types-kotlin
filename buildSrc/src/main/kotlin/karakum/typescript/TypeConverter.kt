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
        /*
        if (" | " in type && " | undefined" !in type && "(" !in type && "string" !in type)
            println(type)
        */

        return kotlinType(type, name)
    }
}
