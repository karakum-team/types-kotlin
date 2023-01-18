package karakum.browser

import karakum.common.unionBody

internal const val MODIFIER_KEY = "ModifierKey"

private val MODIFIER_KEYS = listOf(
    "Alt",
    "AltGraph",
    "CapsLock",
    "Control",
    "Fn",
    "FnLock",
    "Meta",
    "NumLock",
    "ScrollLock",
    "Shift",
    "Symbol",
    "SymbolLock",
)

internal fun keyboardTypes(): Sequence<ConversionResult> =
    sequenceOf(
        ConversionResult(
            name = MODIFIER_KEY,
            body = unionBody(MODIFIER_KEY, MODIFIER_KEYS),
            pkg = "web.keyboard",
        ),
    )
