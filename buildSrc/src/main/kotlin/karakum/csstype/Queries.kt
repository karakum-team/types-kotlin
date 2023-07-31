package karakum.csstype

import karakum.common.ConversionResult

internal const val MEDIA_QUERY = "MediaQuery"
internal const val SIZE_QUERY = "SizeQuery"

internal fun MediaQuery(): ConversionResult =
    ConversionResult(
        name = MEDIA_QUERY,
        body = queryBody(MEDIA_QUERY),
    )

internal fun SizeQuery(): ConversionResult =
    ConversionResult(
        name = SIZE_QUERY,
        body = queryBody(SIZE_QUERY),
    )

private fun queryBody(
    name: String,
): String = """
external interface $name

inline fun $name(
    value: String,
): $name =
    value.unsafeCast<$name>()
    
infix fun $name.and(
    other: $name,
): $name =
    $name("${'$'}this and ${'$'}other")

infix fun $name.or(
    other: $name,
): $name =
    $name("${'$'}this or ${'$'}other")

inline fun not(
    query: $name,
): $name =
    $name("(not (${'$'}query))")
""".trimIndent()
