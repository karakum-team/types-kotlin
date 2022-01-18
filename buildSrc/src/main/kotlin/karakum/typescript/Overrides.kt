package karakum.typescript

internal fun fixOverrides(
    name: String,
    content: String,
): String =
    when (name) {

        else ->
            content
                .override("kind")
                .override("name")
                .override("parent")
    }

private fun String.override(
    name: String,
): String =
    replaceFirst("var $name:", "override var $name:")
        .replaceFirst("val $name:", "override val $name:")
