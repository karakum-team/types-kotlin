package karakum.browser

import java.io.File

internal val GET_CONTEXT_REGEX = Regex("""\n    getContext\(contextId: "(\w+)", options\?: (\w+)\): (\w+) \| null;""")

internal object RenderingContextRegistry {
    private lateinit var map: Map<String, RenderingContextData>

    fun fill(
        definitionFile: File,
    ) {
        val dataList = GET_CONTEXT_REGEX
            .findAll(definitionFile.readText())
            .map { result ->
                RenderingContextData(
                    id = result.groupValues[1],
                    options = result.groupValues[2],
                    type = result.groupValues[3],
                )
            }

        map = dataList.sortedBy { it.options.length }
            .associateBy { it.type }
            .mapValues { (_, data) ->
                if (data.options == "any") {
                    data.copy(options = data.type.removePrefix("Offscreen") + "Settings")
                } else data
            }
    }

    fun getIdDeclaration(name: String): String? {
        val data = map[name]
            ?: return null

        return """
        @JsValue("${data.id}")
        val ID: $RENDERING_CONTEXT_ID<${data.type}, ${data.options}>
        """.trimIndent()
    }
}

private data class RenderingContextData(
    val id: String,
    val options: String,
    val type: String,
)
