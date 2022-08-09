package karakum.node

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
    "bigint" to "BigInt",

    "void" to UNIT,
    "null" to "Nothing?",
    "undefined" to "Nothing?",

    "Date" to "kotlin.js.Date",

    "Event" to "org.w3c.dom.events.Event",
    "MessageEvent" to "org.w3c.dom.MessageEvent",

    "MediaStream" to "org.w3c.dom.mediacapture.MediaStream",
    "MediaStreamTrack" to "org.w3c.dom.mediacapture.MediaStreamTrack",

    "ArrayBuffer" to "org.khronos.webgl.ArrayBuffer",
    "NodeJS.ArrayBufferView" to "org.khronos.webgl.ArrayBufferView",

    "Blob" to "org.w3c.files.Blob",

    "() => void" to "() -> Unit",

    "BufferEncoding" to "node.buffer.BufferEncoding",
    "symlink.Type" to "SymlinkType",

    "-1 | 0 | 1" to "Int /* -1 | 0 | 1 */",
)

private val TYPED = setOf(
    "ReadonlyArray",
)

internal fun kotlinType(
    type: String,
    name: String,
): String {
    STANDARD_TYPE_MAP[type]
        ?.also { return it }

    if (type.endsWith(" | undefined"))
        return kotlinType(type.removeSuffix(" | undefined"), name)

    if (type.endsWith(" | null")) {
        var resultType = kotlinType(type.removeSuffix(" | null"), name)
        if (!resultType.startsWith(DYNAMIC))
            resultType += "?"

        return resultType
    }

    if (" | " in type)
        return "$DYNAMIC /* $type */"

    if (type.endsWith("[]"))
        return "ReadonlyArray<${kotlinType(type.removeSuffix("[]"), name)}>"

    for (typedType in TYPED) {
        if (type.startsWith("$typedType<"))
            return "$typedType<${kotlinType(type.removeSurrounding("$typedType<", ">"), name)}>"
    }

    if (type.startsWith("Promise<")) {
        val parameter = kotlinType(type.removeSurrounding("Promise<", ">"), name)
        return "kotlin.js.Promise<$parameter>"
    }

    // TODO: remove
    if ("IterableIterator" in type)
        return "$DYNAMIC /* $type */"

    return type
}
