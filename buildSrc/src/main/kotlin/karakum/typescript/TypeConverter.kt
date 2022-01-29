package karakum.typescript

internal interface TypeConverter {
    fun convert(
        type: String,
        name: String,
    ): String
}

internal class GlobalTypeConverter {
    private val map = mutableMapOf<String, String>()

    fun add(
        name: String,
        type: String,
    ): String? {
        if (" | " in type && " | undefined" !in type && "(" !in type && "string" !in type) {
            map[name] = type
            return null // TODO: return type name
        }

        return null
    }
}

internal class SimpleTypeConverter(
    private val parentName: String,
    private val parent: GlobalTypeConverter,
) : TypeConverter {
    override fun convert(
        type: String,
        name: String,
    ): String =
        parent.add("$parentName.$name", type)
            ?: kotlinType(type, name)
}
