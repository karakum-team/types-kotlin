package com.github.turansky.react

import java.io.File

internal data class ConversionResult(
    val name: String,
    val body: String,
)

internal fun convertDefinitions(
    definitionFile: File,
): Sequence<ConversionResult> {
    val content = definitionFile.readText()
        .replace("HTMLTableHeaderCellElement", "HTMLTableCellElement")
        .replace("HTMLTableDataCellElement", "HTMLTableCellElement")
        .replace("HTMLWebViewElement", "HTMLElement")
        .replace("\r\n", "\n")

    val reactContent = content
        .substringAfter("declare namespace React {\n")
        .substringBefore("\n}\n")
        .trimIndent()

    return convertInterfaces(reactContent)
        .plus(convertUnions(reactContent))
        .plus(convertNativeEvents(content))
        .plus(convertEventHandlers(reactContent))
}

private fun convertUnions(
    content: String,
): Sequence<ConversionResult> =
    content.splitToSequence("\ntype ")
        .drop(1)
        .map { it.substringBefore(";") }
        .mapNotNull {
            convertUnion(
                name = it.substringBefore(" ="),
                source = it.substringAfter(" =")
            )
        }

private fun convertInterfaces(
    content: String,
): Sequence<ConversionResult> =
    content.splitToSequence("\ninterface ")
        .drop(1)
        .map { it.substringBefore("\n}\n") }
        .mapNotNull {
            convertInterface(
                name = it.substringBefore(" ")
                    .substringBefore("<"),
                source = it,
            )
        }

private const val INTRINSIC_TYPE_IMPORT = "import react.IntrinsicType"

private const val SVG_TYPE = "DefaultSvgType"
private const val SVG_TYPE_DECLARATION = "typealias $SVG_TYPE = IntrinsicType<SVGAttributes<org.w3c.dom.svg.SVGElement>>"

private fun convertUnion(
    name: String,
    source: String,
): ConversionResult? {
    if ("<" in name)
        return null

    if (" | '" !in source)
        return null

    return ConversionResult(name, "typealias $name = String")
}

private fun convertInterface(
    name: String,
    source: String,
): ConversionResult? =
    when {
        name.endsWith("Event") -> convertEventInterface(name, source)
        name.endsWith("Attributes") -> convertAttributesInterface(name, source)
        name == "ReactHTML" -> convertIntrinsicTypes("ReactHTML", source, ::convertHtmlType)
        name == "ReactSVG" -> convertIntrinsicTypes("ReactSVG", source, ::convertSvgType, SVG_TYPE_DECLARATION)
        else -> null
    }

private fun convertAttributesInterface(
    name: String,
    source: String,
): ConversionResult? {
    when (name) {
        "Attributes",
        "RefAttributes",
        "ClassAttributes",
        -> return null
    }

    var declaration = source.substringBefore(" {")
        .replace(" extends ", " : ")

    when (name) {
        "DOMAttributes",
        -> declaration += ": react.PropsWithChildren"

        "DetailsHTMLAttributes",
        "InputHTMLAttributes",
        "SelectHTMLAttributes",
        "TextareaHTMLAttributes",
        -> declaration = declaration.replaceFirst("<T>", "<T: Element>")
    }

    val content = when (name) {
        "DOMAttributes" -> source.substringAfter("} | undefined;\n\n")
        else -> source
    }

    var members = when (name) {
        // TODO: support
        "AriaAttributes" -> ""
        else -> convertMembers(content, false)
    }

    members = when (name) {
        "AllHTMLAttributes",
        "InputHTMLAttributes",
        "TextareaHTMLAttributes",
        -> members.replaceFirst("var placeholder: ", "override var placeholder: ")

        "VideoHTMLAttributes",
        -> members.replaceFirst("var playsInline: ", "override var playsInline: ")

        else -> members
    }

    val body = "import org.w3c.dom.Element\n\n" +
            "external interface $declaration {\n" +
            members +
            "\n}\n"

    return ConversionResult(name, body)
}

private fun convertIntrinsicTypes(
    name: String,
    source: String,
    convert: (String) -> String,
    vararg aliases: String,
): ConversionResult {
    val content = source.substringAfter("{\n")
        .trimIndent()
        .removeSuffix(";")
        .splitToSequence(";\n")
        .map(convert)
        .joinToString("\n\n")

    val body = sequenceOf(INTRINSIC_TYPE_IMPORT)
        .plus(aliases)
        .plus(content)
        .joinToString("\n\n")

    return ConversionResult(name, body)
}

private fun convertHtmlType(
    source: String,
): String {
    val name = source.substringBefore(": ")
        .removeSurrounding("\"")

    val propsType = source.substringAfter(": DetailedHTMLFactory<")
        .substringBefore(",")
        .replaceFirst("<", "<org.w3c.dom.")
    val type = "IntrinsicType<$propsType>"

    val id = when (name) {
        "object", "var" -> "`$name`"
        else -> name
    }

    return """
        inline val $id: $type
            get() = "$name".unsafeCast<$type>()
    """.trimIndent()
}

private fun convertSvgType(
    source: String,
): String {
    val name = source.substringBefore(": ")
        .removeSurrounding("\"")

    val type = SVG_TYPE

    return """
        inline val $name: $type
            get() = "$name".unsafeCast<$type>()
    """.trimIndent()
}
