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
    "null | number" to "Number?",

    "void" to UNIT,
    "null" to "Nothing?",

    "string[]" to "ReadonlyArray<String>",
    "[string, number][]" to "ReadonlyArray<JsPair<String, Number>>",

    "Record<string, boolean>" to "Record<String, Boolean>",
    "Record<string, any>" to "Record<String, Any>",

    "() => boolean" to "() -> Boolean",
    "() => number" to "() -> Number",

    "TData[]" to "ReadonlyArray<TData>",
    "Column<TData>[]" to "ReadonlyArray<Column<TData>>",
    "ColumnDef<TData>[]" to "ReadonlyArray<ColumnDef<TData>>",
    "Header<TData>[]" to "ReadonlyArray<Header<TData>>",
    "HeaderGroup<TData>[]" to "ReadonlyArray<HeaderGroup<TData>>",
    "Row<TData>[]" to "ReadonlyArray<Row<TData>>",
    "() => Column<TData>[]" to "() -> ReadonlyArray<Column<TData>>",
    "() => Cell<TData>[]" to "() -> ReadonlyArray<Cell<TData>>",
    "() => Header<TData>[]" to "() -> ReadonlyArray<Header<TData>>",
    "() => HeaderGroup<TData>[]" to "() -> ReadonlyArray<HeaderGroup<TData>>",
    "() => Row<TData>[]" to "() -> ReadonlyArray<Row<TData>>",

    "false | -1 | 1" to "Int /* false | -1 | 1 */",
    "false | string" to "String /* false | string */",
) + (sequenceOf(
    "Cell", "CoreCell", "CoreHeader",
).map {
    val contextParent = if (it == "Cell") "CoreCell" else it
    "ColumnDefTemplate<ReturnType<$it<TData>['getContext']>>" to
            "ColumnDefTemplate<() -> $contextParent.Context<TData>>"
})

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
        .replace("?: ColumnPinningPosition", ": ColumnPinningPosition?")
        .replace("?: InitialTableState", ": InitialTableState?")
        .replace("?: number", ": Number?")
        .replace("?: boolean", ": Boolean?")
        .replace("?: string", ": String?")
        .replace("?: any", ": Any?")
        .replace(": number", ": Number")
        .replace(": boolean", ": Boolean")
        .replace(": string", ": String")
        .replace(": unknown", ": Any")
        .replace(": any", ": Any")
        .replace(" -> number", " -> Number")
        .replace(" -> boolean", " -> Boolean")
        .replace(" -> unknown", " -> Any")
        .replace(" -> any", " -> Any")
        .replace("<number>", "<Number>")
        .replace("<any>", "<*>")
        .replace("number[]", "ReadonlyArray<Number>")
        .replace("undefined | [number, number]", "JsPair<Number, Number>?")
        .replace("boolean | (", "(")
        .replace("Map<any, number>", "Record<Any, Int> /* JS Map */")
}
