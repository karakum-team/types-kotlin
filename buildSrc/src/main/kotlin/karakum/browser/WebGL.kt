package karakum.browser

import karakum.common.objectUnionBody
import karakum.common.unionConstant

private val CONVERTED_WEBGL_TYPES = listOf(
    "WebGLContextEvent",
    "WebGLContextEventInit",
)

internal fun webglDeclarations(
    content: String,
): Sequence<ConversionResult> {
    val interfaces = Regex("""interface ((ANGLE_|EXT_|KHR_|OES_|OVR_|WEBGL_|WebGL).+?) \{[\s\S]+?\}""")
        .findAll(content)
        .map { it.value }
        .mapNotNull { convertInterface(it) }
        .toList()

    val classes = Regex("""declare var WebGL.+?: \{[\s\S]+?\}""")
        .findAll(content)
        .map { it.value }
        .mapNotNull { convertCompanion(it) }

    val extension = convertExtension(interfaces.first { it.name == "WebGLRenderingContextBase" }.body)

    return merge(interfaces + classes)
        .plus(extension)
        .plus(Lists())
        .plus(GLNumbers())
}

private fun convertInterface(
    source: String,
): ConversionResult? {
    val name = source
        .substringAfter(" ")
        .substringBefore(" ")

    if (name in CONVERTED_WEBGL_TYPES)
        return null

    val declaration = source.substringBefore(" {\n")
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
            .joinToString("\n") { convertMember(it) }
    } else ""

    val body = "sealed external $declaration {\n$members\n}"

    return ConversionResult(
        name = name,
        body = body,
    )
}

private fun convertExtension(
    source: String,
): ConversionResult {
    val values = source.splitToSequence("\n")
        .filter { "WebGLExtension." in it }
        .map { it.substringAfter("WebGLExtension.") }
        .map { it.substringBefore(")") }
        .toList()

    return ConversionResult(
        name = "WebGLExtension",
        body = objectUnionBody(
            name = "WebGLExtension",
            constants = values.map(::unionConstant),
        ),
    )
}

private fun convertCompanion(
    source: String,
): ConversionResult? {
    val name = source
        .removePrefix("declare var ")
        .substringBefore(": ")

    if (name in CONVERTED_WEBGL_TYPES)
        return null

    val memberSource = source
        .substringAfter(" {\n")
        .removeSuffix("}")
        .removeSuffix(";\n")
        .trimIndent()
        .splitToSequence(";\n")
        .minus("prototype: $name")
        .minus("new(): $name")
        .joinToString("\n")

    val body = if (memberSource.isNotEmpty()) {
        "companion object {\n" +
                memberSource.replace("readonly ", "val ")
                    .replace(Regex(""": 0x\S+"""), ": GLenum")
                    .replace(Regex(""": \-?\d"""), ": GLenum") +
                "\n}"
    } else ""

    return ConversionResult(
        name = name,
        body = body,
    )
}

private fun convertMember(
    source: String,
): String {
    if ("(" !in source)
        return convertProperty(source)

    return convertFunction(source.replace(" | null", "?"))
}

private fun convertProperty(
    source: String,
): String {
    val modifier = if (source.startsWith("readonly ")) "val" else "var"
    var (name, type) = source.removePrefix("readonly ").split(": ")
    type = when {
        type == "string" -> "String"
        type == "boolean" -> "Boolean"
        type == "HTMLCanvasElement | OffscreenCanvas" -> "Any /* $type */"

        type.startsWith("0x") -> "GLenum"
        type.toIntOrNull() != null -> "GLenum"

        else -> type
    }

    if (name.endsWith("?")) {
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
                pname == "extensionName" && ptype.startsWith("\"")
                -> "WebGLExtension." + ptype.removeSurrounding("\"")

                ptype == "string"
                -> "String"

                ptype == "GLint | GLboolean"
                -> "GLint /* | GLboolean */"

                ptype.startsWith("Int32Array | ")
                -> "Int32Array /* ${ptype.removePrefix("Int32Array")} */"

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

private fun merge(
    source: List<ConversionResult>,
): Sequence<ConversionResult> =
    source.groupBy { it.name }
        .values
        .asSequence()
        .map { items ->
            when (items.size) {
                1 -> items.single()
                2 -> {
                    val (ib, cb) = items.map { it.body }
                    var body = ib.replaceFirst(" interface ", " class ")
                    if (cb.isNotEmpty())
                        body = body.replaceFirst("\n}", "\n$cb\n}")

                    ConversionResult(
                        name = items.first().name,
                        body = body,
                    )
                }

                else -> TODO()
            }
        }
