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

    "number" to "Int",
    "null | number" to "Int?",

    "void" to UNIT,
    "null" to "Nothing?",

    "string[]" to "ReadonlyArray<String>",
    "[string, number][]" to "ReadonlyArray<JsTuple2<String, Int>>",

    "Record<string, boolean>" to "Record<String, Boolean>",
    "Record<string, any>" to "Record<String, Any>",
    "Record<string, Row<TData>>" to "Record<String, Row<TData>>",
    "Record<string, FilterMeta>" to "Record<String, FilterMeta>",

    "Partial<TableState>" to "TableState /* Partial */",
    "Partial<PaginationState>" to "PaginationState /* Partial */",
    "Partial<ColumnDef<TData, unknown>>" to "ColumnDef<TData, *> /* Partial */",

    "() => boolean" to "() -> Boolean",
    "() => number" to "() -> Int",
    "() => number[]" to "() -> ReadonlyArray<Int>",

    "<TTValue = TValue>() => TTValue" to "() -> TValue",
    "<TTValue = TValue>() => TTValue | null" to "() -> TValue?",
    "<TValue>(columnId: string) => TValue" to "(columnId: String) -> Any? /* TValue */",

    "TData[]" to "ReadonlyArray<TData>",
    "Column<TData, unknown>[]" to "ReadonlyArray<Column<TData, *>>",
    "Column<TData, TValue>[]" to "ReadonlyArray<Column<TData, TValue>>",
    "ColumnDef<TData>" to "ColumnDef<TData, *>",
    "ColumnDef<TData>[]" to "ReadonlyArray<ColumnDef<TData, *>>",
    "ColumnDef<TData, any>[]" to "ReadonlyArray<ColumnDef<TData, *>>",
    "ColumnDef<TData, unknown>[]" to "ReadonlyArray<ColumnDef<TData, *>>",
    "Header<TData, unknown>[]" to "ReadonlyArray<Header<TData, *>>",
    "Header<TData, TValue>[]" to "ReadonlyArray<Header<TData, TValue>>",
    "HeaderGroup<TData>[]" to "ReadonlyArray<HeaderGroup<TData>>",
    "Row<TData>[]" to "ReadonlyArray<Row<TData>>",
    "() => Column<TData, unknown>[]" to "() -> ReadonlyArray<Column<TData, *>>",
    "() => Column<TData, TValue>[]" to "() -> ReadonlyArray<Column<TData, TValue>>",
    "() => Cell<TData, unknown>[]" to "() -> ReadonlyArray<Cell<TData, *>>",
    "() => Header<TData, unknown>[]" to "() -> ReadonlyArray<Header<TData, *>>",
    "() => HeaderGroup<TData>[]" to "() -> ReadonlyArray<HeaderGroup<TData>>",
    "() => Row<TData>[]" to "() -> ReadonlyArray<Row<TData>>",

    "false | -1 | 1" to "Int /* false | -1 | 1 */",
    "false | string" to "String /* false | string */",
    "false | 'reorder' | 'remove'" to "GroupingColumnMode",

    "string | keyof TData" to "String /* string | keyof TData */",

    "() => undefined | ((event: unknown) => void)" to "() -> ((event: Any) -> Unit)?",
    "RequiredKeys<TableOptionsResolved<TData>, 'state'>" to "Any /* RequiredKeys<TableOptionsResolved<TData>, 'state'> */",

    "CellContext<TData, TValue>['getValue']" to "Getter<TValue> /* CellContext<TData, TValue>['getValue'] */",
    "CellContext<TData, TValue>['renderValue']" to "Getter<TValue?> /* CellContext<TData, TValue>['renderValue'] */",

    "ColumnDefTemplate<ReturnType<Cell<TData, TValue>['getContext']>>" to "ColumnDefTemplate<CellContext<TData, TValue>>"
)

internal fun kotlinType(
    type: String,
    name: String,
): String {
    if (type.endsWith(" | undefined"))
        return kotlinType(type.removeSuffix(" | undefined"), name)

    if (name.endsWith("RowModel") && type == "(table: Table<any>) => () => RowModel<any>")
        return "(table: Table<TData>) -> () -> RowModel<TData>"

    STANDARD_TYPE_MAP[type]
        ?.also { return it }

    return type
        .replace(" => ", " -> ")
        .replace(") -> void", ") -> Unit")
        .replace("?: ColumnPinningPosition", ": ColumnPinningPosition?")
        .replace("?: InitialTableState", ": InitialTableState?")
        .replace("?: number", ": Int?")
        .replace("?: boolean", ": Boolean?")
        .replace("?: string", ": String?")
        .replace("?: any", ": Any?")
        .replace(": number", ": Int")
        .replace(": boolean", ": Boolean")
        .replace(": string", ": String")
        .replace(": unknown", ": Any")
        .replace(": any", ": Any")
        .replace(" -> number", " -> Int")
        .replace(" -> string", " -> String")
        .replace(" -> boolean", " -> Boolean")
        .replace(" -> unknown", " -> Any")
        .replace(" -> any", " -> Any")
        .replace("<number>", "<Int>")
        .replace("<any>", "<*>")
        .replace("number[]", "ReadonlyArray<Int>")
        .replace("undefined | [number, number]", "JsTuple2<Int, Int>?")
        .replace("boolean | (", "(")
        .replace("Map<any, number>", "Record<Any, Int> /* JS Map */")
        .replace("SortDirection | false", "SortDirection?")
        .replace("false | SortDirection", "SortDirection?")
        .replace(": Partial<TableOptions<TData>>", ": TableOptions<TData> /* Partial */")
        .replace("?: Row<TData>", ": Row<TData>?")
        .replace(" -> undefined | TData[]", " -> ReadonlyArray<TData>")
        .replace("Column<TData, unknown>", "Column<TData, *>")
        .replace("<TValue | null>", "<TValue?>")
}
