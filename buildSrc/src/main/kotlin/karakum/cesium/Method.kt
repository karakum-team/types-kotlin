package karakum.cesium

internal class Method(
    override val source: Definition,
) : Member() {
    override val name = source.parseFunctionName()
    override val docName: String
        get() = if (static) ".$name" else name

    private val modifiers = source.body
        .substringBefore("(")
        .split(" ")
        .dropLast(1)

    override val static: Boolean by lazy {
        "static" in modifiers || (hasParent && parent is Namespace)
    }

    private val parameters = source.parseFunctionParameters()
    private val returnType = source.parseFunctionReturnType(name)

    private val isOperator: Boolean =
        when (name) {
            "get" -> parameters.size == 1 && returnType != null
            "set" -> parameters.size == 2 && returnType == null
            else -> false
        }

    override fun toCode(): String {
        if (name == "toString" && parameters.isEmpty())
            return ""

        if (name == "equals" && parameters.size == 1)
            return ""

        val returnExpression = returnType
            // Nullability fix for `SceneTransforms`
            ?.let { if (name.startsWith("wgs84To")) "$it?" else it }
            ?.let { ": $it" } ?: ""

        val modifiers = (if (hasParent) "" else "external ") +
                (if (abstract) "abstract " else "") +
                (if (overridden) "override " else "") +
                (if (isOperator) "operator " else "")

        val link = if (hasParent) {
            DocLink(parent, this)
        } else {
            DocLink(this)
        }

        val doc = source.doc(link)
            .let { if (it.isNotEmpty()) "$it\n" else "" }

        var params = parameters.toCode()
        if (overridden) {
            params = params.replace(" = definedExternally", "")
        }

        return doc +
                "$modifiers fun $name $params$returnExpression"
    }
}
