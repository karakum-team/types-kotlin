package actions.cache

external fun <T> assertDefined(
    name: String,
    value: T = definedExternally,
): T
