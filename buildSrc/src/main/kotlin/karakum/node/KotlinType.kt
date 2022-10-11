package karakum.node

internal const val DYNAMIC = "dynamic"
internal const val UNIT = "Unit"

internal const val STRING = "String"

private val STANDARD_TYPE_MAP = mapOf(
    "any" to "Any",
    "object" to "Any",
    "Object" to "Any",
    "{}" to "Any",

    "boolean" to "Boolean",
    "false" to "Boolean /* false */",
    "true" to "Boolean /* true */",

    "string" to STRING,

    "never" to "Nothing",
    "unknown" to "Any?",

    "number" to "Number",
    "bigint" to "BigInt",

    "void" to UNIT,
    "null" to "Void",
    "undefined" to "Void",

    "Function" to "Function<*>",

    "Date" to "kotlin.js.Date",

    "unique symbol" to "Symbol",
    "Array<string | symbol>" to "ReadonlyArray<String /* | Symbol */>",
    "NodeJS.TypedArray" to "ReadonlyArray<*> /* TypedArray */",

    "boolean | Promise<boolean>" to "PromiseResult<Boolean>",

    "[number, number]" to "JsTuple2<Int, Int>",
    "ReadonlySet<string>" to "JsSet<out String>",

    "Uint8Array | ReadonlyArray<number>" to "Uint8Array /* | ReadonlyArray<number> */",
    "NodeJS.EventEmitter" to "node.events.IEventEmitter",
    "NodeJS.ArrayBufferView" to "ArrayBufferView",
    "NodeJS.Platform" to "node.process.Platform",
    "NodeJS.ReadableStream" to "node.ReadableStream",
    "NodeJS.WritableStream" to "node.WritableStream",
    "NodeJS.Signals" to "Signals",
    "NodeJS.ProcessEnv" to "ProcessEnv",
    "NodeJS.ErrnoException" to "ErrnoException",

    "Blob" to "web.buffer.Blob",

    "() => void" to "() -> Unit",
    "(error?: Error) => void" to "(error: Error?) -> Unit",
    "(error: Error | null) => void" to "(error: Error?) -> Unit",
    "(error?: Error | null) => void" to "(error: Error?) -> Unit",
    "(error: Error | null | undefined) => void" to "(error: Error?) -> Unit",

    "Dirent | null) => void" to "Dirent?) -> Unit",
    "NodeJS.Signals | null) => void" to "Signals?) -> Unit",
    "string | Buffer) => void" to "Any /* string | Buffer */) -> Unit",
    "(ObjectEncodingOptions & ExecFileOptions)" to "ObjectEncodingOptions /* & ExecFileOptions */",
    "'buffer' | null; // specify `null`." to "BufferEncodingOption? // specify `null`.",
    "'buffer'" to "BufferEncodingOption",

    "BufferEncoding" to "node.buffer.BufferEncoding",
    "ReadableStream" to "web.streams.ReadableStream<*>",
    "stream.Readable" to "Readable",
    "stream.Duplex" to "Duplex",
    "stream.TransformOptions" to "TransformOptions",
    "stream.WritableOptions" to "WritableOptions",
    "symlink.Type" to "SymlinkType",

    "ObjectEncodingOptions | BufferEncoding" to "node.buffer.BufferEncoding /* ObjectEncodingOptions | BufferEncoding */",

    "IterableIterator<number>" to "JsIterable.Iterator<Int>",
    "IterableIterator<[number, number]>" to "JsIterable.Iterator<JsTuple2<Int, Int>>",

    "AsyncIterableIterator<any>" to "AsyncIterable.Iterator<*>",
    "AsyncIterable<FileChangeInfo<string>> | AsyncIterable<FileChangeInfo<Buffer>>" to
            "AsyncIterable<FileChangeInfo<Any /* string | Buffer */>>",

    "NodeJS.Dict<string>" to "Dict<String>",
    "NodeJS.Dict<NetworkInterfaceInfo[]>" to "Dict<ReadonlyArray<NetworkInterfaceInfo>>",
    "NodeJS.ReadOnlyDict<Socket[]>" to "ReadOnlyDict<ReadonlyArray<Socket>>",
    "NodeJS.ReadOnlyDict<IncomingMessage[]>" to "ReadOnlyDict<ReadonlyArray<IncomingMessage>>",

    "streamWeb.ReadableStream" to "web.streams.ReadableStream<*>",
    "streamWeb.WritableStream" to "web.streams.WritableStream<*>",
    "net.SocketConstructorOpts" to "node.net.SocketConstructorOpts",

    "-1 | 0 | 1" to "Int /* -1 | 0 | 1 */",
    "'\\\\' | '/'" to "String /* '\\\\' | '/' */",
    "';' | ':'" to "String /* ';' | ':' */",

    "UrlWithStringQuery" to "Url /* UrlWithStringQuery */",

    "PathLike | FileHandle" to "PathLike /* | FileHandle */",

    "typeof IncomingMessage" to "JsClass<IncomingMessage>",
    "typeof ServerResponse" to "JsClass<ServerResponse>",

    "(...args: any[]) => void" to "Function<Unit>",
    "typeof test" to "Function<Promise<Void>> /* typeof test */",

    // TEMP
    "Require" to "$DYNAMIC /* Require */",
    "EventLoopUtilityFunction" to "Function<*> /* EventLoopUtilityFunction */",
    "ReadableStreamDefaultReadResult<R>" to "Any /* ReadableStreamDefaultReadResult<R> */",
)

private val TYPED = setOf(
    "ReadonlyArray",
)

internal fun kotlinType(
    type: String,
    name: String,
): String {
    if (name == "event" && (type == "string" || type == "string | symbol"))
        return EVENT_TYPE

    STANDARD_TYPE_MAP[type]
        ?.let { return it }

    type.removeSurrounding("(", ")")
        .takeIf { it != type }
        ?.let { return "(${kotlinType(it, name)})" }

    if (" is " in type)
        return "Boolean /* $type */"

    if (name == "port" && (type.startsWith("string | number") || type.startsWith("number | string")))
        return STRING

    if (type.endsWith("\n| undefined"))
        return kotlinType(type.removeSuffix("\n| undefined"), name)

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

    functuionType(type)
        ?.let { return it }

    if (type.startsWith("{")) {
        if ("end?: boolean | undefined;" in type && type.count { it == ';' } == 1)
            return PIPE_OPTIONS
        return "Any /* $type */"
            .prependIndent("    ")
            .removePrefix("    ")
    }

    if (type.startsWith("["))
        return "ReadonlyArray<*> /* $type */"
            .prependIndent("    ")
            .removePrefix("    ")

    if (" | " in type && !type.startsWith("{") && !type.startsWith("Promise<") && !type.startsWith("Any /* ")) {
        if ("| BufferEncoding" in type)
            return "BufferEncoding /* $type */"

        return "Any /* $type */"
    }

    if (type.endsWith("[]")) {
        val typeSource = type
            .removePrefix("readonly ")
            .removeSuffix("[]")

        return "ReadonlyArray<${kotlinType(typeSource, name)}>"
    }

    for (typedType in TYPED) {
        if (type.startsWith("$typedType<"))
            return "$typedType<${kotlinType(type.removeSurrounding("$typedType<", ">"), name)}>"
    }

    if (type.startsWith("Promise<") && type.endsWith(">")) {
        val typeParameter = type.removeSurrounding("Promise<", ">")
        val parameter = when (typeParameter) {
            "unknown" -> "*"
            "void" -> "Void"
            else -> kotlinType(typeParameter, name)
        }

        return "Promise<$parameter>"
    }

    if (name == "event" && type.startsWith("'")) {
        val originalEventName = type.removeSurrounding("'")
        if (originalEventName.startsWith("test:"))
            return "TestEvent.${eventName(originalEventName.removePrefix("test:"))}"

        return "$EVENT.${eventName(originalEventName)}"
    }

    if (type.startsWith("'"))
        return "$STRING /* $type */"

    if (" & " in type)
        return (type.substringBefore(" & ") + " /* " + type.substringAfter(" & ") + " */")
            .prependIndent("    ")
            .removePrefix("    ")

    return type
        .replace("<string>", "<String>")
        .replace(": number", ": Number")
        .replace(": string", ": String")
        .replace(": boolean", ": Boolean")
        .replace("=> string", "-> String")
        .replace("=> number", "-> Number")
        .replace("=> void", "-> Unit")
        .replace("=> string", "-> String")
        .replace("=> Socket", "-> Socket")
        .replace(": any", ": Any")
        .replace(": unknown", ": Any?")
}
