package com.github.turansky.router

internal enum class Package(
    id: String,
) {
    HISTORY("history"),
    ROUTER("react.router"),
    ROUTER_DOM("react.router.dom"),

    ;

    val pkg = "package $id"
    val path = id.replace(".", "/")
}
