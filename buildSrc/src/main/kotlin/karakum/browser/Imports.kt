package karakum.browser

internal class Imports(
    vararg items: String,
) {
    private val map: Map<String, String> = items.associateWith { it.substringAfterLast(".") }

    fun forContent(
        body: String,
    ): String =
        map.asSequence()
            .mapNotNull { (fqn, name) ->
                if (name in body) fqn else null
            }.joinToString("\n") { "import $it" }
}
