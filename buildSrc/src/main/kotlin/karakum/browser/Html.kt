package karakum.browser

import karakum.common.unionBody
import java.io.File

private val DEPRECATED = setOf(
    "HTMLDirectoryElement",
    "HTMLFontElement",
    "HTMLFrameElement",
    "HTMLFrameSetElement",
    "HTMLMarqueeElement",

    "HTMLTableHeaderCellElement",
    "HTMLTableDataCellElement",
)

internal fun htmlDeclarations(
    definitionsFile: File,
): Sequence<ConversionResult> {
    val content = definitionsFile.readText()
        .replace(";\n     *", ";--\n     *")

    val interfaces = Regex("""interface (HTML.+?|ValidityState) \{[\s\S]+?\}""")
        .findAll(content)
        .map { it.value }
        .mapNotNull { convertInterface(it) }

    val types = content
        .splitToSequence("\ntype ")
        .drop(1)
        .filter { it.startsWith("SelectionMode = ") }
        .map { it.substringBefore(";\n") }
        .map { convertType(it) }

    return interfaces
        .plus(types)
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
        name in DEPRECATED -> return null
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
    if ("\n" in source) {
        val comment = source.substringBeforeLast("\n")
            .replace(";--\n", ";\n") // RESTORE

        if ("@deprecated" in comment)
            return null

        val member = convertMember(source.substringAfterLast("\n"))
            ?: return null

        return comment + "\n" + member
    }

    when {
        source.startsWith("addEventListener<") -> return null
        source.startsWith("addEventListener(") -> return null
        source.startsWith("removeEventListener<") -> return null
        source.startsWith("removeEventListener(") -> return null
        source.startsWith("remove()") -> return null
        source.startsWith("toString()") -> return null
    }

    if (source.startsWith("on") && "(this:" in source)
        return "    // $source"

    if (source.startsWith("["))
        return "    // $source"

    if ("(" !in source)
        return convertProperty(source)

    return convertFunction(source)
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

    if (name == "as")
        name = "`$name`"

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
            ptype = getParameterType(ptype)

            if (pname.endsWith("?")) {
                pname = pname.removeSuffix("?")
                ptype += " = definedExternally"
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
        .replace("<void>", "<Void>")
        .replace(" | null", "?")

    return "fun $name($params)$result"
}

private fun getParameterType(
    source: String,
): String {
    if (source.endsWith(" | null")) {
        var type = getParameterType(source.removeSuffix(" | null"))
        if ("? /* " !in type)
            type += "?"

        return type
    }

    return when {
        source.startsWith("\"")
        -> "String /* $source */"

        source == "string"
        -> "String"

        source == "number"
        -> "Number"

        source == "HTMLOptionElement | HTMLOptGroupElement"
        -> "HTMLElement /* HTMLOptionElement | HTMLOptGroupElement */"

        source == "HTMLElement | number"
        -> "Any? /* HTMLElement | number */"

        source.endsWith("[]") -> {
            var atype = source.removeSuffix("[]")
            if (atype == "string")
                atype = "String"

            "ReadonlyArray<$atype>"
        }

        else -> source
    }
}

private fun convertType(
    source: String,
): ConversionResult {
    require(" = \"" in source)

    val (name, body) = source
        .substringBefore(";")
        .split(" = ")

    val values = body
        .splitToSequence(" | ")
        .map { it.removeSurrounding("\"") }
        .toList()

    return ConversionResult(
        name = name,
        body = unionBody(name, values),
        pkg = "dom.html"
    )
}
