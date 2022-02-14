package karakum.node

private val IGNORE_LIST = setOf(
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
    if (pkg == Package("buffer"))
        return sequenceOf(BufferEncoding())

    var content = source
        .substringAfter("declare module '${pkg.name}' {\n")
        .substringBefore("\n}")
        .trimIndent()

    val namespaceStart = "namespace ${pkg.name} {\n"
    if (namespaceStart in content) {
        content = content
            .substringAfter(namespaceStart)
            .substringBefore("\n}")
            .trimIndent()
            .let { "\n$it" }
    }

    val interfaces = content
        .splitToSequence("\nexport interface ", "\ninterface ")
        .drop(1)
        .map { convertInterface(it) }
        .filter { it.name !in IGNORE_LIST }

    return interfaces
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
    } else ""

    val body = convertMembers(bodySource)

    return ConversionResult(
        name = name,
        body = "external sealed interface $declaration {\n$body\n}",
    )
}
