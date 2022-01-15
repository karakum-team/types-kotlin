package karakum.popper

private val EXCLUDED_NAMES = setOf(
    "StrictModifiers",
)

internal fun convertType(
    declaration: String,
    source: String,
): ConversionResult? {
    val name = declaration.substringBefore("<")
    if (name in EXCLUDED_NAMES)
        return null

    val content = when (source) {
        "S | ((prev: S) => S)",
        -> "(prev: S) -> S // $source"

        "number | Partial<SideObject>",
        -> "SideObject // $source"

        "(arg0: State) => void",
        -> "(State) -> Unit"

        else -> source
    }
    val body = "typealias $declaration = $content"

    return ConversionResult(name, body)
}
