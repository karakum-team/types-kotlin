package karakum.table

internal const val DYNAMIC = "dynamic"
internal const val UNIT = "Unit"

internal const val STRING = "String"

internal const val INT = "Int"
internal const val DOUBLE = "Double"

private val STANDARD_TYPE_MAP = mapOf(
    "any" to "Any",
    "object" to "Any",
    "unknown" to "Any",

    "boolean" to "Boolean",
    "string" to STRING,

    "number" to "Number",

    "void" to UNIT,
    "null" to "Nothing?",

    "() => boolean" to "() -> Boolean",
    "() => number" to "() -> Number",

    "() => Column<TData>[]" to "() -> ReadonlyArray<Column<TData>>",
    "() => Cell<TData>[]" to "() -> ReadonlyArray<Cell<TData>>",
)

internal fun kotlinType(
    type: String,
    name: String,
): String {
    if (type.endsWith(" | undefined"))
        return kotlinType(type.removeSuffix(" | undefined"), name)

    STANDARD_TYPE_MAP[type]
        ?.also { return it }

    return type
        .replace(" => ", " -> ")
        .replace(") -> void", ") -> Unit")
}
