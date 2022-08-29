package karakum.browser

import karakum.common.unionBody
import java.io.File

internal fun webglDeclarations(
    definitionsFile: File,
): Sequence<ConversionResult> {
    val content = definitionsFile.readText()

    val interfaces = Regex("""interface ((ANGLE_|EXT_|KHR_|OES_|OVR_|WEBGL_|WebGL).+?) \{[\s\S]+?\}""")
        .findAll(content)
        .map { it.value }
        .mapNotNull { convertInterface(it) }

    val classes = Regex("""declare var WebGL.+?: \{[\s\S]+?\}""")
        .findAll(content)
        .map { it.value }
        .mapNotNull { convertCompanion(it) }

    return merge(interfaces + classes)
        .plus(Aliases())
        .plus(BufferSource())
        .plus(Lists())
        .plus(Numbers())
        .plus(
            // TEMP
            ConversionResult(
                name = "WebGLPowerPreference",
                body = unionBody(
                    name = "WebGLPowerPreference",
                    values = listOf("default", "high-performance", "low-power"),
                ),
            )
        )
}

private fun convertInterface(
    source: String,
): ConversionResult? {
    val name = source
        .substringAfter(" ")
        .substringBefore(" ")

    if (name in OLD_WEBGL_TYPES)
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

private fun convertCompanion(
    source: String,
): ConversionResult? {
    val name = source
        .removePrefix("declare var ")
        .substringBefore(": ")

    if (name in OLD_WEBGL_TYPES)
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
                memberSource.replace("readonly ", "val ") +
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
    if ("extensionName: \"" in source)
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
    type = when (type) {
        "string" -> "String"
        "boolean" -> "Boolean"

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
    source: Sequence<ConversionResult>,
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
