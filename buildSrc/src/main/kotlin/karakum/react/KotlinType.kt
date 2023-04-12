package karakum.react

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

    "Document" to "web.dom.Document",
    "DataTransfer" to "web.data.DataTransfer",
    "AbstractView" to "Window",

    "Key" to "react.Key",
    "CSSProperties" to "react.CSSProperties",

    // TODO: use React interface instead
    "TouchList" to "web.uievents.TouchList",
    "string | TrustedHTML" to "String /* | TrustedHTML */",

    // TODO: check
    "Booleanish" to "Boolean",
    "boolean | string" to "Boolean",
)

private val ANY_ALIASES = setOf(
    "string | ReadonlyArray<string> | number",
    "string | number | ReadonlyArray<string>",
    "number | Date",
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
        return "$t?"
    }

    if (type.startsWith("string | "))
        return "Any // $type"

    if (type.startsWith("Booleanish | "))
        return "Any // $type"

    return type
}
