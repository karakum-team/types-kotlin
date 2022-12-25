package karakum.node

internal fun String.addOptionality(): String {
    if (startsWith("("))
        return "($this)?"

    if (this == DYNAMIC || this.startsWith("$DYNAMIC ") || this == "Void")
        return this

    if (endsWith("?") || "? /*" in this)
        return this

    return if (" /*" in this && !endsWith(">")) {
        replace(" /*", "? /*")
    } else "$this?"
}
