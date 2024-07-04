package karakum.common

private val MINUS_LETTER = Regex("""-(\w)""")
private val PLUS_LETTER = Regex("""\+(\w)""")
private val SLASH_LETTER = Regex("""\/(\w)""")
private val UNDERSCORE_LETTER = Regex("""_(\w)""")
private val SPACE_LETTER = Regex("""\s(\w)""")

private val SINGLE_QUOTE_LETTER = "\'"
private val DOUBLE_QUOTE_LETTER = "\""

private val toUpperCase: (MatchResult) -> CharSequence = {
    it.groupValues[1].uppercase()
}

internal fun String.kebabToCamel(): String =
    replace(MINUS_LETTER, toUpperCase)
        .replace(PLUS_LETTER, toUpperCase)
        .replace(SLASH_LETTER, toUpperCase)
        .replace(SPACE_LETTER, toUpperCase)

internal fun String.snakeToCamel(): String =
    replace(UNDERSCORE_LETTER, toUpperCase)
        .replace(SPACE_LETTER, toUpperCase)

internal fun String.replaceSuffix(
    from: String,
    to: String,
): String {
    require(endsWith(from))

    return removeSuffix(from) + to
}

internal fun String.removeQuoteSurrounding(): String = removeSurrounding(SINGLE_QUOTE_LETTER)
    .removeSurrounding(DOUBLE_QUOTE_LETTER)

internal fun Sequence<String>.removeQuoteSurrounding(): Sequence<String> =
    map { it.removeQuoteSurrounding() }
