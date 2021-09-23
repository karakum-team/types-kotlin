package com.github.turansky.csstype

internal const val GRID_LENGTH_PROPERTY = "GridLengthProperty"

internal val GRID_LENGTH_PROPERTY_PARENTS = listOf(
    "GridAutoColumns",
    "GridAutoRows",
    "GridTemplateColumns",
    "GridTemplateRows",
)

internal fun GridLengthProperty(): ConversionResult {
    val body = """
        sealed external interface $GRID_LENGTH_PROPERTY:
        ${GRID_LENGTH_PROPERTY_PARENTS.joinToString(",\n")}
    """.trimIndent()

    return ConversionResult(GRID_LENGTH_PROPERTY, body)
}
