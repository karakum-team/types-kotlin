package karakum.browser

import karakum.common.UnionConstant
import karakum.common.objectUnionBody
import karakum.common.unionBody

internal const val VIDEO_FRAME_REQUEST_ID = "VideoFrameRequestId"
internal const val CANVAS_CONTEXT_ID = "CanvasContextId"

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
    source: String,
): Sequence<ConversionResult> {
    val (content, additionalType) = prepareContent(
        source.replace(";\n     *", ";--\n     *")
    )

    val interfaces =
        Regex("""interface (HTML.+?|PictureInPictureWindow.+?|ValidityState|AssignedNodesOptions|VideoFrameMetadata|VideoPlaybackQuality|RemotePlayback .+?) \{[\s\S]+?\}""")
            .findAll(content)
            .map { it.value }
            .mapNotNull { convertInterface(it) }

    return interfaces
        .plus(additionalType)
        .plus(
            ConversionResult(
                name = VIDEO_FRAME_REQUEST_ID,
                body = "sealed external interface $VIDEO_FRAME_REQUEST_ID",
                pkg = "dom.html",
            )
        )
        .plus(
            ConversionResult(
                name = "HTMLCollectionOf",
                body = "typealias HTMLCollectionOf<T> = HTMLCollection",
                pkg = "dom.html",
            )
        )
}

private fun prepareContent(
    source: String,
): Pair<String, ConversionResult> {
    val ids = Regex("""getContext\(contextId\: "([\w\d]+)"\, """)
        .findAll(source)
        .map { it.groupValues[1] }
        .toList()

    fun kotlinName(id: String): String =
        if (id == "2d") "canvas" else id

    val contextIdBody = objectUnionBody(
        name = CANVAS_CONTEXT_ID,
        constants = ids.map { id ->
            val name = kotlinName(id)

            UnionConstant(
                kotlinName = name,
                jsName = name,
                value = id,
                comment = if (id != name) "// `$id`" else null
            )
        }
    )

    val contextId = ConversionResult(
        CANVAS_CONTEXT_ID,
        contextIdBody,
        "dom.html",
    )

    val content = ids.fold(source) { acc, id ->
        val name = kotlinName(id)

        acc.replace(
            """getContext(contextId: "$id"""",
            """getContext(contextId: $CANVAS_CONTEXT_ID.$name""",
        )
    }

    return content to contextId
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
        "AssignedNodesOptions",
        "HTMLOrSVGElement",
        "HTMLHyperlinkElementUtils",
        "VideoFrameMetadata",
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

    val modifier = if (
        type == "class" &&
        name.startsWith("HTML") &&
        name.endsWith("Element")
    ) "abstract" else "sealed"

    val body = "$modifier external $declaration {\n$members\n}"

    val pkg = when (name) {
        "RemotePlayback" -> "remoteplayback"

        else -> "dom.html"
    }

    return ConversionResult(
        name = name,
        body = body,
        pkg = pkg,
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

    when (true) {
        (source.startsWith("on") && "(this:" in source),
        source.startsWith("["),
        ("webkitEntries" in source),
        ("webkitdirectory" in source),
        -> return "    // $source"
    }


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
        "number" -> numberType(name)
        "DOMHighResTimeStamp" -> "HighResTimeStamp"

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
    val parametersSource = source
        .substringAfter("(")
        .substringBefore("):")

    val parameters = when (parametersSource) {
        "...nodes: (Element | Text)[]",
        -> listOf(
            "vararg nodes: Element /* | Text */",
        )

        else -> parametersSource
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
    }

    val params = if (parameters.size > 1) {
        parameters.joinToString(",\n", "\n", ",\n")
    } else parameters.joinToString("\n")

    val result = (": " + source.substringAfter("): "))
        .removeSuffix(": void")
        .replace(": Promise<number>", ": Promise<Number>")
        .replace(": WebGLShader[]", ": ReadonlyArray<WebGLShader>")
        .replace(": GLuint[]", ": ReadonlyArray<GLuint>")
        .replace(": string[]", ": ReadonlyArray<String>")
        .replace(": Element[]", ": ReadonlyArray<Element>")
        .replace(": Node[]", ": ReadonlyArray<Node>")
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
