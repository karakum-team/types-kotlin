package com.github.turansky.react

internal enum class Package {
    HTML,
    SVG,

    ;

    private val id = name.toLowerCase()
    val pkg = "package react.dom.$id"
    val path = "react/dom/$id"
}
