package karakum.browser

import java.net.URL

internal const val DOM_EXCEPTION = "DOMException"

private val MDN_URL =
    URL("https://raw.githubusercontent.com/mdn/content/main/files/en-us/web/api/domexception/index.md")

internal fun domExceptionErrorNames(): String {
    val rawContent = MDN_URL.openStream()
        .use { stream -> String(stream.readAllBytes()) }
        .substringAfter("\n## Error names\n", "")
        .substringAfter("> **Note:** ", "")
        .substringAfter("\n\n", "")
        .substringBefore("\n\n", "")

    return rawContent
}
