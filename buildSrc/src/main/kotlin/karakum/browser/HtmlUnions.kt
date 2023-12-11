package karakum.browser

import karakum.common.sealedUnionBody

internal class UnionData(
    val name: String,
    val values: List<String>,
    val pkg: String,
)

internal val UNION_DATA_LIST = listOf(
    UnionData(
        name = "ButtonType",
        values = listOf(
            "submit",
            "reset",
            "button",
        ),
        pkg = "web.html",
    ),
    /*
    UnionData(
        name = "Preload",
        values = listOf(
            "none",
            "metadata",
            "auto",
            "",
        ),
        pkg = "web.html",
    ),
    */
    UnionData(
        name = "SelectionDirection",
        values = listOf(
            "forward",
            "backward",
            "none",
        ),
        pkg = "web.html",
    ),
    UnionData(
        name = "DropEffect",
        values = listOf(
            "none",
            "copy",
            "link",
            "move",
        ),
        pkg = "web.data",
    ),
    UnionData(
        name = "AllowedEffect",
        values = listOf(
            "none",
            "copy",
            "copyLink",
            "copyMove",
            "link",
            "linkMove",
            "move",
            "all",
            "uninitialized",
        ),
        pkg = "web.data",
    ),
)

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
        )
    ).plus(
        UNION_DATA_LIST.map { data ->
            ConversionResult(
                name = data.name,
                body = sealedUnionBody(
                    name = data.name,
                    values = data.values,
                ),
                pkg = data.pkg,
            )
        }
    )
