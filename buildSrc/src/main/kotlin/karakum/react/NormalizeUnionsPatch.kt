package karakum.react

private fun String.normalizeUnions(): String =
    replace(Regex("\n *\\|"), " |")

internal fun String.applyNormalizeUnionsPatch(): String = this
    .normalizeUnions()
    .replace(": | \"", ": \"")
