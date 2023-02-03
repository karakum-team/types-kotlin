package karakum.actions

private val STANDARD_TYPE_MAP = mapOf(
    "boolean" to "Boolean",
    "string" to "String",
    "number" to "Number",

    "string[]" to "ReadonlyArray<String>",
    "Record<string, string>" to "Record<String, String>",

    "typeof http | typeof https" to "Any /* typeof http | typeof https */",

    "NodeJS.ReadableStream" to "node.ReadableStream",

    "Promise<string[]>" to "Promise<ReadonlyArray<String>>",
    "Promise<DownloadResponse[]>" to "Promise<ReadonlyArray<DownloadResponse>>",

    "AsyncGenerator<string, void>" to "Any /* AsyncGenerator<string, void> */"
)

internal fun kotlinType(
    type: String,
): String {
    STANDARD_TYPE_MAP[type]?.also {
        return it
    }

    if (type.endsWith(" | null"))
        return kotlinType(type.removeSuffix(" | null")) + "?"

    if ("." in type)
        return "node.$type"

    if (type.endsWith("[]"))
        return "ReadonlyArray<${type.removeSuffix("[]")}>"

    return type
        .replace(": string)", ": String)")
        .replace(") => void", ") -> Unit")
        .replace(") => ", ") -> ")
        .replace(" | undefined", "?")
        .replace(" | null", "?")

}
