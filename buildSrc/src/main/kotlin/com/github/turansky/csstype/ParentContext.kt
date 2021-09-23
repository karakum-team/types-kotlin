package com.github.turansky.csstype

internal interface ParentProvider {
    val parentTypes: List<String>
    fun get(): String
}

internal interface ParentConsumer {
    fun apply(
        items: List<ConversionResult>,
    ): List<ConversionResult>
}

internal class ParentContext(
    private val type: String,
    private val updateMode: Boolean = false,
) : ParentProvider, ParentConsumer {
    private val marker: String = "// $type\n"

    override lateinit var parentTypes: List<String>

    override fun get(): String =
        parentTypes.joinToString(",\n")

    override fun apply(
        items: List<ConversionResult>,
    ): List<ConversionResult> {
        parentTypes = items.asSequence()
            .filter { marker in it.body }
            .map { it.name }
            .sorted()
            .toList()

        var result = items.map { it.copy(body = it.body.replace(marker, "")) }
        if (updateMode) {
            val oldType = result.first { it.name == type }
            val newType = oldType.copy(
                body = oldType.body.replaceFirst(type, "$type:\n${get()}")
            )
            result = result - oldType + newType
        }
        return result
    }
}
