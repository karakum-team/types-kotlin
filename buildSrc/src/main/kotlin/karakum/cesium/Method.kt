package karakum.cesium

import karakum.common.withSuspendAdapter

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

        val modifierList = listOfNotNull(
            "external".takeIf { !hasParent },
            "abstract".takeIf { abstract },
            "override".takeIf { overridden },
            "operator".takeIf { isOperator },
        )

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

        val sourceDeclaration = "fun $name$params$returnExpression"
        val declarations = when (name) {
            "loadTileDataAvailability" -> listOf(sourceDeclaration)
            else -> withSuspendAdapter(sourceDeclaration).toList()
        }

        if (declarations.size == 1) {
            val modifiers = modifierList.joinToString(" ", "", " ")
            return doc + modifiers + declarations.single()
        }

        val declaration = declarations
            .drop(if ("override" in modifierList) 1 else 0)
            .map {
                val suspend = "suspend " in it
                val anchor = if (suspend) "suspend " else "fun "
                val modifiers = (modifierList - (if (suspend) "abstract" else ""))
                    .joinToString(" ", "", " ")

                val result = it.replaceFirst(anchor, "$modifiers $anchor")
                if (!suspend && "override" in modifierList && result.startsWith("@JsName(")) {
                    result.substringAfter("\n")
                } else result
            }
            .joinToString("\n\n")

        return doc + declaration
    }
}
