package karakum.browser

internal val FINAL_LIST_LIKE = setOf(
    "CSSTransformValue",
    "CSSUnparsedValue",
)

internal val LIST_LIKE = setOf(
    "CSSNumericArray",
    "DOMTokenList",
    "NodeList",
) + FINAL_LIST_LIKE

internal val FINAL_MAP_LIKE = setOf(
    "FormData",
    "Headers",
    "URLSearchParams",
)

internal val MAP_LIKE = setOf(
    "MediaKeyStatusMap",
    "StylePropertyMapReadOnly",
) + FINAL_MAP_LIKE

internal class MapLikeParameters(
    val key: String,
    val value: String,
)

internal fun mapLikeParameters(
    source: String,
): MapLikeParameters? {
    if (!source.startsWith("["))
        return null

    val (key, value) = source
        .removeSurrounding("[", "]")
        .replace("string", "String")
        .replace(", Iterable<", ", JsIterable<")
        .split(", ")

    return MapLikeParameters(
        key = key,
        value = value,
    )
}

internal fun listLikeOverrides(
    itemType: String,
): String = """
override fun entries(): IterableIterator<JsTuple2<Int, $itemType>>
override fun keys(): IterableIterator<Int>
override fun values(): IterableIterator<$itemType>
override fun forEach(action: (item: $itemType) -> Unit)
""".trimIndent()

internal fun mapLikeOverrides(
    keyType: String,
    valueType: String,
): String = """
override fun entries(): IterableIterator<JsTuple2<$keyType, $valueType>>
override fun keys(): IterableIterator<$keyType>
override fun values(): IterableIterator<$valueType>
override fun forEach(action: (value: $valueType, key: $keyType) -> Unit)
""".trimIndent()
