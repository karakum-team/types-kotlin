package com.github.turansky.popper

internal enum class Package(
    id: String,
) {
    CORE("popper.core"),
    MODIFIERS("popper.core.modifiers"),

    ;

    val pkg = "package $id"
    val path = id.replace(".", "/")
}
