package karakum.browser

import karakum.common.sealedUnionBody

internal fun htmlUnions(): Sequence<ConversionResult> =
    sequenceOf(
        ConversionResult(
            name = "CrossOrigin",
            body = sealedUnionBody(
                name = "CrossOrigin",
                values = listOf(
                    "anonymous",
                    "use-credentials",
                    "",
                )
            ),
            pkg = "web.http",
        ),
        ConversionResult(
            name = "EnterKeyHint",
            body = sealedUnionBody(
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
            body = sealedUnionBody(
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
            body = sealedUnionBody(
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
            name = "ButtonType",
            body = sealedUnionBody(
                name = "ButtonType",
                values = listOf(
                    "submit",
                    "reset",
                    "button",
                )
            ),
            pkg = "web.html",
        ),
        ConversionResult(
            name = "InputType",
            body = sealedUnionBody(
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
            body = sealedUnionBody(
                name = "Loading",
                values = listOf(
                    "eager",
                    "lazy",
                )
            ),
            pkg = "web.html",
        ),
        ConversionResult(
            name = "SelectionDirection",
            body = sealedUnionBody(
                name = "SelectionDirection",
                values = listOf(
                    "forward",
                    "backward",
                    "none",
                )
            ),
            pkg = "web.html",
        ),
    )
