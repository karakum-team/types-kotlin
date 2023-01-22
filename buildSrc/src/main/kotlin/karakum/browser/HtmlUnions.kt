package karakum.browser

import karakum.common.unionBody

internal fun htmlUnions(): Sequence<ConversionResult> =
    sequenceOf(
        ConversionResult(
            name = "EnterKeyHint",
            body = unionBody(
                name = "EnterKeyHint",
                values = listOf(
                    "enter",
                    "done",
                    "go",
                    "next",
                    "previous",
                    "search",
                    "send",
                )
            ),
            pkg = "web.html",
        ),
        ConversionResult(
            name = "ImageDecoding",
            body = unionBody(
                name = "ImageDecoding",
                values = listOf(
                    "async",
                    "sync",
                    "auto",
                )
            ),
            pkg = "web.html",
        ),
        ConversionResult(
            name = "InputMode",
            body = unionBody(
                name = "InputMode",
                values = listOf(
                    "none",
                    "text",
                    "tel",
                    "url",
                    "email",
                    "numeric",
                    "decimal",
                    "search",
                )
            ),
            pkg = "web.html",
        ),
        ConversionResult(
            name = "InputType",
            body = unionBody(
                name = "InputType",
                values = listOf(
                    "button",
                    "checkbox",
                    "color",
                    "date",
                    "datetime-local",
                    "email",
                    "file",
                    "hidden",
                    "image",
                    "month",
                    "number",
                    "password",
                    "radio",
                    "range",
                    "reset",
                    "search",
                    "submit",
                    "tel",
                    "text",
                    "time",
                    "url",
                    "week",
                )
            ),
            pkg = "web.html",
        ),
        ConversionResult(
            name = "Loading",
            body = unionBody(
                name = "Loading",
                values = listOf(
                    "eager",
                    "lazy",
                )
            ),
            pkg = "web.html",
        )
    )
