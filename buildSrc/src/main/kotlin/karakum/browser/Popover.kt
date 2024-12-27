package karakum.browser

import karakum.common.unionBody

internal const val POPOVER = "Popover"
internal const val POPOVER_TARGET_ACTION = "PopoverTargetAction"

internal fun popoverTypes(): Sequence<ConversionResult> =
    sequenceOf(
        ConversionResult(
            POPOVER,
            unionBody(
                name = POPOVER,
                values = listOf("auto", "manual"),
            ),
            pkg = "web.popover",
        ),
        ConversionResult(
            POPOVER_TARGET_ACTION,
            unionBody(
                name = POPOVER_TARGET_ACTION,
                values = listOf("hide", "show", "toggle"),
            ),
            pkg = "web.popover",
        ),
    )
