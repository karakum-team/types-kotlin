package com.github.turansky.router

private val CLASS_NAME = """
    className?: string | ((props: {
        isActive: boolean;
    }) => string);
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
        .replace("interface ", "external interface ")
        .replace(" extends ", " : ")
        .replace(
            "Omit<React.AnchorHTMLAttributes<HTMLAnchorElement>, \"href\">",
            "react.dom.html.AnchorHTMLAttributes<org.w3c.dom.HTMLAnchorElement>",
        )
        .replace(
            "Omit<LinkProps, \"className\" | \"style\">",
            "LinkProps",
        )

    if (name == "OutletProps")
        declaration += ": react.Props"

    val membersSource = source
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

    if (name.endsWith("Props") && ":" !in declaration) {
        val parentType = if ("var children: react.ReactNode?" in members) {
            members = members.replace(
                "var children: react.ReactNode?",
                "override var children: kotlinext.js.ReadonlyArray<react.ReactNode>?",
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
        .map(::invokeWrapper)
        .joinToString("\n\n")

    return "class $name\nprivate constructor() {\n$body\n}"
}

private fun invokeWrapper(
    source: String,
): String {
    val parameters = source
        .substringAfter("(")
        .substringBefore(")")
        .splitToSequence(", ")
        .map { it.substringBefore("?:") }
        .map { it.substringBefore(":") }
        .joinToString(", ")

    return "inline operator fun invoke" + source
        .replace(": To,", ": history.To,")
        .replace("?: NavigateOptions", ": NavigateOptions? = null")
        .replace("delta: number", "delta: Int")
        .replace("): void", ") {\nasDynamic()($parameters)\n}")
}
