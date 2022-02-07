package karakum.node

internal fun String.addOptionality(): String {
    if (startsWith("("))
        return "($this)?"

    if (this == DYNAMIC || this.startsWith("$DYNAMIC ") || this == "Nothing?")
        return this

    if (endsWith("?"))
        return this

    return if (" /*" in this) {
        replace(" /*", "? /*")
    } else "$this?"
}
