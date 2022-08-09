package karakum.node

internal data class Package(
    val name: String,
) {
    private val root = name.substringBefore("/")

    val id: String = "node:$name"
    val pkg: String = "package node.$root"

    val path: String = "node/$root"
}
