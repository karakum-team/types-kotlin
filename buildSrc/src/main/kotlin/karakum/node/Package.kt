package karakum.node

import karakum.common.snakeToCamel

internal data class Package(
    val name: String,
) {
    private val root = name
        .removeSuffix("/promises")
        .replace("stream/web", "web/streams")
        .snakeToCamel()

    val id: String = concat("node", ":", name)
    val pkg: String = "package " + concat("node", ".", root.replace("/", "."))

    val path: String = concat("node", "/", root)
}

private fun concat(
    base: String,
    delimiter: String,
    suffix: String,
): String =
    when (suffix) {
        "globals",
        -> base

        "web/streams",
        "web.streams",
        -> suffix

        else -> "$base$delimiter$suffix"
    }
