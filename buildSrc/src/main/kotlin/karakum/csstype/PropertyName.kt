package karakum.csstype

import karakum.common.unionBody

private const val PROPERTY_NAME = "PropertyName"

internal fun PropertyName(
    contentMap: Map<String, String>,
): ConversionResult {
    val values = sequenceOf(
        "StandardLonghandPropertiesHyphen",
        "StandardShorthandPropertiesHyphen",
    ).map(contentMap::getValue)
        .flatMap { propertyNames(it) }
        .sorted()
        .toList()


    return ConversionResult(PROPERTY_NAME, unionBody(PROPERTY_NAME, values))
}

private fun propertyNames(
    content: String,
): Sequence<String> =
    content.substringAfter("{\n")
        .substringBefore("\n}")
        .trimIndent()
        .lineSequence()
        .filter { "?: " in it }
        .map { it.substringBefore("?: ") }
        .map { it.removeSurrounding("\"") }
