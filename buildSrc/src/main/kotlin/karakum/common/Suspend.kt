package karakum.common

private val ASYNC_FUNCTION_REGEX = Regex(
    """^((operator)?\s*)(fun.*[ >])([a-zA-Z\d]+)(\(.*\)): Promise<(.+)>( = definedExternally)?$""",
    RegexOption.DOT_MATCHES_ALL
)

private const val DELIMITER = "<!----!>"

internal fun withSuspendAdapter(
    source: String,
): Sequence<String> =
    source.replace(
        ASYNC_FUNCTION_REGEX,
        transform = { mr ->
            val p3 = mr.groupValues[3]
            val originalName = mr.groupValues[4]
            val parameters = mr.groupValues[5]
            val payload = mr.groupValues[6]
            val de = mr.groupValues[7]

            val ret = when (payload) {
                "*" -> ": Any?"
                "Void" -> if (de.isNotEmpty()) ": Unit" else ""
                else -> ": $payload"
            }

            var suspendName = originalName.removeSuffix("Async")

            // TEMP
            when (originalName) {
                "createComputePipelineAsync",
                "createRenderPipelineAsync",
                -> suspendName = originalName
            }

            val asyncName = suspendName + "Async"
            val jsName = if (asyncName != originalName) """@JsName("$originalName")""" else ""

            sequenceOf(
                "@JsAsync",
                "suspend $p3$suspendName$parameters$ret$de",
                DELIMITER,
                jsName,
                "$p3$asyncName$parameters: Promise<$payload>$de",
            ).joinToString("\n")
        }
    ).splitToSequence(DELIMITER)
        .map { it.trim() }
