package karakum.router

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

    "void" to UNIT,
    "null" to "Nothing?",

    "string | undefined" to "String?",

    "Date" to "kotlin.js.Date",

    "Window" to "web.window.Window",
    "URLSearchParams" to "web.url.URLSearchParams",

    "React.ReactNode" to "react.ReactNode",
    "React.ReactElement" to "react.ReactElement<*>",

    "string[]" to "js.core.ReadonlyArray<String>",
    "InitialEntry[]" to "js.core.ReadonlyArray<history.InitialEntry>",
    "RouteObject[]" to "js.core.ReadonlyArray<RouteObject>",
    "RouteMatch[]" to "js.core.ReadonlyArray<RouteMatch>",

    "To" to "history.To",
    "Path" to "history.Path",
    "Partial<Path>" to "history.Path",
    "Location" to "history.Location",
    "History" to "history.History",

    "PathPattern<Path> | Path" to STRING,
    "Context" to "Any?",

    "Partial<Location> | string" to "history.Location",
    "PathPattern | string" to "String /* PathPattern | string */",

    "React.HTMLAttributeAnchorTarget" to "web.window.WindowTarget",
    "(event: React.MouseEvent<E, MouseEvent>) => void" to "react.dom.events.MouseEventHandler<E>",

    "asserts cond" to "Boolean /* asserts cond */"
)

internal fun kotlinType(
    type: String,
    name: String,
): String {
    if (type.endsWith(" | null"))
        return kotlinType(type.removeSuffix(" | null"), name) + "?"

    when (type) {
        "false",
        "true",
        -> return "Boolean /* $type */"
    }

    if (type == "number") {
        return when (name) {
            "index",
            "initialIndex",
            -> INT

            else -> TODO("Support number type for property '$name'")
        }
    }

    if (name == "defaultInit" && type == "URLSearchParamsInit")
        return "web.url.URLSearchParams"

    if (name == "state") {
        when (type) {
            "unknown" -> return "history.LocationState?"
            "any" -> return "history.LocationState"
        }
    }

    STANDARD_TYPE_MAP[type]
        ?.also { return it }

    return type
}
