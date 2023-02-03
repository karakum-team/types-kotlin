package karakum.actions

private val STANDARD_TYPE_MAP = mapOf(
    "boolean" to "Boolean",
    "string" to "String",
    "number" to "Number",

    "string[]" to "ReadonlyArray<String>",
    "Record<string, string>" to "Record<String, String>",

    "typeof http | typeof https" to "Any /* typeof http | typeof https */",

    "NodeJS.ReadableStream" to "node.ReadableStream",
)

internal fun kotlinType(
    type: String,
): String {
    STANDARD_TYPE_MAP[type]?.also {
        return it
    }

    if ("." in type)
        return "node.$type"

    if (type.endsWith("[]"))
        return "ReadonlyArray<${type.removeSuffix("[]")}>"

    return type
        .replace(": string)", ": String)")
        .replace(") => void", ") -> Unit")
        .replace(" | null", "?")

}
