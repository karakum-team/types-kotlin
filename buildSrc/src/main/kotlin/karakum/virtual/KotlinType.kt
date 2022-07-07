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
    "[string, number][]" to "ReadonlyArray<JsPair<String, Int>>",

    "Record<string, boolean>" to "Record<String, Boolean>",
    "Record<string, any>" to "Record<String, Any>",

    "() => boolean" to "() -> Boolean",
    "() => number" to "() -> Int",
    "() => number[]" to "() -> ReadonlyArray<Int>",
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
        .replace("undefined | [number, number]", "JsPair<Int, Int>?")
        .replace("boolean | (", "(")
}
