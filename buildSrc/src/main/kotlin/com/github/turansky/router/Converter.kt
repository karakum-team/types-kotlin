package com.github.turansky.router

private const val DELIMITER = "//--delimiter--//"

internal data class ConversionResult(
    val name: String,
    val body: String,
    // temp field
    val ready: Boolean,
)

internal fun convertDefinitions(
    source: String,
): Sequence<ConversionResult> {
    return source
        .replace("<ParamKey extends string = string>", "")
        .replace("<ParamKey>", "")
        .replace("\n/**\n", "\n$DELIMITER\n/**\n")
        .replace("\nexport ", "\n$DELIMITER\nexport ")
        .replace("\n */\n$DELIMITER\n", "\n */\n")
        .splitToSequence("\n$DELIMITER\n")
        .map { it.replace("\ninterface ", "\n\nexport interface ") }
        .map { it.replace("\ndeclare const ", "\n\nexport declare const ") }
        .flatMap { it.splitToSequence("\n\n") }
        .map { content ->
            val name = content.substringAfter(" */\n")
                .substringAfter("export ")
                .substringBefore(" extends ")
                .substringBefore(": ")
                .substringBefore("(")
                .substringBefore("<")
                .substringBefore(" = {")
                .substringBefore(" {")
                .substringBefore(" = ")
                .substringAfterLast(" ")

            val body = content
                .splitToSequence("export declare ", "export ")
                .filter { it.isNotEmpty() }
                .joinToString("")

            convert(name, body)
        }
}

private fun convert(
    name: String,
    source: String,
): ConversionResult {
    val contentSource = source.substringAfter(" */\n")
    val comment = source.removeSuffix(contentSource).removeSuffix("\n")

    val type = contentSource.substringBefore(" ")
    val content = when (type) {
        "const" -> convertConst(name, contentSource)
        "function" -> convertFunction(name, contentSource)
        "type" -> convertType(name, contentSource)
        "interface" -> convertInterface(name, contentSource)
        "enum" -> convertEnum(contentSource)

        else -> TODO()
    }

    val body = sequenceOf(comment, content)
        .filter { it.isNotEmpty() }
        .joinToString("\n")

    return ConversionResult(
        name = name,
        body = body,
        ready = content != contentSource
    )
}
