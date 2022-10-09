package karakum.browser

import java.io.File

internal fun htmlDeclarations(
    definitionsFile: File,
): Sequence<ConversionResult> {
    val content = definitionsFile.readText()
        .replace(";\n     *", ";--\n     *")

    return Regex("""interface (HTML.+?|ValidityState) \{[\s\S]+?\}""")
        .findAll(content)
        .map { it.value }
        .mapNotNull { convertInterface(it) }
        .plus(
            ConversionResult(
                name = "HTMLCollectionOf",
                body = "typealias HTMLCollectionOf<T> = HTMLCollection",
                pkg = "dom.html",
            )
        )
}

private fun convertInterface(
    source: String,
): ConversionResult? {
    val name = source
        .substringAfter(" ")
        .substringBefore(" ")

    when {
        name in HTML_ALIAS_CLASSES -> return null
        name == "HTMLOrSVGElement" -> return null
        name.endsWith("NameMap") -> return null
        name.endsWith("EventMap") -> return null
        "Collection" in name -> return null
    }

    val type = when (name) {
        "HTMLOrSVGElement",
        "HTMLHyperlinkElementUtils",
        -> "interface"

        else -> "class"
    }
    val declaration = source.substringBefore(" {\n")
        .replace("interface ", "$type ")
        .replace(" extends ", " :\n")
        .replace(", ", ",\n")

    val memberSource = source
        .substringAfter(" {\n")
        .removeSuffix("}")
        .removeSuffix(";\n")
        .trimIndent()

    val members = if (memberSource.isNotEmpty()) {
        memberSource
            .splitToSequence(";\n")
            .mapNotNull { convertMember(it) }
            .joinToString("\n")
    } else ""

    val body = "sealed external $declaration {\n$members\n}"

    return ConversionResult(
        name = name,
        body = body,
        pkg = "dom.html",
    )
}

private fun convertMember(
    source: String,
): String? {
    if ("\n" in source)
        return convertMember(source.substringAfterLast("\n"))

    when {
        source.startsWith("addEventListener<") -> return null
        source.startsWith("addEventListener(") -> return null
        source.startsWith("removeEventListener<") -> return null
        source.startsWith("removeEventListener(") -> return null
        source.startsWith("toString()") -> return null
    }

    if (source.startsWith("on") && "(this:" in source)
        return "    // $source"

    if (source.startsWith("["))
        return "    // $source"

    if ("(" !in source)
        return convertProperty(source)

    return convertFunction(source.replace(" | null", "?"))
}

private fun convertProperty(
    source: String,
): String {
    val modifier = if (source.startsWith("readonly ")) "val" else "var"
    var (name, type) = source.removePrefix("readonly ").split(": ")

    val optional = type.endsWith(" | null")
    type = type.removeSuffix(" | null")

    type = when (type) {
        "string" -> "String"
        "boolean" -> "Boolean"
        "number" -> "Number"

        else -> if (type.startsWith("\"")) {
            "String /* $type */"
        } else type
    }

    if (name.endsWith("?") || optional) {
        name = name.removeSuffix("?")
        type += "?"
    }

    return "$modifier $name: $type"
}

private fun convertFunction(
    source: String,
): String {
    val name = source.substringBefore("(")
    val parameters = source
        .substringAfter("(")
        .substringBefore(")")
        .splitToSequence(", ")
        .filter { it.isNotEmpty() }
        .map {
            var (pname, ptype) = it.split(": ")
            ptype = when {
                ptype.startsWith("\"")
                -> "String /* $ptype */"

                ptype == "string"
                -> "String"

                ptype == "number"
                -> "Number"

                ptype == "HTMLOptionElement | HTMLOptGroupElement"
                -> "HTMLElement /* HTMLOptionElement | HTMLOptGroupElement */"

                ptype == "HTMLElement | number | null"
                -> "Any? /* HTMLElement | number | null */"

                ptype.endsWith("[]") -> {
                    var atype = ptype.removeSuffix("[]")
                    if (atype == "string")
                        atype = "String"

                    "ReadonlyArray<$atype>"
                }

                else -> ptype
            }

            if (pname.endsWith("?")) {
                pname = pname.removeSuffix("?")
                ptype += "?"
            }

            "$pname: $ptype"
        }
        .toList()

    val params = if (parameters.size > 1) {
        parameters.joinToString(",\n", "\n", ",\n")
    } else parameters.joinToString("\n")

    val result = source.substringAfter(")")
        .removeSuffix(": void")
        .replace(": WebGLShader[]", ": ReadonlyArray<WebGLShader>")
        .replace(": GLuint[]", ": ReadonlyArray<GLuint>")
        .replace(": string[]", ": ReadonlyArray<String>")
        .replace(": string", ": String")
        .replace(": boolean", ": Boolean")
        .replace(": any", ": Any")

    return "fun $name($params)$result"
}
