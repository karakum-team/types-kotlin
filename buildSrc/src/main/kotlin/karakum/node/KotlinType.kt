package karakum.node

internal const val DYNAMIC = "dynamic"
internal const val UNIT = "Unit"

internal const val STRING = "String"

private val STANDARD_TYPE_MAP = mapOf(
    "any" to "Any",
    "object" to "Any",
    "{}" to "Any",

    "boolean" to "Boolean",
    "true" to "Boolean /* true */",

    "string" to STRING,

    "never" to "Nothing",

    "number" to "Number",
    "bigint" to "BigInt",

    "void" to UNIT,
    "null" to "Void",
    "undefined" to "Void",

    "Date" to "kotlin.js.Date",

    "Uint8Array | ReadonlyArray<number>" to "Uint8Array /* | ReadonlyArray<number> */",
    "NodeJS.ArrayBufferView" to "ArrayBufferView",

    "Blob" to "org.w3c.files.Blob",

    "() => void" to "() -> Unit",

    "Buffer" to "node.buffer.Buffer",
    "BufferEncoding" to "node.buffer.BufferEncoding",
    "ReadableStream" to "node.stream.ReadableStream",
    "symlink.Type" to "SymlinkType",

    "ObjectEncodingOptions | BufferEncoding" to "node.buffer.BufferEncoding /* ObjectEncodingOptions | BufferEncoding */",

    "AsyncIterable<FileChangeInfo<string>> | AsyncIterable<FileChangeInfo<Buffer>>" to
            "AsyncIterable<FileChangeInfo<Any /* string | Buffer */>>",

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

    if (" is " in type)
        return "Boolean /* $type */"

    if (type.endsWith(" | undefined"))
        return kotlinType(type.removeSuffix(" | undefined"), name)

    if (type.endsWith(" | null")) {
        var resultType = kotlinType(type.removeSuffix(" | null"), name)
        if (!resultType.startsWith(DYNAMIC)) {
            if (" /* " in resultType) {
                resultType = resultType.replace(" /* ", "? /* ")
            } else {
                resultType += "?"
            }
        }

        return resultType
    }

    if (" | " in type && !type.startsWith("Promise<"))
        return "Any /* $type */"

    if (type.endsWith("[]"))
        return "ReadonlyArray<${kotlinType(type.removeSuffix("[]"), name)}>"

    for (typedType in TYPED) {
        if (type.startsWith("$typedType<"))
            return "$typedType<${kotlinType(type.removeSurrounding("$typedType<", ">"), name)}>"
    }

    if (type.startsWith("Promise<")) {
        var parameter = kotlinType(type.removeSurrounding("Promise<", ">"), name)
        if (parameter == UNIT)
            parameter = "Void"

        return "Promise<$parameter>"
    }

    // TODO: remove
    if ("IterableIterator" in type)
        return "$DYNAMIC /* $type */"

    return type
        .replace("<string>", "<String>")
}
