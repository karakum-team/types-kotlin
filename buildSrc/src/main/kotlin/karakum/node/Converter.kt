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

    val interfaces = mainContent
        .splitToSequence("\nexport interface ", "\ninterface ")
        .drop(1)
        .map { convertInterface(it) }
        .filter { it.name !in IGNORE_LIST }

    return when (pkg) {
        Package("buffer") -> interfaces
            .plus(BufferEncoding())

        Package("fs") -> interfaces
            .plus(ConversionResult("Mode", "typealias Mode = Int"))
            .plus(ConversionResult("ReadPosition", "typealias ReadPosition = Number"))

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
