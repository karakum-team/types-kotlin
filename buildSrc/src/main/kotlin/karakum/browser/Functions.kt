package karakum.browser

internal fun browserFunctions(
    content: String,
): Sequence<ConversionResult> =
    Regex("""\ndeclare function (.+);""")
        .findAll(content)
        .map { it.groupValues[1] }
        .mapNotNull { convertFunctionResult(it) }

private fun convertFunctionResult(
    source: String,
): ConversionResult? {
    val name = source.substringBefore("(")

    val pkg = when (name) {
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

        "alert",
        "confirm",
        "prompt",
        -> "web.prompts"

        else -> return null
    }

    val bodySource = source
        // alert
        .replace("message?: any", "message: String")
        .replace("message?: ", "message: ")
        .replace("elt: Element", "element: Element")

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
