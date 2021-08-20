package com.github.turansky.react

import java.io.File

fun printKotlinAdapter(
    definitionsFile: File,
) {
    val source = definitionsFile.readText()
        .substringAfter("\n    interface DOMAttributes<", "")
        .substringAfter("{\n", "")
        .substringBefore("\n    }", "")

    println("--------------------------------")
    println(source)
    println("--------------------------------")
}
