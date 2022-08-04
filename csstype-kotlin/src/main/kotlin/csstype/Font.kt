// Automatically generated - do not modify!

@file:Suppress(
    "NAME_CONTAINS_ILLEGAL_CHARS",
    "NESTED_CLASS_IN_EXTERNAL_INTERFACE",
    "NOTHING_TO_INLINE",
)

package csstype

// language=JavaScript
@JsName("""(/*union*/{caption: 'caption', icon: 'icon', menu: 'menu', messageBox: 'message-box', smallCaption: 'small-caption', statusBar: 'status-bar'}/*union*/)""")
sealed external interface Font {
    companion object {
        val caption: Font
        val icon: Font
        val menu: Font
        val messageBox: Font
        val smallCaption: Font
        val statusBar: Font
    }
}

inline fun Font(
    style: FontStyle,
    variant: FontVariant,
    weight: FontWeight,
    stretch: FontStretch,
    size: FontSize,
    lineHeight: LineHeight,
    family: FontFamily,
): Font =
    "$style $variant $weight $stretch $size/$lineHeight $family".unsafeCast<Font>()
