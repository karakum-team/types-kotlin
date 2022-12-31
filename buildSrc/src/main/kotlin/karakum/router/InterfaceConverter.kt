package karakum.router

private val CHILDREN = """
    children?: React.ReactNode | ((props: {
        isActive: boolean;
    }) => React.ReactNode);
""".removeSuffix("\n")

private val CLASS_NAME = """
    className?: string | ((props: {
        isActive: boolean;
    }) => string | undefined);
""".removeSuffix("\n")

private val STYLE = """
    style?: React.CSSProperties | ((props: {
        isActive: boolean;
    }) => React.CSSProperties);
""".removeSuffix("\n")

internal fun convertInterface(
    name: String,
    source: String,
): String {
    if (name == "NavigateFunction")
        return convertMultiFunction(name, source)

    var declaration = source.substringBefore(" {")
        .replace("<Path extends string = string>", "")
        .replace("interface ", "external interface ")
        .replace(" extends ", " : ")
        .replace(
            "Omit<React.AnchorHTMLAttributes<HTMLAnchorElement>, \"href\">",
            "react.dom.html.AnchorHTMLAttributes<web.html.HTMLAnchorElement>",
        )
        .replace(
            "Omit<LinkProps, \"className\" | \"style\" | \"children\">",
            "LinkProps",
        )

    if (name == "OutletProps")
        declaration += ": react.Props"

    val membersSource = source
        .replace(CHILDREN, "")
        .replace(CLASS_NAME, "")
        .replace(STYLE, "")
        .substringAfter(" {\n")
        .also { if (it == "}") return declaration }
        .substringBefore(";\n}")
        .trimIndent()

    if (membersSource.startsWith("("))
        return "typealias $name = ${membersSource.replace("): void", ") -> Unit")}"

    var members = membersSource
        .splitToSequence(";\n")
        .joinToString("\n", transform = ::convertMember)

    if (name == "PathPattern")
        members = members.replace("var path: history.Path", "var path: String")

    if (name.endsWith("Props") && ":" !in declaration) {
        val parentType = if ("var children: react.ReactNode?" in members) {
            members = members.replace(
                "var children: react.ReactNode?",
                "override var children: react.ReactNode?",
            )
            "react.PropsWithChildren"
        } else {
            "react.Props"
        }

        declaration += " : $parentType"
    }

    return "$declaration {\n$members\n}"
}

private fun convertMultiFunction(
    name: String,
    source: String,
): String {
    val body = source
        .substringAfter(" {\n")
        .substringBefore(";\n}")
        .trimIndent()
        .splitToSequence(";\n")
        .flatMap(::invokeWrapper)
        .joinToString("\n\n")

    return "sealed class $name {\n$body\n}"
}

private fun invokeWrapper(
    source: String,
): Sequence<String> {
    if (", options?: NavigateOptions" in source)
        return invokeWrapper(source.replace(", options?: NavigateOptions", "")) +
                invokeWrapper(source.replace(", options?: NavigateOptions", ", options: NavigateOptions"))

    val parameters = source
        .substringAfter("(")
        .substringBefore(")")
        .splitToSequence(", ")
        .map { it.substringBefore("?:") }
        .map { it.substringBefore(":") }
        .joinToString(", ")

    val result = "inline operator fun invoke" + source
        .replace(": To", ": history.To")
        .replace("delta: number", "delta: Int")
        .replace("): void", ") {\nasDynamic()($parameters)\n}")

    return sequenceOf(result)
}
