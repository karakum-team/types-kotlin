package karakum.node

internal data class Package(
    val name: String,
) {
    val id: String = "node:$name"
    val pkg: String = "package node.${name.removeSuffix("/promises")}"

    val path: String = "node/${name.removeSuffix("/promises")}"
}
