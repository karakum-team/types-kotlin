package com.github.turansky.router

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

    "React.ReactNode" to "react.ReactNode",
    "React.ReactElement" to "react.ReactElement",

    "RouteObject[]" to "kotlinext.js.ReadonlyArray<RouteObject>",
    "RouteMatch[]" to "kotlinext.js.ReadonlyArray<RouteMatch>",

    "Location" to "history.Location",
)

internal fun kotlinType(
    type: String,
    name: String,
): String {
    if (type.endsWith(" | null"))
        return kotlinType(type.removeSuffix(" | null"), name)

    STANDARD_TYPE_MAP[type]
        ?.also { return it }

    return type
}
