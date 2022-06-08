package karakum.router

internal fun convertType(
    name: String,
    source: String,
): String {
    val body = source.substringAfter(" = ")
        .removeSuffix(";")

    val alias = when {
        name == "Params" -> "kotlinx.js.Record<String, String>"
        name == "URLSearchParamsInit" -> "kotlinx.js.Record<String, String> // $body"

        body == "string" -> "String"
        body == "unknown" -> "Any?"
        body == "object | null" -> "Any?"
        body == "[string, string]" -> "kotlinx.js.JsPair<String, String>"

        body.startsWith("Pick<History, ") -> "history.History"
        body.startsWith("Partial<") -> "Any // $body"
        body.startsWith("string | ") -> "String // $body"

        else -> null
    }

    if (name == "State" && alias == "Any?")
        return "sealed external interface Location$name"

    if (alias != null)
        return "typealias $name = $alias"

    if (" = {\n" in source)
        return convertInterface(
            name = name,
            source = source
                .replaceFirst("type ", "interface ")
                .replaceFirst(" = {", " {")
        )

    return source
}
