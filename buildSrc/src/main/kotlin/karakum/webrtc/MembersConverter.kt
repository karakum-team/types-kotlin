package karakum.webrtc

private val IGNORED = setOf(
    "NavigatorGetUserMedia",
    "RTCDtlsTransportEventMap",
    "RTCIceTransportEventMap",
    "RTCPeerConnection",
    "RTCPeerConnectionStatic",

    "RTCDataChannel",
    "RTCDtlsTransport",
    "RTCIceTransport",
)

internal fun convertMembers(
    name: String,
    source: String,
): String {
    if (source.isEmpty())
        return ""

    if (name in IGNORED)
        return "/*\n$source\n*/"

    return source.trimIndent()
        .splitToSequence("\n")
        .filter { it.isNotEmpty() }
        .map { convertMember(it) }
        .joinToString("\n")
}

private fun convertMember(
    source: String,
): String {
    if (source.startsWith("//"))
        return "    $source"

    val body = source.substringBefore(";")
    val content = if (isProperty(body)) {
        convertProperty(body)
    } else {
        convertMethod(body)
    }

    val comment = source.substringAfter("; // ", "")
        .ifEmpty { null }

    return sequenceOf(content, comment)
        .filterNotNull()
        .joinToString(" // ")
}

private fun isProperty(
    source: String,
): Boolean =
    ("(" !in source) || (source.indexOf(":") < source.indexOf("("))

private fun convertProperty(
    source: String,
): String {
    val modifier = if (source.startsWith("readonly ")) "val" else "var"
    val body = source.removePrefix("readonly ")

    val name = body.substringBefore(": ").removeSuffix("?")
    var type = kotlinType(body.substringAfter(": "), name)

    if (body.startsWith("$name?"))
        type = type.addOptionality()

    return "$modifier $name: $type"
}

internal fun convertMethod(
    source: String,
): String {
    val name = source.substringBefore("(")
        .substringBefore("<")
        .removeSuffix("?")
        .ifEmpty { "/* native */ invoke" }

    if (name == "addEventListener" || name == "removeEventListener")
        return "    // $source"

    val typeParameters = source.substringBefore("(")
        .substringAfter("<", "")
        .let { if (it.isNotEmpty()) "<$it" else "" }
        .replace(" extends ", " : ")

    val parametersSource = source
        .substringAfter("(")
        .substringBeforeLast("): ")

    val optional = source.startsWith("$name?")
    val parameters = when {
        parametersSource.isNotEmpty()
        -> parametersSource
            .splitToSequence(", ")
            .joinToString(",\n") {
                convertParameter(it, optional)
            }

        else -> ""
    }

    val returnType = kotlinType(source.substringAfterLast("): "), name)

    if (optional)
        return "val $name: (($parameters) -> $returnType)?"

    val isOperator = when (name) {
        "get" -> parameters.count { it == ':' } == 1
        "set" -> parameters.count { it == ':' } == 2
        else -> false
    }
    val keyword = if (isOperator) "operator fun" else "fun"

    val returnDeclaration = if (returnType != UNIT) {
        ": $returnType"
    } else ""

    var result = "$keyword $typeParameters $name($parameters)$returnDeclaration"
    if (name == "createToken" && typeParameters.isNotEmpty())
        result = "    // TODO: restore after alias update\n    // $result"

    return result
}

internal fun convertParameter(
    source: String,
    lambdaMode: Boolean,
): String {
    val name = source
        .substringBefore(": ")
        .removeSuffix("?")

    val type = kotlinType(source.substringAfter(": "), name)
    val declaration = if (source.startsWith("$name?:")) {
        if (lambdaMode) {
            type.addOptionality()
        } else {
            "$type = definedExternally"
        }
    } else type

    return "$name: $declaration"
}
