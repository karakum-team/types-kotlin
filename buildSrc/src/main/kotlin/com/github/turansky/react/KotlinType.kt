package com.github.turansky.react

internal const val DYNAMIC = "dynamic"
internal const val UNIT = "Unit"

private val STANDARD_TYPE_MAP = mapOf(
    "any" to "Any",
    "object" to "Any",

    "boolean" to "Boolean",
    "number" to "Number",
    "string" to "String",

    "void" to UNIT,
    "null" to "Nothing?",

    "Date" to "kotlin.js.Date",

    "DataTransfer" to "org.w3c.dom.DataTransfer",

    // TODO: use React interface instead
    "TouchList" to "org.w3c.dom.TouchList",
    "AbstractView" to DYNAMIC,

    // TODO: check
    "Booleanish" to "Boolean",
    "number | string" to "Number",
)

internal fun kotlinType(
    type: String,
    name: String,
): String {
    STANDARD_TYPE_MAP[type]
        ?.also { return it }

    if (type.endsWith(" | null")) {
        val t = kotlinType(type.removeSuffix(" | null"), name)
        return if (t == DYNAMIC) t else "$t?"
    }

    if (type.startsWith("'") || type.startsWith("\""))
        return "String /* $type */"

    return type
}
