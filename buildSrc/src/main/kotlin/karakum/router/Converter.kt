package karakum.router

private const val DELIMITER = "//--delimiter--//"

internal data class ConversionResult(
    val name: String,
    val body: String,
)

internal fun convertDefinitions(
    source: String,
): Sequence<ConversionResult> {
    return source
        // TODO: check how to fix comment
        .replace("`/*`", "`/ *`")
        .replace("<ParamKey extends string = string>", "")
        .replace("Readonly<[\n    ParamsOrKey\n] extends [string] ? Params<ParamsOrKey> : Partial<ParamsOrKey>>", "Params")
        .replace("<ParamKey>", "")
        .replace("\n/**\n", "\n$DELIMITER\n/**\n")
        .replace("\nexport ", "\n$DELIMITER\nexport ")
        .replace("\n */\n$DELIMITER\n", "\n */\n")
        .splitToSequence("\n$DELIMITER\n")
        .map { it.replace("\ninterface ", "\n\nexport interface ") }
        .map { it.replace("\ndeclare const ", "\n\nexport declare const ") }
        .flatMap { it.splitToSequence("\n\n") }
        .mapNotNull { content ->
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
): ConversionResult? {
    var contentSource = source.substringAfter(" */\n")
    val comment = source.removeSuffix(contentSource).removeSuffix("\n")

    contentSource = contentSource.removePrefix("declare ")
        .takeIf { !it.startsWith("type {") }
        ?: return null

    val sourceType = contentSource.substringBefore(" ")
    val content = when (sourceType) {
        "const" -> convertConst(name, contentSource)
        "function" -> convertFunction(name, contentSource)
        "type" -> convertType(name, contentSource)
        "interface" -> convertInterface(name, contentSource)
        "enum" -> convertEnum(contentSource)

        "namespace" -> return null

        // HistoryRouter workaround
        "{" -> return null

        // TODO: check
        "import" -> return null

        else -> {
            if (sourceType.startsWith("{};"))
                return null

            if (sourceType.startsWith("///"))
                return null

            println("FAILED")
            println(sourceType)

            TODO()
        }
    }

    val body = sequenceOf(comment, content)
        .filter { it.isNotEmpty() }
        .joinToString("\n")

    return ConversionResult(
        name = name,
        body = body,
    )
}
