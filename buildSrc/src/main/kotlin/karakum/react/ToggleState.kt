package karakum.react

internal const val TOGGLE_STATE = "ToggleState"

internal fun ToggleState(): ConversionResult =
    ConversionResult(
        name = TOGGLE_STATE,
        body = "typealias ToggleState = String /* 'open' | 'closed' */",
        pkg = Package.EVENTS,
    )
