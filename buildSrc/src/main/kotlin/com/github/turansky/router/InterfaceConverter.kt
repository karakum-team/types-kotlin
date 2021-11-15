package com.github.turansky.router

private val CONVERTABLE = setOf(
    "Path",
    "Location",
    "Update",
    "RouteMatch",
)

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
    when {
        name in CONVERTABLE -> Unit
        name.endsWith("Object") -> Unit
        name.endsWith("Options") -> Unit
        name.endsWith("Props") -> Unit
        else -> return source
    }

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

    var members = source
        .replace(CLASS_NAME, "")
        .replace(STYLE, "")
        .substringAfter(" {\n")
        .also { if (it == "}") return declaration }
        .substringBefore(";\n}")
        .trimIndent()
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

private fun convertMember(
    source: String,
): String {
    val comment = source.substringBeforeLast("\n", "")
    val body = source.substringAfterLast("\n")

    val declaration = convertParameter(body)

    return sequenceOf(comment, declaration)
        .filter { it.isNotEmpty() }
        .joinToString("\n")
}

private fun convertParameter(
    source: String,
): String {
    val name = source
        .substringBefore("?: ")
        .substringBefore(": ")

    var type = kotlinType(source.substringAfter(": "), name)
    if ("?: " in source && !type.endsWith("?"))
        type += "?"

    return "var $name: $type"
}
