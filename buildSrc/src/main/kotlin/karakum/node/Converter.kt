package karakum.node

private val IGNORE_LIST = setOf(
    "BufferConstructor",

    "BigIntOptions",
    "BigIntStats",
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

    val functions = convertFunctions(content)

    val interfaces = mainContent
        .splitToSequence("\nexport interface ", "\ninterface ")
        .drop(1)
        .map { convertInterface(it) }
        .filter { it.name !in IGNORE_LIST }

    return when (pkg) {
        Package("buffer") -> interfaces
            .plus(BufferEncoding())

        Package("fs") -> interfaces
            .plus(functions)
            .plus(SymlinkType())
            .plus(ConversionResult("PathLike", "typealias PathLike = String"))
            .plus(ConversionResult("PathOrFileDescriptor", "typealias PathOrFileDescriptor = PathLike"))
            .plus(ConversionResult("TimeLike", "typealias TimeLike = kotlin.js.Date"))
            .plus(ConversionResult("EncodingOption", "typealias EncodingOption = ObjectEncodingOptions?"))
            .plus(ConversionResult("WriteFileOptions", "typealias WriteFileOptions = node.buffer.BufferEncoding?"))
            .plus(ConversionResult("Mode", "typealias Mode = Int"))
            .plus(ConversionResult("OpenMode", "typealias OpenMode = Int"))
            .plus(ConversionResult("ReadPosition", "typealias ReadPosition = Number"))
            .plus(ConversionResult("Dir", "external class Dir"))

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
        .replace("NodeJS.ArrayBufferView", "org.khronos.webgl.ArrayBufferView")

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
): Sequence<ConversionResult> =
    source
        .splitToSequence("\nexport function ")
        .drop(1)
        .map { it.substringBefore(";\nexport ") }
        .map { it.substringBefore(";\ninterface ") }
        .map { it.substringBefore("\n/**") }
        .map { it.removeSuffix(";") }
        .filter { "{" !in it }
        .mapNotNull { functionSource ->
            convertFunction(functionSource)?.let { result ->
                val comment = "/**\n" + source.substringBefore(functionSource)
                    .substringAfterLast("\n/**\n")
                    .substringBeforeLast("\n */\n") + "\n */"

                result.copy(result.name, comment + "\n" + result.body)
            }
        }

private fun convertFunction(
    source: String,
): ConversionResult? {
    val name = source.substringBefore("(")
    if (!name.endsWith("Sync"))
        return null

    val parameters = source.substringAfter("(")
        .substringBeforeLast(")")
        .splitToSequence(", ")
        .map { convertParameter(it) }
        .joinToString(",\n")

    val returnType = kotlinType(source.substringAfter("): "), name)
    val returnDeclaration = when (returnType) {
        "Unit" -> ""
        else -> ": $returnType"
    }

    val body = "external fun $name(\n$parameters\n)$returnDeclaration"

    return ConversionResult(name, body)
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
