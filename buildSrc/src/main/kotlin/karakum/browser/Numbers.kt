package karakum.browser

private val INT_NAMES = setOf(
    "cellIndex",
    "colSpan",
    "cols",
    "index",
    "length",
    "maxLength",
    "minLength",
    "naturalHeight",
    "naturalWidth",
    "rowIndex",
    "rowSpan",
    "rows",
    "sectionRowIndex",
    "selectedIndex",
    "selectionEnd",
    "selectionStart",
    "size",
    "span",
    "start",
    "textLength",
)

private val DOUBLE_NAMES = setOf(
    "height",
    "high",
    "low",
    "max",
    "min",
    "optimum",
    "position",
    "value",
    "valueAsNumber",
    "width",
    "x",
    "y",
)

fun numberType(
    propertyName: String,
): String =
    when {
        propertyName.endsWith("Frames") -> "Int"

        propertyName in INT_NAMES -> "Int"
        propertyName in DOUBLE_NAMES -> "Double"
        else -> {
            // TODO("No numberability configuration for property '$propertyName'")
            "Number"
        }
    }
