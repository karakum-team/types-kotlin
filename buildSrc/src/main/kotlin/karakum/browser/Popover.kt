package karakum.browser

import karakum.common.unionBody

internal const val POPOVER = "Popover"

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
    )
