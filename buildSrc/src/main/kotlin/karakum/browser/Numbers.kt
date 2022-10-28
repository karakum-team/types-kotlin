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

    "videoHeight",
    "videoWidth",
)

private val DOUBLE_NAMES = setOf(
    "height",
    "high",
    "low",
    "max",
    "min",
    "optimum",
    "position",
    "quality",
    "value",
    "valueAsNumber",
    "width",
    "x",
    "y",
)

internal class TypeProvider(
    private val parentType: String,
) {
    fun numberType(
        propertyName: String,
    ): String {
        // flags
        if (propertyName == propertyName.toUpperCase())
            return "Short"

        if (parentType == "HTMLCanvasElement") {
            when (propertyName) {
                "width",
                "height",
                -> return "Int"
            }
        }

        return when {
            propertyName.endsWith("Frames") -> "Int"

            propertyName in INT_NAMES -> "Int"
            propertyName in DOUBLE_NAMES -> "Double"
            else -> {
                println("$parentType.$propertyName")

                // TODO("No numberability configuration for property '$propertyName'")
                "Number"
            }
        }
    }
}
