package karakum.browser

import java.io.File

internal object RenderingContextRegistry {
    private lateinit var map: Map<String, RenderingContextData>

    fun fill(
        definitionFile: File,
    ) {
        val dataList = Regex("""getContext\(contextId: "(\w+)", options\?: (\w+)\): (\w+) \| null""")
            .findAll(definitionFile.readText())
            .map { result ->
                RenderingContextData(
                    id = result.groupValues[1],
                    options = result.groupValues[2],
                    type = result.groupValues[3],
                )
            }

        val dataMap = dataList.sortedBy { it.options.length }
            .associateBy { it.type }

        println(dataMap.values.joinToString("\n\n"))

        map = dataMap
    }
}

private data class RenderingContextData(
    val id: String,
    val options: String,
    val type: String,
)
