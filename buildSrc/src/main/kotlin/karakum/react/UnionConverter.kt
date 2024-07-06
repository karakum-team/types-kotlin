package karakum.react

import karakum.common.removeQuoteSurrounding
import karakum.common.sealedUnionBody
import kotlin.text.Typography.quote

internal fun convertUnion(
    name: String,
    source: String,
): ConversionResult? {
    if ("<" in name)
        return null

    val values = if (source.startsWith(" \"")) {
        source.trim()
            .splitToSequence(" | ")
            .map { it.removeQuoteSurrounding() }
            .toList()
    } else if (" | '" in source || " | $quote" in source) {
        source.removePrefix("\n")
            .trimIndent()
            .splitToSequence("\n")
            .map { it.removePrefix("| ") }
            .filter { it != "(string & {})" }
            .map { it.removeQuoteSurrounding() }
            .toList()
    } else {
        return null
    }

    return convertUnion(name, values)
}

internal fun convertUnion(
    name: String,
    values: List<String>,
): ConversionResult =
    ConversionResult(
        name = name,
        body = sealedUnionBody(name, values),
    )

internal fun String.withNormalizedUnions(): String {
    if (":\n    | " !in this) {
        return this
    }

    val unionMembers = this
        .substringAfter(":\n")
        .splitToSequence(" | ")
        .drop(1)
        .map { it.trim() }
        .joinToString(" | ")

    return this.substringBefore(":\n") + ": " + unionMembers
}
