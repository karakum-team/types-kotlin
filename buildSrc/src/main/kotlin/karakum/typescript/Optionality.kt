package karakum.typescript

internal fun String.addOptionality(): String {
    if (startsWith("("))
        return "($this)?"

    if (this == DYNAMIC || this.startsWith("$DYNAMIC ") || this == "Nothing?")
        return this

    return when {
        " /*" in this -> replace(" /*", "? /*")
        endsWith("?") -> this
        else -> "$this?"
    }
}
