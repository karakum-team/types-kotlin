package karakum.browser

internal const val KEY = "Key"
internal const val KEY_CODE = "KeyCode"

private val KEY_CODES = listOf(
    // Alphanumeric Section
    "Backquote",
    "Backslash",
    "Backspace",
    "BracketLeft",
    "BracketRight",
    "Comma",
    "Digit0",
    "Digit1",
    "Digit2",
    "Digit3",
    "Digit4",
    "Digit5",
    "Digit6",
    "Digit7",
    "Digit8",
    "Digit9",
    "Equal",
    "IntlBackslash",
    "IntlRo",
    "IntlYen",
    "KeyA",
    "KeyB",
    "KeyC",
    "KeyD",
    "KeyE",
    "KeyF",
    "KeyG",
    "KeyH",
    "KeyI",
    "KeyJ",
    "KeyK",
    "KeyL",
    "KeyM",
    "KeyN",
    "KeyO",
    "KeyP",
    "KeyQ",
    "KeyR",
    "KeyS",
    "KeyT",
    "KeyU",
    "KeyV",
    "KeyW",
    "KeyX",
    "KeyY",
    "KeyZ",
    "Minus",
    "Period",
    "Quote",
    "Semicolon",
    "Slash",

    // Functional Keys
    "AltLeft",
    "AltRight",
    "CapsLock",
    "ContextMenu",
    "ControlLeft",
    "ControlRight",
    "Enter",
    "MetaLeft",
    "MetaRight",
    "ShiftLeft",
    "ShiftRight",
    "Space",
    "Tab",

    "Convert",
    "KanaMode",
    "Lang1",
    "Lang2",
    "Lang3",
    "Lang4",
    "Lang5",
    "NonConvert",

    // Firefox on Mac
    "OSLeft",
    "OSRight",

    // Control Pad Section
    "Delete",
    "End",
    "Help",
    "Home",
    "Insert",
    "PageDown",
    "PageUp",

    // Arrow Pad Section
    "ArrowDown",
    "ArrowLeft",
    "ArrowRight",
    "ArrowUp",

    // Numpad Section
    "NumLock",
    "Numpad0",
    "Numpad1",
    "Numpad2",
    "Numpad3",
    "Numpad4",
    "Numpad5",
    "Numpad6",
    "Numpad7",
    "Numpad8",
    "Numpad9",
    "NumpadAdd",
    "NumpadBackspace",
    "NumpadClear",
    "NumpadClearEntry",
    "NumpadComma",
    "NumpadDecimal",
    "NumpadDivide",
    "NumpadEnter",
    "NumpadEqual",
    "NumpadHash",
    "NumpadMemoryAdd",
    "NumpadMemoryClear",
    "NumpadMemoryRecall",
    "NumpadMemoryStore",
    "NumpadMemorySubtract",
    "NumpadMultiply",
    "NumpadParenLeft",
    "NumpadParenRight",
    "NumpadStar",
    "NumpadSubtract",

    // Function Section
    "Escape",
    "F1",
    "F2",
    "F3",
    "F4",
    "F5",
    "F6",
    "F7",
    "F8",
    "F9",
    "F10",
    "F11",
    "F12",
    "F13",
    "F14",
    "F15",
    "Fn",
    "FnLock",
    "PrintScreen",
    "ScrollLock",
    "Pause",

    // Media Keys
    "BrowserBack",
    "BrowserFavorites",
    "BrowserForward",
    "BrowserHome",
    "BrowserRefresh",
    "BrowserSearch",
    "BrowserStop",
    "Eject",
    "LaunchApp1",
    "LaunchApp2",
    "LaunchMail",
    "MediaPlayPause",
    "MediaSelect",
    "MediaStop",
    "MediaTrackNext",
    "MediaTrackPrevious",
    "Power",
    "Sleep",
    "AudioVolumeDown",
    "AudioVolumeMute",
    "AudioVolumeUp",
    "WakeUp",

    // International
    "Hiragana",
    "Katakana",

    // Special
    "Unidentified",
)

internal const val MODIFIER_KEY = "ModifierKey"
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

internal fun keyboardTypes(): Sequence<ConversionResult> {
    return sequenceOf(
        keyboardTypes(
            type = KEY,
            codeType = KEY_CODE,
            values = KEY_CODES,
        ),
        keyboardTypes(
            type = MODIFIER_KEY,
            codeType = MODIFIER_KEY_CODE,
            values = MODIFIER_KEY_CODES,
        )
    ).flatMap { it }
}

private fun keyboardTypes(
    type: String,
    codeType: String,
    values: List<String>,
): Sequence<ConversionResult> {
    val items = values.joinToString("\n\n") { name ->
        val code = if (name.length == 1) "Key$name" else name

        """
        inline val $code: $codeType
            get() = $codeType("$code")
        """.trimIndent()
    }

    val typeBody = """
    object $type {
        $items
    }
    """.trimIndent()

    val codeTypeBody = """
    sealed external interface $codeType
            
    inline fun $codeType(
        code: String,
    ): $codeType =
        code.unsafeCast<$codeType>()            
    """.trimIndent()

    return sequenceOf(
        ConversionResult(
            name = type,
            body = typeBody,
            pkg = "web.keyboard",
        ),
        ConversionResult(
            name = codeType,
            body = codeTypeBody,
            pkg = "web.keyboard",
        ),
    )
}
