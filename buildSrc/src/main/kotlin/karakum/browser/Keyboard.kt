package karakum.browser

import karakum.common.unionBody

internal const val MODIFIER_KEY_CODE = "ModifierKeyCode"

private val MODIFIER_KEY_CODES = listOf(
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
            name = MODIFIER_KEY_CODE,
            body = unionBody(MODIFIER_KEY_CODE, MODIFIER_KEY_CODES),
            pkg = "web.keyboard",
        ),
    )
