package karakum.query

class Class(
    override val source: String,
) : TypeBase() {
    override val name: String =
        getTypeName(source, JsTypeKeyword.CLASS)

    override val openByDefault: Boolean = true

    override fun toCode(): String {
        val extends = parentType?.let {
            (if (typeParameters.isNotEmpty()) "\n" else "") + ": $it"
        } ?: ""

        val constructor = members.asSequence()
            .filterIsInstance<Constructor>()
            .firstOrNull()

        return "open external class $name ${formatParameters(typeParameters)}" +
                (constructor?.toCode() ?: "") +
                "$extends {\n$content\n}"
    }
}
