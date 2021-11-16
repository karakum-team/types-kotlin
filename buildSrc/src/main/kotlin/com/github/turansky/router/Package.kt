package com.github.turansky.router

internal enum class Package(
    id: String,
    val moduleName: String,
) {
    HISTORY("history", "history"),
    ROUTER("react.router", "react-router"),
    ROUTER_DOM("react.router.dom", "react-router-dom"),

    ;

    val pkg = "package $id"
    val path = id.replace(".", "/")
}
