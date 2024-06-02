package karakum.typescript

internal fun String.addOptionality(): String {
    if (startsWith("("))
        return "($this)?"

    if (this == ANY_N || this.startsWith("$ANY_N ") || this == "Nothing?")
        return this

    return when {
        endsWith(">") -> "$this?"
        " /*" in this -> replace(" /*", "? /*")
        endsWith("?") -> this
        else -> "$this?"
    }
}
