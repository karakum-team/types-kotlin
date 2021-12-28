package com.github.turansky.query

abstract class Declaration {
    protected abstract val source: String

    abstract val name: String

    abstract fun toCode(): String
}
