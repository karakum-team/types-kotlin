package karakum.typescript

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

    "number" to "Double",

    "void" to UNIT,
    "null" to "Nothing?",
    "undefined" to "Nothing?",

    "Date" to "kotlin.js.Date",

    "false" to "Boolean /* false */",
    "true" to "Boolean /* true */",

    "MapLike<string>" to "MapLike<String>",
    "MapLike<string[]>" to "MapLike<ReadonlyArray<String>>",

    "-1" to "Double /* -1 */",
)

internal fun kotlinType(
    type: String,
    name: String,
): String {
    if (type.startsWith("readonly "))
        return kotlinType(type.removePrefix("readonly "), name)

    if (type.endsWith(" | undefined"))
        return kotlinType(type.removeSuffix(" | undefined"), name)
            .addOptionality()

    STANDARD_TYPE_MAP[type]
        ?.also { return it }

    if (" | " in type)
        return "$DYNAMIC /* $type */"

    if (type.startsWith("\""))
        return "$STRING /* $type */"

    if ("[\"" in type)
        return "$DYNAMIC /* $type */"

    if (type.endsWith("[]"))
        return "ReadonlyArray<${kotlinType(type.removeSuffix("[]"), name)}>"

    if (type.startsWith("Promise<"))
        return type.replaceFirst("Promise<", "kotlin.js.Promise<")

    return type
}
