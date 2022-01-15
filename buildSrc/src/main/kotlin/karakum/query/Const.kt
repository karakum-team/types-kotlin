package karakum.query

class Const(
    override val source: String,
) : Declaration() {
    override val name: String = source
        .substringAfter("${JsTypeKeyword.CONST} ")
        .substringBefore(": ")

    private val type: String by lazy {
        kotlinType(
            source.substringAfter(": ")
                .removeSuffix(";")
        )
    }

    override fun toCode(): String {
        return "external val $name: $type"
    }
}
