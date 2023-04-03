package karakum.browser

internal fun browserFunctions(
    content: String,
): Sequence<ConversionResult> =
    convertFunctions(
        content = content.replace(
            "\ndeclare function ",
            "\nfunction ",
        ),
        getPkg = ::getBrowserPkg,
    )

private fun getBrowserPkg(
    name: String,
): String? =
    when (name) {
        "atob",
        "btoa",
        -> "web.buffer"

        // TODO: support
        /*
        "createImageBitmap",
        -> "web.canvas"
        */

        "matchMedia",
        -> "web.cssom"

        "getComputedStyle",
        -> "web.dom"

        "reportError",
        -> "web.errors"

        "alert",
        "confirm",
        "prompt",
        -> "web.prompts"

        else -> null
    }

internal fun convertFunctions(
    content: String,
    getPkg: (name: String) -> String?,
): Sequence<ConversionResult> =
    Regex("""\nfunction (.+);""")
        .findAll(content)
        .map { it.groupValues[1] }
        .mapNotNull { convertFunctionResult(it, getPkg) }
        .groupBy { it.name }
        .values
        .asSequence()
        .map { items ->
            items.singleOrNull()
                ?: run {
                    val first = items.first()
                    ConversionResult(
                        name = first.name,
                        body = items.joinToString("\n\n") { it.body },
                        pkg = first.pkg,
                    )
                }
        }

private fun convertFunctionResult(
    source: String,
    getPkg: (name: String) -> String?,
): ConversionResult? {
    val name = source.substringBefore("(")
    val pkg = getPkg(name) ?: return null

    val bodySource = source
        // reportError
        .replace("(e: any", "(error: JsError")
        // alert
        .replace("message?: any", "message: String")
        .replace("message?: ", "message: ")
        .replace("_default?: ", "default?: ")
        .replace("elt: Element", "element: Element")
        .replace("pseudoElt?: ", "pseudoElement?: ")

        .replace(": boolean", ": Boolean")
        .replace("?: string | null", "?: string")
        .replace("?: string", ": String = definedExternally")
        .replace(": string", ": String")
        .replace("): void", ")")
        .replace(" | null", "?")
        .replace(", ", ",\n")
        .replace("(", "(\n")
        .replace(")", "\n)")

    val body = "external fun $bodySource"

    return ConversionResult(
        name = name,
        body = body,
        pkg = pkg
    )
}
