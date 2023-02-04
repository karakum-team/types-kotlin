package karakum.virtual

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
    "VirtualItem[]" to "ReadonlyArray<VirtualItem>",
    "[string, number][]" to "ReadonlyArray<JsTuple2<String, Int>>",

    "Record<string, boolean>" to "Record<String, Boolean>",
    "Record<string, any>" to "Record<String, Any>",

    "() => boolean" to "() -> Boolean",
    "() => number" to "() -> Int",
    "() => number[]" to "() -> ReadonlyArray<Int>",
    "() => $ITEM_RANGE" to "() -> $ITEM_RANGE",
    "() => VirtualItem[]" to "() -> ReadonlyArray<VirtualItem>",
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
        .removeSurrounding("Required<", ">")
        .replace(" => ", " -> ")
        .replace(" -> void | (() -> void)", " -> (() -> Unit)?")
        .replace(") -> void", ") -> Unit")
        .replace("number[]", "ReadonlyArray<Int>")
        .replace("VirtualItem<TItemElement>[]", "ReadonlyArray<VirtualItem<TItemElement>>")
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
        .replace("undefined | [number, number]", "JsTuple2<Int, Int>?")
        .replace("boolean | (", "(")
        .replace("{ align, smoothScroll }?: ScrollToOffsetOptions", "options: ScrollToOffsetOptions?")
        .replace("{ align, smoothScroll, ...rest }?: ScrollToIndexOptions", "options: ScrollToIndexOptions?")
        .replace(" | null", "?")
}
