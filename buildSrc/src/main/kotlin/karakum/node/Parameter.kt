package karakum.node

data class Parameter(
    val name: String,
    val type: String,
    val optional: Boolean,
) {
    override fun toString(): String =
        "$name: $type" + if (optional) " = definedExternally" else ""
}
