package com.github.turansky.popper

internal enum class Package(
    id: String,
) {
    CORE("popper.core"),

    ;

    val pkg = "package $id"
    val path = id.replace(".", "/")
}
