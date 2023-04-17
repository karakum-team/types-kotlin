package karakum.csstype

import karakum.common.ConversionResult
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


    val body = unionBody(PROPERTY_NAME, values)
        .replaceFirst(PROPERTY_NAME, "$PROPERTY_NAME : $IDENT")

    return ConversionResult(PROPERTY_NAME, body)
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
