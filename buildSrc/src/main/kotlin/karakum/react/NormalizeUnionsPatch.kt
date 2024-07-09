package karakum.react

private fun String.normalizeUnions(n: Int): String =
    replace("\n${" ".repeat(n)}|", " |")

internal fun String.applyNormalizeUnionsPatch(): String =
    normalizeUnions(8)
