package karakum.router

private val SEARCH_RESULT_TS = """
readonly [URLSearchParams, (nextInit: URLSearchParamsInit, navigateOptions?: {
    replace?: boolean | undefined;
    state?: any;
} | undefined) => void]
""".trimIndent()

private const val PATH_ALIAS = "{ pathname, search, hash }"

internal fun convertFunction(
    name: String,
    source: String,
): String {
    // Quick fix
    if (name == "useLocation" && ";\n" in source)
        return convertFunction(name, source.substringBefore(";\n"))

    if ("Props): " in source)
        return convertComponent(name, source)

    if (PATH_ALIAS in source)
        return convertFunction(name, source.replace(PATH_ALIAS, "path"))

    if (SEARCH_RESULT_TS in source)
        return convertFunction(name, source.replace(SEARCH_RESULT_TS, SEARCH_PARAMS_INSTANCE))

    toClickHandler(name, source)
        ?.let { return it }

    if ("{" in source)
        return source

    val body = source
        .substringAfter("(")
        .substringBefore("): ")

    val resultType = source
        .substringAfter("): ")
        .substringBeforeLast(";")
        .let { kotlinType(it, name) }

    val parameters = if (body.isNotEmpty()) {
        body.splitToSequence(", ")
            .map(::convertParameter)
            .map { "$it,\n" }
            .joinToString("", "\n")
    } else ""

    val typeParameter = if (name == "useLinkClickHandler") {
        source
            .substringBefore(">(", "")
            .substringAfter("<")
            .replace("E extends Element = HTMLAnchorElement", "E: dom.Element")
            .takeIf { it.isNotEmpty() }
            ?.let { "<$it>" }
            ?: ""
    } else ""

    return "external fun $typeParameter $name($parameters): $resultType"
}

private fun convertParameter(
    source: String,
): String {
    val (name, type) = source.split("?: ", ": ")

    return name + ": " + kotlinType(type, name) +
            if ("?: " in source) " = definedExternally" else ""
}

private fun convertComponent(
    name: String,
    source: String,
): String {
    var propsType = source
        .substringBefore("): ")
        .substringAfterLast(": ")

    if (name == "Route")
        propsType = "RouteProps /* $propsType */"

    var result = "external val $name: react.FC<$propsType>"
    if (name == "HistoryRouter")
        result = "@JsName(\"unstable_$name\")\n$result"

    return result
}
