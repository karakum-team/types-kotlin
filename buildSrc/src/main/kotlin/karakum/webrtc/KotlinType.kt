package karakum.webrtc

internal const val DYNAMIC = "dynamic"
internal const val UNIT = "Unit"

internal const val STRING = "String"

private val STANDARD_TYPE_MAP = mapOf(
    "any" to "Any",
    "object" to "Any",
    "{}" to "Any",

    "boolean" to "Boolean",
    "string" to STRING,

    "never" to "Nothing",

    "number" to "Number",

    "void" to UNIT,
    "null" to "Nothing?",
    "undefined" to "Nothing?",

    "Date" to "kotlin.js.Date",
)

internal fun kotlinType(
    type: String,
    name: String,
): String {
    STANDARD_TYPE_MAP[type]
        ?.also { return it }

    if (type.endsWith(" | undefined"))
        return kotlinType(type.removeSuffix(" | undefined"), name)

    if (type.endsWith(" | null"))
        return kotlinType(type.removeSuffix(" | null"), name) + "?"

    if (" | " in type)
        return "$DYNAMIC /* $type */"

    if (type.startsWith("W3C."))
        return "$DYNAMIC /* $type */"

    if (type.endsWith("[]"))
        return "ReadonlyArray<${kotlinType(type.removeSuffix("[]"), name)}>"

    if (type.startsWith("Promise<")) {
        val parameter = kotlinType(type.removeSurrounding("Promise<", ">"), name)
        return "kotlin.js.Promise<$parameter>"
    }

    return type
}
