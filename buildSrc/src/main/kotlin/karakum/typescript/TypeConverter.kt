package karakum.typescript

internal interface TypeConverter {
    fun convert(
        type: String,
        name: String,
    ): String
}

internal class GlobalTypeConverter {
    private val typeMap = mutableMapOf<String, String>()
    private val map = mutableMapOf<String, String>()

    fun register(
        name: String,
        body: String,
    ) {
        typeMap[name] = body
    }

    fun add(
        name: String,
        type: String,
    ): String? {
        if (" | " in type && " | undefined" !in type && "(" !in type && "string" !in type && "ModuleKind." !in type && "<" !in type) {
            map[name] = type
            return null // TODO: return type name
        }

        return null
    }

    fun print() {
        println("----------------")
        println(
            typeMap.asSequence()
                .plus(map.asSequence())
                .map { (key, value) -> """ "$key" to "$value", """ }
                .joinToString("\n")
        )
        println("----------------")
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
