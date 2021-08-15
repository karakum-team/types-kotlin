package com.github.turansky.react

private const val INTRINSIC_TYPE_IMPORT = "import react.IntrinsicType"

private const val SVG_TYPE = "DefaultSvgType"
private const val SVG_TYPE_DECLARATION = "typealias $SVG_TYPE = IntrinsicType<SVGAttributes<org.w3c.dom.svg.SVGElement>>"

internal fun convertInterface(
    name: String,
    source: String,
): Sequence<ConversionResult> {
    val typeConverter = SimpleTypeConverter(name)
    val result = when {
        name.endsWith("Event") -> convertEventInterface(name, source, typeConverter)
        name.endsWith("Attributes") -> convertAttributesInterface(name, source, typeConverter)
        name == "ReactHTML" -> convertIntrinsicTypes("ReactHTML", source, ::convertHtmlType)
        name == "ReactSVG" -> convertIntrinsicTypes("ReactSVG", source, ::convertSvgType, SVG_TYPE_DECLARATION)
        else -> null
    }

    result ?: return emptySequence()

    return sequenceOf(result) + typeConverter.unions
}

private fun convertAttributesInterface(
    name: String,
    source: String,
    typeConverter: TypeConverter,
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

    var members = convertMembers(content, false, typeConverter)

    members = when (name) {
        "AllHTMLAttributes",
        "InputHTMLAttributes",
        "TextareaHTMLAttributes",
        -> members.replaceFirst("var placeholder: ", "override var placeholder: ")

        "VideoHTMLAttributes",
        -> members.replaceFirst("var playsInline: ", "override var playsInline: ")

        else -> members
    }

    val body = if (name == "AriaAttributes") {
        val adapterName = "${name}LegacyAdapter"
        "external interface $name: $adapterName\n\n" +
                "sealed interface $adapterName {\n" +
                members +
                "\n}\n"
    } else {
        "import org.w3c.dom.Element\n\n" +
                "external interface $declaration {\n" +
                members +
                "\n}\n"
    }

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
