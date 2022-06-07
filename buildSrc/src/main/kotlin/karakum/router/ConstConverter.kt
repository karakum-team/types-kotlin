package karakum.router

internal fun convertConst(
    name: String,
    source: String,
): String {
    if (name.endsWith("Context")) {
        val annotation = when (name) {
            "NavigationContext",
            "LocationContext",
            "RouteContext",
            -> "@JsName(\"UNSAFE_$name\")"

            else -> null
        }

        val declaration = source
            .replace("const ", "external val ")
            .replace("React.", "react.")

        return listOfNotNull(annotation, declaration)
            .joinToString("\n")
    }

    return "external val $name: react.FC<${name}Props>"
}
