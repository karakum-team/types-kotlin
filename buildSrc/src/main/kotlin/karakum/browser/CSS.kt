package karakum.browser

internal fun cssObject(
    content: String,
): ConversionResult {
    val members = content
        .substringAfter("\ndeclare namespace CSS {\n")
        .substringBefore(";\n}")
        .replace(";\n", "\n")
        .trimIndent()
        .replace("function ", "fun ")
        .replace(": number", ": Number")
        .replace(": string", ": String")
        .replace(": boolean", ": Boolean")
        .replace("): void", ")")

    val body = "external object CSS {\n" +
            members +
            "\n}"

    return ConversionResult(
        name = "CSS",
        body = body,
        pkg = "web.cssom"
    )
}
