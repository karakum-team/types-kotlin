package karakum.router

private val OPTIONS_BODY = """
{
    target?: React.HTMLAttributeAnchorTarget;
    replace?: boolean;
    state?: any;
}
""".trimIndent()

internal fun toClickHandler(
    name: String,
    source: String,
): String? {
    if (OPTIONS_BODY !in source)
        return null

    return sequenceOf(
        convertInterface(
            name = "ClickHandlerOptions",
            source = "interface ClickHandlerOptions $OPTIONS_BODY",
        ),
        convertFunction(
            name = name,
            source = source
                .replace("{ target, replace: replaceProp, state }", "options")
                .replace(OPTIONS_BODY, "ClickHandlerOptions")
        )
    ).joinToString("\n\n")
}
