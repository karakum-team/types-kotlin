package karakum.typescript

internal fun String.addOptionality(): String {
    if (startsWith("("))
        return "($this)?"

    if (startsWith("$ANY ") && endsWith(" */?"))
        return this

    if (this == ANY_N || startsWith("$ANY_N ") || startsWith("dynamic ") || this == "Nothing?")
        return this

    return when {
        endsWith(">") -> "$this?"
        " /*" in this -> replace(" /*", "? /*")
        endsWith("?") -> this
        else -> "$this?"
    }
}
