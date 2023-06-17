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

internal val MAP_LIKE = setOf(
    // "FormData",
    // "Headers",
    "MediaKeyStatusMap",
    "StylePropertyMapReadOnly",
    // "URLSearchParams",
)

internal fun listLikeOverrides(
    itemType: String,
): String = """
override fun entries(): JsIterable.Iterator<JsTuple2<Int, $itemType>>
override fun keys(): JsIterable.Iterator<Int>
override fun values(): JsIterable.Iterator<$itemType>
override fun forEach(action: (item: $itemType) -> Unit)
""".trimIndent()
