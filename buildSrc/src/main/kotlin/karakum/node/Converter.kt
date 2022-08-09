package karakum.node

private val IGNORE_LIST = setOf(
    "BufferConstructor",

    "FSWatcher",
    "StatSyncFn",
    "StatWatcher",
    "WatchOptions",
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
        mainContent += "\n\n" + (content
            .substringAfter(globalsStart)
            .substringBefore("\n}")
            .trimIndent()
            .let { "\n$it" })
    }

    val interfaces = mainContent
        .splitToSequence("\nexport interface ", "\ninterface ")
        .drop(1)
        .map { convertInterface(it) }
        .filter { it.name !in IGNORE_LIST }

    return when (pkg) {
        Package("buffer") -> interfaces
            .plus(BufferEncoding())

        Package("fs") -> interfaces
            .plus(convertFunctions(content, syncOnly = true))
            .plus(SymlinkType())
            .plus(WatchEventType())
            .plus(ConversionResult("PathLike", "typealias PathLike = String"))
            .plus(ConversionResult("PathOrFileDescriptor", "typealias PathOrFileDescriptor = PathLike"))
            .plus(ConversionResult("TimeLike", "typealias TimeLike = kotlin.js.Date"))
            .plus(ConversionResult("EncodingOption", "typealias EncodingOption = ObjectEncodingOptions?"))
            .plus(ConversionResult("BufferEncodingOption", "sealed interface BufferEncodingOption"))
            .plus(ConversionResult("WriteFileOptions", "typealias WriteFileOptions = node.buffer.BufferEncoding?"))
            .plus(ConversionResult("Mode", "typealias Mode = Int"))
            .plus(ConversionResult("OpenMode", "typealias OpenMode = Int"))
            .plus(ConversionResult("ReadPosition", "typealias ReadPosition = Number"))
            .plus(ConversionResult("Dir", "external class Dir"))
            .plus(ConversionResult("ReadStream", "external class ReadStream"))
            .plus(ConversionResult("WriteStream", "external class WriteStream"))

        Package("fs/promises") -> interfaces
            .plus(convertFunctions(content))

        Package("stream/web") -> emptySequence<ConversionResult>()
            .plus(ConversionResult("ReadableStream", "external class ReadableStream"))

        else -> interfaces
    }
}

private fun convertInterface(
    source: String,
): ConversionResult {
    val name = source.substringBefore(" ")
        .substringBefore("<")
        .substringBefore("(")
        .substringBefore(":")

    val declaration = source
        .substringBefore(" {}\n")
        .substringBefore(" {\n")
        .replace(" extends ", " : ")
        .replace("<number>", "<Number>")
        .replace("<bigint>", "<BigInt>")
        .replace("NodeJS.ArrayBufferView", "ArrayBufferView")
        .replace(" = Buffer", "")
        .replace("string | Buffer", "Any /* string | Buffer */")

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
        "Buffer" -> "class"
        else -> "sealed interface"
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
                .substringBeforeLast("\n */\n") + "\n */"

            convertFunction(functionSource, comment, syncOnly)
        }

private fun convertFunction(
    source: String,
    comment: String,
    syncOnly: Boolean,
): Sequence<ConversionResult> {
    val name = source.substringBefore("(")
    if (syncOnly && !name.endsWith("Sync"))
        return emptySequence()

    val parameters = source.substringAfter("(")
        .substringBeforeLast(")")
        .splitToSequence(", ")
        .map { convertParameter(it) }
        .joinToString(",\n")

    val returnType = kotlinType(source.substringAfter("): "), name)

    // ignore fallbacks
    if ("/* string | Buffer */" in returnType)
        return emptySequence()

    val returnDeclaration = when (returnType) {
        "Unit" -> ""
        else -> ": $returnType"
    }

    val finalName = if (returnType.startsWith("Promise<")) name + "Async" else name
    var body = "external fun $finalName(\n$parameters\n)$returnDeclaration"

    if (name != finalName)
        body = "@JsName(\"$name\")\n$body"

    if (comment.isNotEmpty())
        body = "$comment\n$body"

    return sequenceOf(
        ConversionResult(finalName, body),
    )
}

private fun convertParameter(
    source: String,
): String {
    val name = source
        .substringBefore("?:")
        .substringBefore(":")

    val optional = source.startsWith("$name?")
    val type = kotlinType(source.substringAfter(": "), name) +
            if (optional) " = definedExternally" else ""

    return "$name: $type"
}
