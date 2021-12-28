package com.github.turansky.query

class Constructor(
    override val source: String,
) : Member() {
    private val parameters: List<String> by lazy {
        parseParameters(source.removeSuffix(")"))
    }

    override fun toCode(): String {
        return parameters.joinToString(",\n", "(", ")")
    }
}
