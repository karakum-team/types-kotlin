package karakum.browser

internal const val KEY = "Key"
internal const val KEY_CODE = "KeyCode"

private val KEY_CODES = listOf(
    // Whitespace Keys
    "Enter",
    "Tab",
    "Space",

    // Navigation Keys
    "ArrowDown",
    "ArrowLeft",
    "ArrowRight",
    "ArrowUp",
    "End",
    "Home",
    "PageDown",
    "PageUp",

    // Editing Keys
    "Backspace",
    "Clear",
    "Copy",
    "CrSel",
    "Cut",
    "Delete",
    "EraseEof",
    "ExSel",
    "Insert",
    "Paste",
    "Redo",
    "Undo",

    // UI Keys
    "Accept",
    "Again",
    "Attn",
    "Cancel",
    "ContextMenu",
    "Escape",
    "Execute",
    "Find",
    "Help",
    "Pause",
    "Play",
    "Props",
    "Select",
    "ZoomIn",
    "ZoomOut",

    // Device Keys
    "BrightnessDown",
    "BrightnessUp",
    "Eject",
    "LogOff",
    "Power",
    "PowerOff",
    "PrintScreen",
    "Hibernate",
    "Standby",
    "WakeUp",

    // IME and Composition Keys
    "AllCandidates",
    "Alphanumeric",
    "CodeInput",
    "Compose",
    "Convert",
    "Dead",
    "FinalMode",
    "GroupFirst",
    "GroupLast",
    "GroupNext",
    "GroupPrevious",
    "ModeChange",
    "NextCandidate",
    "NonConvert",
    "PreviousCandidate",
    "Process",
    "SingleCandidate",
    "HangulMode",
    "HanjaMode",
    "JunjaMode",
    "Eisu",
    "Hankaku",
    "Hiragana",
    "HiraganaKatakana",
    "KanaMode",
    "KanjiMode",
    "Katakana",
    "Romaji",
    "Zenkaku",
    "ZenkakuHankaku",

    // General-Purpose Function Keys
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
    "Soft1",
    "Soft2",
    "Soft3",
    "Soft4",

    // Multimedia Keys
    "ChannelDown",
    "ChannelUp",
    "Close",
    "MailForward",
    "MailReply",
    "MailSend",
    "MediaClose",
    "MediaFastForward",
    "MediaPause",
    "MediaPlay",
    "MediaPlayPause",
    "MediaRecord",
    "MediaRewind",
    "MediaStop",
    "MediaTrackNext",
    "MediaTrackPrevious",
    "New",
    "Open",
    "Print",
    "Save",
    "SpellCheck",

    // Audio Keys
    "AudioBalanceLeft",
    "AudioBalanceRight",
    "AudioBassBoostDown",
    "AudioBassBoostToggle",
    "AudioBassBoostUp",
    "AudioFaderFront",
    "AudioFaderRear",
    "AudioSurroundModeNext",
    "AudioTrebleDown",
    "AudioTrebleUp",
    "AudioVolumeDown",
    "AudioVolumeUp",
    "AudioVolumeMute",
    "MicrophoneToggle",
    "MicrophoneVolumeDown",
    "MicrophoneVolumeUp",
    "MicrophoneVolumeMute",

    // Speech Keys
    "SpeechCorrectionList",
    "SpeechInputToggle",

    // Application Keys
    "LaunchApplication1",
    "LaunchApplication2",
    "LaunchCalendar",
    "LaunchContacts",
    "LaunchMail",
    "LaunchMediaPlayer",
    "LaunchMusicPlayer",
    "LaunchPhone",
    "LaunchScreenSaver",
    "LaunchSpreadsheet",
    "LaunchWebBrowser",
    "LaunchWebCam",
    "LaunchWordProcessor",

    // Browser Keys
    "BrowserBack",
    "BrowserFavorites",
    "BrowserForward",
    "BrowserHome",
    "BrowserRefresh",
    "BrowserSearch",
    "BrowserStop",

    // Mobile Phone Keys
    "AppSwitch",
    "Call",
    "Camera",
    "CameraFocus",
    "EndCall",
    "GoBack",
    "GoHome",
    "HeadsetHook",
    "LastNumberRedial",
    "Notification",
    "MannerMode",
    "VoiceDial",

    // TV Keys
    "TV",
    "TV3DMode",
    "TVAntennaCable",
    "TVAudioDescription",
    "TVAudioDescriptionMixDown",
    "TVAudioDescriptionMixUp",
    "TVContentsMenu",
    "TVDataService",
    "TVInput",
    "TVInputComponent1",
    "TVInputComponent2",
    "TVInputComposite1",
    "TVInputComposite2",
    "TVInputHDMI1",
    "TVInputHDMI2",
    "TVInputHDMI3",
    "TVInputHDMI4",
    "TVInputVGA1",
    "TVMediaContext",
    "TVNetwork",
    "TVNumberEntry",
    "TVPower",
    "TVRadioService",
    "TVSatellite",
    "TVSatelliteBS",
    "TVSatelliteCS",
    "TVSatelliteToggle",
    "TVTerrestrialAnalog",
    "TVTerrestrialDigital",
    "TVTimer",

    // Media Controller Keys
    "AVRInput",
    "AVRPower",
    "ColorF0Red",
    "ColorF1Green",
    "ColorF2Yellow",
    "ColorF3Blue",
    "ColorF4Grey",
    "ColorF5Brown",
    "ClosedCaptionToggle",
    "Dimmer",
    "DisplaySwap",
    "DVR",
    "Exit",
    "FavoriteClear0",
    "FavoriteClear1",
    "FavoriteClear2",
    "FavoriteClear3",
    "FavoriteRecall0",
    "FavoriteRecall1",
    "FavoriteRecall2",
    "FavoriteRecall3",
    "FavoriteStore0",
    "FavoriteStore1",
    "FavoriteStore2",
    "FavoriteStore3",
    "Guide",
    "GuideNextDay",
    "GuidePreviousDay",
    "Info",
    "InstantReplay",
    "Link",
    "ListProgram",
    "LiveContent",
    "Lock",
    "MediaApps",
    "MediaAudioTrack",
    "MediaLast",
    "MediaSkipBackward",
    "MediaSkipForward",
    "MediaStepBackward",
    "MediaStepForward",
    "MediaTopMenu",
    "NavigateIn",
    "NavigateNext",
    "NavigateOut",
    "NavigatePrevious",
    "NextFavoriteChannel",
    "NextUserProfile",
    "OnDemand",
    "Pairing",
    "PinPDown",
    "PinPMove",
    "PinPToggle",
    "PinPUp",
    "PlaySpeedDown",
    "PlaySpeedReset",
    "PlaySpeedUp",
    "RandomToggle",
    "RcLowBattery",
    "RecordSpeedNext",
    "RfBypass",
    "ScanChannelsToggle",
    "ScreenModeNext",
    "Settings",
    "SplitScreenToggle",
    "STBInput",
    "STBPower",
    "Subtitle",
    "Teletext",
    "VideoModeNext",
    "Wink",
    "ZoomToggle",

    // Special Keys
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
    /*
    val keyCodes = (('A'..'F') + ('0'..'9'))
        .map { it.toString() }
        .plus(KEY_CODES)
    */

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
    val items = values.joinToString("\n\n") {code ->
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
