package karakum.node

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
        .substringAfter("namespace ${pkg.name} {\n")
        .substringBefore("\n}")
        .trimIndent()
        .let { "\n$it" }

    val interfaces = content
        .splitToSequence("\nexport interface ", "\ninterface ")
        .drop(1)
        .map { convertInterface(it) }

    return interfaces
}

private fun convertInterface(
    source: String,
): ConversionResult {
    val name = source.substringBefore(" ")
        .substringBefore("<")
        .substringBefore("(")
        .substringBefore(":")

    val declaration = source.substringBefore(" {\n")
    val bodySource = source.substringAfter(" {\n")
        .let { if (it.startsWith("}")) "" else it }
        .substringBefore("\n}")
        .trimIndent()

    val body = "" /*convertMembers(
        name = name,
        source = bodySource,
    )*/

    return ConversionResult(
        name = name,
        body = "external sealed interface $declaration {\n$body\n}",
    )
}
