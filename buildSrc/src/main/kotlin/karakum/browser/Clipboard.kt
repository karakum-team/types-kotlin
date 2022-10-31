package karakum.browser

// language=kotlin
private val CLIPBOARD_ITEM_BODY = """
external class ClipboardItem(
    items: Record<String, Any /* String | Blob | PromiseLike<String | Blob> */>,
    options: ClipboardItemOptions = definedExternally,
) {
    val types: ReadonlyArray<String>
    fun getType(type: String): Promise<Blob>
}
""".trimIndent()

internal fun clipboardDeclarations(): Sequence<ConversionResult> =
    sequenceOf(
        ConversionResult(
            name = "ClipboardItem",
            body = CLIPBOARD_ITEM_BODY,
            pkg = "web.clipboard",
        ),
        ConversionResult(
            name = "ClipboardItems",
            body = "typealias ClipboardItems = ReadonlyArray<ClipboardItem>",
            pkg = "web.clipboard",
        )
    )
