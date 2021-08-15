package com.github.turansky.react

internal fun String.kebabToCamel(): String =
    replace(Regex("""-(\w)""")) {
        it.groupValues[1].toUpperCase()
    }
