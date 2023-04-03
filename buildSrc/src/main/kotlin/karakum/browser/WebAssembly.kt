package karakum.browser

internal fun webAssemblyDeclarations(
    source: String,
): Sequence<ConversionResult> {
    val content = webAssemblyContent(source)

    val types = convertTypes(
        content = content,
        getPkg = { "webassembly" },
    )

    val interfaces = Regex("""interface .+? \{[\s\S]*?\n}""")
        .findAll(content)
        .map { it.value }
        .groupBy { it.substringBefore(" {\n") }
        .map { (declaration, sourceParts) ->
            sourceParts.singleOrNull() ?: run {
                val body = sourceParts
                    .asSequence()
                    .map {
                        it.replace(": any\n", ": any;\n")
                            .substringAfter(" {\n")
                            .substringBefore("\n}")
                            .trimIndent()
                    }
                    .joinToString("\n")
                    .splitToSequence("\n")
                    .distinct()
                    .joinToString("\n")
                    .prependIndent("    ")

                "$declaration {\n$body\n}"
            }
        }
        .mapNotNull {
            convertInterface(
                source = it,
                getStaticSource = { getStaticSource(it, content) },
                predefinedPkg = "webassembly",
            )
        }.map { result ->
            if (result.name.endsWith("Error")) {
                result.copy(
                    body = """
                        external class ${result.name}(
                            message: String = definedExternally,
                        ): JsError
                    """.trimIndent()
                )
            } else {
                result
            }
        }

    val functions = convertFunctions(
        content = content,
        getPkg = { "webassembly" },
    )

    return (types + interfaces + functions)
}

private fun webAssemblyContent(
    source: String,
): String =
    source
        .replace("\r\n", "\n")
        .substringAfter("\ndeclare namespace WebAssembly {\n")
        .substringBefore("\n}")
        .trimIndent()
        .splitUnion("string | string[]")
        .splitUnion("number | bigint")
        .replace("\n\n", "\n")
