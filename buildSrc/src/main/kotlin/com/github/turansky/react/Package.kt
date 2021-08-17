package com.github.turansky.react

internal enum class Package {
    DOM,
    SVG,

    ;

    private val id = name.toLowerCase()
    val pkg = "package react.$id"
    val path = "react/$id"
}
