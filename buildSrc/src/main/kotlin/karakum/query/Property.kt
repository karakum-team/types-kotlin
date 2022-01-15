package karakum.query

private const val MARK_OPTIONAL: Boolean = false

class Property(
    override val source: String,
    private val isVar: Boolean,
) : Member() {
    private val type: String by lazy {
        kotlinType(source.substringAfter(": "), name)
            .fixDefaultOptions()
    }

    override fun toCode(): String {
        val optional = MARK_OPTIONAL && source.startsWith("$name?: ")
        val typeDeclaration = if (optional && type != DYNAMIC && !type.endsWith(">?")) {
            if (type.startsWith("(")) "($type)?" else "$type?"
        } else type

        return "$modifiers ${if (isVar) "var" else "val"} ${safeName(name)}: $typeDeclaration"
    }
}
