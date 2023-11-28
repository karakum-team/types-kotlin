package karakum.browser

import java.net.URL

internal const val DOM_EXCEPTION = "DOMException"

private val MDN_URL =
    URL("https://raw.githubusercontent.com/mdn/content/main/files/en-us/web/api/domexception/index.md")

internal fun domExceptionErrorNames(): String =
    MDN_URL.openStream()
        .use { stream -> String(stream.readAllBytes()) }
        .substringAfter("\n## Error names\n", "")
        .substringAfter("> **Note:** ", "")
        .substringAfter("\n\n", "")
        .substringBefore("\n\n", "")
        .let { "\n$it" }
        .splitToSequence("\n- ")
        .drop(1)
        .map { it.split("\n  - : ") }
        .mapNotNull { (name, description) -> parseErrorName(name, description) }
        .joinToString("\n\n") { (name, description) ->
            """
            /**
              * $description
              */
            @JsValue("$name")
            val $name: JsErrorName
            """.trimIndent()
        }

private fun parseErrorName(
    nameSource: String,
    descriptionSource: String,
): Pair<String, String>? {
    if ("{{deprecated_inline}}" in nameSource)
        return null

    val name = nameSource.substringBefore(" {{")
    val description = descriptionSource

    return name to description
}
