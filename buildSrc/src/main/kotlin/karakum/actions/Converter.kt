package karakum.actions

import karakum.common.UnionConstant
import karakum.common.unionBody
import karakum.common.unionBodyByConstants

private val EXCLUDED_NAMES = setOf(
    "getCacheEntry",
    "reserveCache",
    "retryTypedResponse",
)

internal fun convert(
    content: String,
): Sequence<ConversionResult> {
    val body = cleanup(content)

    return ("\n" + body).splitToSequence("\nexport declare ", "\nexport ", "\ndeclare ")
        .drop(1)
        .map { it.substringBefore("\n/**") }
        // TODO: check
        .filter { !it.startsWith("const chmod") }
        .mapNotNull { convertItem(it) }
        .filter { it.name !in EXCLUDED_NAMES }
}

private fun cleanup(
    content: String,
): String =
    content.splitToSequence("\n")
        .filter { line -> !line.startsWith("/// ") }
        .filter { line -> !line.startsWith("import ") }
        .filter { line -> !line.startsWith("    private _") }
        .joinToString("\n")
        .replace(" ifm.", " ")
        .replace(" im.", " ")
        .replace("<ifm.", "<")
        .trim()

private fun convertItem(
    source: String,
): ConversionResult? {
    if (source.startsWith("{"))
        return null

    if (source.startsWith("default "))
        return null

    val type = source.substringBefore(" ")
    when (type) {
        "interface" ->
            return convertInterface(
                source = source.substringAfter(" ")
            )

        "class" ->
            return convertClass(
                source = source.substringAfter(" ")
            )

        "function" ->
            return convertFunction(
                source = source.substringAfter(" ")
            )

        "enum" ->
            return convertEnum(
                source = source.substringAfter(" ")
            )

        "type" ->
            return convertType(
                source = source.substringAfter(" ")
            )

        "const" ->
            return convertConst(
                source = source.substringAfter(" ")
            )
    }

    val name = source.substringAfter(" ")
        .substringBefore("<")
        .substringBefore(" ")
        .substringBefore("(")
        .substringBefore(":")

    var body = source
    if (body.startsWith("function ") && "():" !in body)
        body = body
            .replaceFirst("(", "(\n")
            .replace(", ", ",\n")
            .replaceFirst("):", ",\n):")

    return ConversionResult(
        name = name,
        body = body,
    )
}

private fun convertEnum(
    source: String,
): ConversionResult {
    val name = source.substringBefore(" ")

    val memberSource = source.substringAfter(" {\n")
        .substringBefore("\n}")
        .trimIndent()

    var constants = memberSource
        .splitToSequence(",\n")
        .map { constSource ->
            val comment = if ("\n" in constSource) {
                constSource.substringBeforeLast("\n")
            } else null

            val (constName, value) = constSource.substringAfterLast("\n").split(" = ")
            UnionConstant(
                kotlinName = constName,
                jsName = constName,
                value = value,
                originalValue = true,
                comment = comment,
            )
        }
        .toList()

    val body = unionBodyByConstants(
        name = name,
        constants = constants,
    )

    return ConversionResult(
        name = name,
        body = body,
    )
}

private fun convertInterface(
    source: String,
): ConversionResult {
    val name = source.substringBefore(" ")
        .substringBefore("<")

    val declaration = source.substringBefore(" {\n")

    val memberSource = source.substringAfter(" {\n")
        .substringBefore(";\n}")
        .replace("/**`", "/ **`")
        .replace("/*`", "/ *`")
        .trimIndent()

    if (memberSource == "[key: string]: any")
        return ConversionResult(
            name = name,
            body = "typealias $name = Record<String, Any>"
        )

    var members = memberSource
        .replace("env?: {\n    [key: string]: string;\n};", "env?: Record<string, string>;")
        .splitToSequence(";\n")
        .mapNotNull { convertMember(it) }
        .joinToString("\n")
        .prependIndent("    ")

    val body = "external interface $declaration {\n$members\n}"

    return ConversionResult(
        name = name,
        body = body,
    )
}

private fun convertClass(
    source: String,
): ConversionResult {
    val name = source.substringBefore(" ")
        .substringBefore("<")

    val declaration = source.substringBefore(" {\n")
        .replace(" extends events.EventEmitter", " : node.events.EventEmitter")
        .replace(" extends Error", " : JsError")
        .replace(" extends ", " : ")
        .replace(" implements ", " : ")

    val memberSource = source.substringAfter(" {\n")
        .substringBefore(";\n}")
        .replace("/**`", "/ **`")
        .replace("/*`", "/ *`")
        .trimIndent()

    var members = memberSource
        .splitToSequence(";\n")
        .mapNotNull { convertMember(it) }
        .joinToString("\n")
        .prependIndent("    ")

    var body = "external class $declaration {\n$members\n}"

    body = when (name) {
        "DefaultGlobber" ->
            body
                .replace("fun getSearchPaths(", "override fun getSearchPaths(")
                .replace("fun glob(", "override fun glob(")
                .replace("fun globGenerator(", "override fun globGenerator(")

        in CREDENTIAL_HANDLERS,
        -> body
            .replace("fun prepareRequest(", "override fun prepareRequest(")

        else -> body
            .replace("fun toString(", "override fun toString(")
    }

    when (name) {
        "DefaultArtifactClient",
        "DefaultGlobber",
        -> body = "sealed $body"
    }

    return ConversionResult(
        name = name,
        body = body,
    )
}


private fun convertFunction(
    source: String,
): ConversionResult {
    val name = source
        .substringBefore("(")
        .substringBefore("<")

    val bodies = convertMember(
        source
            .substringBefore(";\n")
            .removeSuffix(";")
            .replace(": Map<number, string>", ": Map<number,string>"),
    )!!
    val body = ("\n" + bodies)
        .replace("\nfun ", "\nexternal fun ")
        .removePrefix("\n")

    return ConversionResult(
        name = name,
        body = body,
    )
}

private fun convertType(
    source: String,
): ConversionResult? {
    val (name, bodySource) = source
        .removeSuffix(";")
        .split(" = ")

    when (name) {
        "IToolRelease",
        "IToolReleaseFile",
        -> return null
    }

    val body = when {
        "' | '" in bodySource -> {
            val values = bodySource
                .split(" | ")
                .map { it.removeSurrounding("'") }

            unionBody(name, values)
        }

        bodySource == "(SummaryTableCell | string)[]"
        -> "typealias $name = ReadonlyArray<Any /* SummaryTableCell | String */>"

        else -> TODO("Unable to convert body source: '$bodySource'")
    }

    return ConversionResult(
        name = name,
        body = body,
    )
}

private fun convertConst(
    source: String,
): ConversionResult {
    val name = source
        .substringBefore(":")
        .substringBefore(" = ")

    val body = if (" = " in source) {
        val value = source
            .substringAfter(" = ")
            .removeSuffix(";")

        "const val $name = $value"
    } else {
        val type = kotlinType(
            source.substringAfter(": ")
                .removeSuffix(";")
        )

        "external val $name: $type"
    }

    return ConversionResult(
        name = name,
        body = body,
    )
}

private fun convertMember(
    source: String,
): String? {
    if ("\n" in source) {
        val member = convertMember(source.substringAfterLast("\n"))
            ?: return null

        val comment = source.substringBeforeLast("\n")
        return "$comment\n$member"
    }

    return when {
        source == "private constructor()"
        -> "// $source"

        source.startsWith("private ")
        -> null

        source.startsWith("constructor(")
        -> convertConstructor(source)

        "(" in source.substringBefore(":")
        -> convertMethod(source)

        else -> convertProperty(source)
    }
}

private fun convertProperty(
    source: String,
): String {
    val cleanSource = source.removePrefix("readonly ")
    val readonly = cleanSource != source
    val nameSource = cleanSource.substringBefore(": ")
    val typeSource = cleanSource.substringAfter(": ")

    val name = nameSource.removeSuffix("?")
    var type = kotlinType(typeSource)

    if (nameSource.endsWith("?")) {
        if (type.startsWith("("))
            type = "($type)"

        if (!type.endsWith("?"))
            type += "?"
    }

    val modifier = if (readonly) "val" else "var"
    return "$modifier $name: $type"
}

private fun convertConstructor(
    source: String,
): String {
    // TODO: move to patches
    sequenceOf(
        "string | string[]",
    ).forEach { unionType ->
        if (": $unionType" in source) {
            val (t1, t2) = unionType.split(" | ")

            return sequenceOf(
                source.replace(": $unionType", ": $t1"),
                source.replace(": $unionType", ": $t2"),
            ).map { convertConstructor(it) }
                .joinToString("\n\n")
        }
    }

    val parameters = convertParameters(
        source.removeSurrounding("constructor(", ")")
    )

    return "constructor($parameters)"
}

private fun convertMethod(
    source: String,
): String {
    // TODO: move to patches
    sequenceOf(
        "string | NodeJS.ReadableStream",
        "string | Error",
        "string | string[]",
        "number | string",
        "Buffer | string",
    ).forEach { unionType ->
        if (": $unionType" in source) {
            val (t1, t2) = unionType.split(" | ")

            return sequenceOf(
                source.replace(": $unionType", ": $t1"),
                source.replace(": $unionType", ": $t2"),
            ).map { convertMethod(it) }
                .joinToString("\n\n")
        }
    }

    if (source.startsWith("static "))
        return "/* static */\n" + convertMethod(source.removePrefix("static "))

    var declaration = source.substringBefore("(")
    if ("<" in declaration) {
        val name = declaration.substringBefore("<")
        val typeParameters = declaration.substringAfter(name)

        declaration = "$typeParameters $name"
    }

    val parameters = convertParameters(
        source.substringAfter("(")
            .substringBeforeLast("): ")
    )

    val returnType = kotlinType(source.substringAfter("): "))
    val returns = when (returnType) {
        "void" -> ""
        else -> ": $returnType"
    }

    return "fun $declaration($parameters)$returns"
}

private fun convertParameters(
    source: String,
): String {
    if (source.isEmpty())
        return ""

    val params = if ("onResult: (err?: Error, res?: HttpClientResponse) => void" in source) {
        source
            .substringBefore(", onResult: ")
            .split(", ")
            .map { convertParameter(it) }
            .plus("onResult: (err: JsError?, res: HttpClientResponse?) -> Unit")
    } else {
        source
            .split(", ")
            .map { convertParameter(it) }
    }

    return params.joinToString(",\n")
}

private fun convertParameter(
    source: String,
): String {
    val nameSource = source.substringBefore(": ")
    val typeSource = source.substringAfter(": ")

    var name = nameSource.removeSuffix("?")
    if (name == "val")
        name = "value"

    var type = kotlinType(typeSource)

    if (nameSource.endsWith("?")) {
        type += " = definedExternally"
    }

    return "$name: $type"
}
