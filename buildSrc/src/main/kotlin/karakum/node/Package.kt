package karakum.node

import karakum.common.snakeToCamel

internal data class Package(
    val name: String,
) {
    private val root = name.substringBefore("/")
        .snakeToCamel()

    val id: String = concat("node", ":", name)
    val pkg: String = "package " + concat("node", ".", root)

    val path: String = concat("node", "/", root)
}

private fun concat(
    base: String,
    delimiter: String,
    suffix: String,
): String =
    if (suffix != "globals") {
        "$base$delimiter$suffix"
    } else {
        base
    }
