package com.github.turansky.csstype

internal const val GRID_LENGTH_PROPERTY = "GridLengthProperty"

private val PARENTS = listOf(
    "GridAutoColumns",
    "GridAutoRows",
    "GridTemplateColumns",
    "GridTemplateRows",
)

internal fun GridLengthProperty(): ConversionResult {
    val body = """
        sealed external interface $GRID_LENGTH_PROPERTY:
        ${PARENTS.joinToString(",\n")}
    """.trimIndent()

    return ConversionResult(GRID_LENGTH_PROPERTY, body)
}
