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
        .substringBefore("\n}")
        .replace("/**`", "/ **`")
        .replace("/*`", "/ *`")
        .trimIndent()

    if (memberSource == "[key: string]: any;")
        return ConversionResult(
            name = name,
            body = "typealias $name = Record<String, Any>"
        )

    var members = memberSource
        .replace("env?: {\n    [key: string]: string;\n};", "env?: Record<string, string>;")
        .splitToSequence("\n")
        .joinToString("\n") { line ->
            if (line.endsWith(";")) {
                convertMember(line.removeSuffix(";"))
            } else {
                line
            }
        }
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

    val memberSource = source.substringAfter(" {\n")
        .substringBefore("\n}")
        .replace("/**`", "/ **`")
        .replace("/*`", "/ *`")
        .trimIndent()

    var members = memberSource
        // .replace("env?: {\n    [key: string]: string;\n};", "env?: Record<string, string>;")
        .splitToSequence("\n")
        .joinToString("\n") { line ->
            if (line.endsWith(";")) {
                convertMember(line.removeSuffix(";"))
            } else {
                line
            }
        }
        .prependIndent("    ")

    val body = "external class $declaration {\n$members\n}"

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
    )
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
): String =
    when {
        source.startsWith("constructor(")
        -> convertConstructor(source)

        "(" in source.substringBefore(":")
        -> convertMethod(source)

        else -> convertProperty(source)
    }

private fun convertProperty(
    source: String,
): String {
    val nameSource = source.substringBefore(": ")
    val typeSource = source.substringAfter(": ")

    val name = nameSource.removeSuffix("?")
    var type = kotlinType(typeSource)

    if (nameSource.endsWith("?")) {
        if (type.startsWith("("))
            type = "($type)"

        type += "?"
    }

    return "var $name: $type"
}

private fun convertConstructor(
    source: String,
): String {
    return "// $source"
}

private fun convertMethod(
    source: String,
): String {
    // TODO: move to patches
    sequenceOf(
        "string | NodeJS.ReadableStream",
        "string | Error",
        "string | string[]",
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

    var declaration = source.substringBefore("(")
    if ("<" in declaration) {
        val name = declaration.substringBefore("<")
        val typeParameters = declaration.substringAfter(name)

        declaration = "$typeParameters $name"
    }

    val parametersSource = source
        .substringAfter("(")
        .substringBeforeLast("): ")

    val parameters = if (parametersSource.isNotEmpty()) {
        val params = if ("onResult: (err?: Error, res?: HttpClientResponse) => void" in parametersSource) {
            parametersSource
                .substringBefore(", onResult: ")
                .split(", ")
                .map { convertParameter(it) }
                .plus("onResult: (err: JsError?, res: HttpClientResponse?) -> Unit")
        } else {
            parametersSource
                .split(", ")
                .map { convertParameter(it) }
        }

        if (params.size > 1) {
            params.joinToString(",\n")
        } else params.joinToString(", ")
    } else ""

    val returnType = kotlinType(source.substringAfter("): "))
    val returns = when (returnType) {
        "void" -> ""
        else -> ": $returnType"
    }

    return "fun $declaration($parameters)$returns"
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
