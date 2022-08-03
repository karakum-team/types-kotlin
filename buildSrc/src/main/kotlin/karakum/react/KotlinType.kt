package karakum.react

internal const val DYNAMIC = "dynamic"
internal const val UNIT = "Unit"

internal const val STRING = "String"

internal const val INT = "Int"
internal const val DOUBLE = "Double"

private val STANDARD_TYPE_MAP = mapOf(
    "any" to "Any",
    "object" to "Any",

    "boolean" to "Boolean",
    "string" to STRING,

    "void" to UNIT,
    "null" to "Nothing?",

    "Date" to "kotlin.js.Date",

    "Document" to "org.w3c.dom.Document",
    "DataTransfer" to "org.w3c.dom.DataTransfer",

    "Key" to "react.Key",
    "CSSProperties" to "react.CSSProperties",

    // TODO: use React interface instead
    "TouchList" to "org.w3c.dom.TouchList",

    // TODO: check
    "Booleanish" to "Boolean",
    "boolean | string" to "Boolean",
)

private val ANY_ALIASES = setOf(
    "string | ReadonlyArray<string> | number",
    "string | number | ReadonlyArray<string>",
)

internal fun kotlinType(
    type: String,
    name: String,
): String {
    if (type.endsWith(" | undefined"))
        return kotlinType(type.removeSuffix(" | undefined"), name)

    if ("; // " in type)
        return kotlinType(type.substringBefore("; // "), name) +
                " // " + type.substringAfter("; // ")

    if (type == "number" || type == "number | string")
        return numberType(name)

    if (type in ANY_ALIASES)
        return "Any /* $type */"

    STANDARD_TYPE_MAP[type]
        ?.also { return it }

    if (type.endsWith(" | null")) {
        val t = kotlinType(type.removeSuffix(" | null"), name)
        return if (t == DYNAMIC) t else "$t?"
    }

    if (type.startsWith("string | "))
        return "$DYNAMIC // $type"

    if (type.startsWith("Booleanish | "))
        return "$DYNAMIC // $type"

    return type
}
