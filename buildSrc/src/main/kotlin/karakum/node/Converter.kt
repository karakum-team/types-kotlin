package karakum.node

private val IGNORE_LIST = setOf(
    "Global",

    "FSWatcher",
    "StatSyncFn",
    "StatWatcher",
    "WatchOptions",

    "UrlWithParsedQuery",
    "UrlWithStringQuery",

    "URL",
    "URLSearchParams",
)

internal data class ConversionResult(
    val name: String,
    val body: String,
)

internal fun convertDefinitions(
    source: String,
    pkg: Package,
): Sequence<ConversionResult> {
    val content = source
        .substringAfter("declare module '${pkg.name}' {\n")
        .substringBefore("\n}")
        .trimIndent()

    val namespaceStart = "namespace ${pkg.name} {\n"
    var mainContent = if (namespaceStart in content) {
        content
            .substringAfter(namespaceStart)
            .substringBefore("\n}")
            .trimIndent()
            .let { "\n$it" }
    } else content

    val globalsStart = "\nglobal {\n"
    if (globalsStart in content) {
        var globals = content
            .substringAfter(globalsStart)
            .substringBefore("\n}")
            .trimIndent()

        if (globals.startsWith("namespace NodeJS {\n") || globals.startsWith("var process: NodeJS.Process;"))
            globals = globals
                .substringAfter("namespace NodeJS {\n")
                .substringBefore("\n}")
                .trimIndent()

        mainContent += "\n\n$globals"
    }

    val interfaces = "\n$mainContent"
        .splitToSequence("\nexport interface ", "\ninterface ")
        .drop(1)
        .map { convertInterface(it, false) }
        .filter { it.name !in IGNORE_LIST }

    val classes = "\n$mainContent"
        .splitToSequence("\nexport class ", "\nclass ")
        .drop(1)
        .map { convertInterface(it, true) }
        .filter { it.name !in IGNORE_LIST }

    val types = "\n$mainContent"
        .splitToSequence("\ntype ")
        .drop(1)
        .mapNotNull { convertType(it) }

    return when (pkg) {
        Package("buffer") -> mergeBuffers(interfaces)

        Package("events") -> interfaces + classes

        Package("globals") -> abortClasses()
            .plus(ConversionResult("Dict", "typealias Dict<T> = Record<String, T>"))
            .plus(ConversionResult("ReadOnlyDict", "typealias ReadOnlyDict<T> = Record<String, out T>"))

        Package("fs") -> interfaces
            .plus(convertFunctions(content, syncOnly = true))
            .plus(SymlinkType())
            .plus(WatchEventType())
            .plus(BufferEncodingOption())
            .plus(ConversionResult("PathLike", "typealias PathLike = String"))
            .plus(ConversionResult("PathOrFileDescriptor", "typealias PathOrFileDescriptor = PathLike"))
            .plus(ConversionResult("TimeLike", "typealias TimeLike = kotlin.js.Date"))
            .plus(ConversionResult("EncodingOption", "typealias EncodingOption = ObjectEncodingOptions?"))
            .plus(ConversionResult("WriteFileOptions", "typealias WriteFileOptions = node.buffer.BufferEncoding?"))
            .plus(ConversionResult("Mode", "typealias Mode = Int"))
            .plus(ConversionResult("OpenMode", "typealias OpenMode = Int"))
            .plus(ConversionResult("ReadPosition", "typealias ReadPosition = Number"))
            .plus(ConversionResult("Dir", "external class Dir"))
            .plus(ConversionResult("ReadStream", "external class ReadStream"))
            .plus(ConversionResult("WriteStream", "external class WriteStream"))

        Package("fs/promises") -> interfaces
            .plus(convertFunctions(content))

        Package("os") -> interfaces
            .plus(convertFunctions(content))
            .plus(ConversionResult("NetworkInterfaceInfo", "typealias NetworkInterfaceInfo = NetworkInterfaceBase"))

        Package("path") -> interfaces
            .plus(rootVal("path", "PlatformPath"))

        Package("process") -> interfaces
            .plus(rootVal("process", "Process"))

        Package("querystring") -> interfaces
            .plus(convertFunctions(content))

        Package("stream/web") -> emptySequence<ConversionResult>()
            .plus(ConversionResult("ReadableStream", "external class ReadableStream"))

        Package("url") -> interfaces
            .plus(convertFunctions(content))
            .plus(ConversionResult("URL.alias", "typealias URL = org.w3c.dom.url.URL"))
            .plus(
                ConversionResult(
                    "URLSearchParams.alias",
                    "typealias URLSearchParams = org.w3c.dom.url.URLSearchParams"
                )
            )

        else -> interfaces
    } + types
}

private fun convertType(
    source: String,
): ConversionResult? {
    val name = source.substringBefore(" =")
    val bodySource = source.substringAfter(" =")
        .removePrefix(" ")
        .substringBefore(";")

    convertUnion(name, bodySource)?.let {
        return it
    }

    if (!bodySource.startsWith("("))
        return null

    var body = bodySource
        .replace("<unknown>", "<*>")
        .replace(": unknown", ": Any?")
        .replace(" => void", " -> Unit")
        .replace("code: number", "code: Int")
        // TEMP
        .replace("worker: Worker", "worker: Any /* Worker */")

    if (!body.startsWith("()"))
        body = body
            .replaceFirst("(", "(\n")
            .replace(",", ",\n")

    return ConversionResult(name, "typealias $name = $body")
}

private fun convertInterface(
    source: String,
    classMode: Boolean,
): ConversionResult {
    var name = source.substringBefore(" ")
        .substringBefore("<")
        .substringBefore("(")
        .substringBefore(":")

    if (!classMode && name == "EventEmitter")
        return convertInterface("I$source", classMode)

    if (" extends NodeJS.Dict<" in source) {
        val type = source
            .substringAfter(" extends NodeJS.Dict<")
            .substringBeforeLast("> {")

        return ConversionResult(
            name = name,
            body = "typealias $name = Dict<Any /* $type */>",
        )
    }

    var declaration = source
        .removeSuffix("{}")
        .substringBefore(" {}\n")
        .substringBefore(" {\n")
        .replace(" extends ", " : ")
        .replace("<number>", "<Number>")
        .replace("<string>", "<String>")
        .replace("<bigint>", "<BigInt>")
        .replace("NodeJS.ArrayBufferView", "ArrayBufferView")
        .replace(" = Buffer", "")
        .replace("string | Buffer", "Any /* string | Buffer */")
        .replace(": EventEmitter", if (classMode) ": node.events.EventEmitter" else ": node.events.IEventEmitter")
        // TEMP
        .replace(": tty.ReadStream", "/* : tty.ReadStream */")
        .replace(": tty.WriteStream", "/* : tty.WriteStream */")
        .replace(": ReadWriteStream", "/* : ReadWriteStream */")

    if (name == "EventEmitter")
        declaration += " : IEventEmitter"

    val bodySource = if (!source.substringBefore("\n").endsWith("{}")) {
        source.substringAfter(" {\n")
            .let { if (it.startsWith("}")) "" else it }
            .substringBefore("\n}")
            .trimIndent()
            .replace("): this", "): $name")
            .replace("toJSON(): {\n    type: 'Buffer';\n    data: number[];\n};", "toJSON(): any;")
            .replace(";\n *", ";--\n *")
    } else ""

    val body = convertMembers(bodySource)
        .replace(";--\n *", ";\n *")

    val type = when (name) {
        "Buffer",
        "BufferConstructor",
        -> "class"

        "IEventEmitter",
        -> "interface"

        "EventEmitter",
        -> "abstract class"

        else -> if (classMode) "class" else "sealed interface"
    }

    return ConversionResult(
        name = name,
        body = "external $type $declaration {\n$body\n}",
    )
}

private fun convertFunctions(
    source: String,
    syncOnly: Boolean = false,
): Sequence<ConversionResult> =
    source
        .splitToSequence("\nexport function ", "\nfunction ")
        .drop(1)
        .map { it.substringBefore(";\nexport ") }
        .map { it.substringBefore(";\ninterface ") }
        .map { it.substringBefore("\n/**") }
        .map { it.removeSuffix(";") }
        .filter { "{" !in it }
        .flatMap { functionSource ->
            val comment = "/**\n" + source.substringBefore(functionSource)
                .substringAfterLast("\n/**\n")
                .substringBeforeLast("\n */\n")
                .let { it + "\n */" }
                .replace("* /*\n", "* ---\n")

            convertFunction(functionSource, comment, syncOnly)
        }

private fun convertFunction(
    source: String,
    comment: String,
    syncOnly: Boolean,
): Sequence<ConversionResult> {
    val name = source.substringBefore("(")
    if (syncOnly && !(name.endsWith("Sync") || name.endsWith("Stream")))
        return emptySequence()

    if ("parseQueryString: false" in source || "parseQueryString: true" in source)
        return emptySequence()

    val parameters = source.substringAfter("(")
        .substringBeforeLast(")")
        .splitToSequence(", ")
        .filter { it.isNotEmpty() }
        .map { convertParameter(it) }
        .toList()

    val returnType = kotlinType(source.substringAfter("): "), name)

    // ignore fallbacks
    if ("/* string | Buffer */" in returnType)
        return emptySequence()

    val returnDeclaration = when (returnType) {
        "Unit" -> ""
        else -> ": $returnType"
    }

    val finalName = if (returnType.startsWith("Promise<")) name + "Async" else name
    val params = parameters
        .takeIf { it.isNotEmpty() }
        ?.joinToString(",\n", "\n", ",\n")
        ?: ""

    var body = "external fun $finalName(" +
            params +
            ")$returnDeclaration"

    if (name != finalName)
        body = "@JsName(\"$name\")\n$body"

    if (comment.isNotEmpty())
        body = "$comment\n$body"

    return sequenceOf(
        ConversionResult(finalName, body),
        suspendFunctions(name, parameters, returnType)
    ).filterNotNull()
}

private fun convertParameter(
    source: String,
): Parameter {
    val name = source
        .substringBefore("?:")
        .substringBefore(":")

    return Parameter(
        name = name,
        type = kotlinType(source.substringAfter(": "), name),
        optional = source.startsWith("$name?"),
    )
}

private fun suspendFunctions(
    name: String,
    parameters: List<Parameter>,
    returnType: String,
): ConversionResult? {
    val promiseResult = returnType.removeSurrounding("Promise<", ">")
    if (promiseResult == returnType)
        return null

    val endIndex = parameters.lastIndex
    val startIndex = parameters.indexOfFirst { it.optional }
        .takeIf { it != -1 }
        ?: endIndex

    var body = (startIndex..endIndex)
        .map { parameters.subList(0, it + 1) }
        .map { it.map { it.copy(optional = false) } }
        .map { params -> suspendFunction(name, params, promiseResult) }
        .joinToString("\n\n")

    return ConversionResult(name, body)
}

private fun suspendFunction(
    name: String,
    parameters: List<Parameter>,
    returnType: String,
): String {
    val declaration = "suspend fun $name(" +
            parameters.joinToString(",\n", "\n", ",\n") +
            ")"

    val call = "${name}Async(" +
            parameters.joinToString(",\n", "\n", ",\n") {
                "${it.name} = ${it.name}"
            } +
            ").await()"

    return if (returnType != "Void") {
        "$declaration : $returnType =\n $call"
    } else {
        "$declaration {\n $call \n}"
    }
}
