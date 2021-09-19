package com.github.turansky.csstype

import com.github.turansky.common.kebabToCamel

private const val LENGTH_TYPE = "LengthType"

internal class LengthTypeConsumer : ParentConsumer {
    override fun apply(
        items: List<ConversionResult>,
    ): List<ConversionResult> {
        val lengthItems = items.filter {
            "// Globals | TLength | " in it.body
        }

        val parentMap = lengthItems.asSequence()
            .flatMap { item ->
                item.body
                    .substringAfter("// Globals | TLength | ")
                    .substringBefore("\n")
                    .splitToSequence(" | ")
                    .filter { !it.startsWith("(") }
                    .map { it.removeSurrounding("\"") }
                    .map { it to item.name }
            }
            .groupBy({ it.first }, { it.second })

        val childTypes = parentMap.asSequence()
            .filter { it.key != "TLength" }
            .map {
                val name = it.key.kebabToCamel().capitalize()

                """
                    sealed interface $name: 
                        ${it.value.joinToString(",\n")}
                """.trimIndent()
            }
            .joinToString("\n\n")

        val body = """
            sealed external interface $LENGTH_TYPE: 
                $LENGTH_PROPERTY {
                
                $childTypes    
            }
        """.trimIndent()

        return items - lengthItems +
                lengthItems.map {
                    it.copy(body = "// $LENGTH_PROPERTY\n" + it.body.substringAfter("\n"))
                } + ConversionResult(LENGTH_TYPE, body)
    }
}
