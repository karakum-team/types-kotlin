package com.github.turansky.router

internal fun convertConst(
    name: String,
    source: String,
): String {
    if (!name.endsWith("Context"))
        return source

    return source
        .replace("const ", "external val ")
        .replace("React.", "react.")
}
