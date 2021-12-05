package com.github.turansky.react

private val TYPE_CONTAINER_IMPORTS = """
import org.w3c.css.masking.*
import org.w3c.dom.*    
import org.w3c.dom.svg.*    
import react.IntrinsicType    
""".trimIndent()

internal fun convertInterface(
    name: String,
    source: String,
): Sequence<ConversionResult> {
    val typeConverter = SimpleTypeConverter(name)
    val result = when {
        name == "AllHTMLAttributes" -> null

        name.endsWith("Event") -> convertEventInterface(name, source, typeConverter)
        name.endsWith("Attributes") -> convertAttributesInterface(name, source, typeConverter)

        name == "ReactHTML" -> convertIntrinsicTypes(name, source, ::convertHtmlType)
        name == "ReactSVG" -> convertIntrinsicTypes(name, source, ::convertSvgType)

        name == "AbstractView" -> convertInterface(name, source, typeConverter)
        name == "StyleMedia" -> convertInterface(name, source, typeConverter)
        name == "DangerouslySetInnerHTML" -> convertInterface(name, source, typeConverter)

        else -> null
    }

    result ?: return emptySequence()

    return sequenceOf(result) + typeConverter.unions
}

private fun convertInterface(
    name: String,
    source: String,
    typeConverter: TypeConverter,
): ConversionResult {
    val members = convertMembers(source, !name.endsWith("HTML"), typeConverter)
    val body = "external interface $name {\n$members\n}"
    return ConversionResult(name, body)
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
        .replace("<T> extends HTMLAttributes<T>", "<T : Element> : HTMLAttributes<T>")
        .replace("<T> extends MediaHTMLAttributes<T>", "<T : Element> : MediaHTMLAttributes<T>")
        .replace(" extends ", " : ")

    when (name) {
        "DOMAttributes",
        "HTMLAttributes",
        "SVGAttributes",
        -> declaration = declaration.replaceFirst("<T>", "<T: Element>")
    }

    when (name) {
        "DOMAttributes",
        -> declaration += ":\n" +
                "react.PropsWithRef<T>,\n" +
                "react.PropsWithChildren,\n" +
                "react.PropsWithClassName"

        "HTMLAttributes",
        "SVGAttributes",
        -> declaration = declaration
            .replaceFirst("> : ", ">:\n")
            .replaceFirst(",", ",\n") +
                ",\nreact.PropsWithStyle"
    }

    val content = when (name) {
        "HTMLAttributes" -> source
            .replaceFirst("\n    color?: string | undefined;", "")

        "DOMAttributes" -> source
            .replaceFirst("children?: ReactNode | undefined;\n", "")
            .replaceFirst("{\n        __html: string;\n    }", "DangerouslySetInnerHTML")

        else -> source
    }

    var members = convertMembers(content, false, typeConverter)

    when (name) {
        "HTMLAttributes",
        "SVGAttributes",
        -> members = members.replaceFirst("var style: ", "override var style: ")
    }

    when (name) {
        "InputHTMLAttributes",
        "TextareaHTMLAttributes",
        -> members = members.replaceFirst("var placeholder: ", "override var placeholder: ")

        "VideoHTMLAttributes",
        -> members = members.replaceFirst("var playsInline: ", "override var playsInline: ")
    }

    val body = if (name == "AriaAttributes") {
        "external interface $name\n\n" +
                members.replace("var ", "var $name.")
    } else {
        var result = "import org.w3c.dom.Element\n" +
                "import react.dom.events.*\n\n" +
                "external interface $declaration {\n" +
                members +
                "\n}\n"

        if (name == "DOMAttributes")
            result = result.replaceFirst("var onChange:", "    // var onChange:") +
                    // language=Kotlin
                    """
                    inline var <T : Element> DOMAttributes<T>.onChange: FormEventHandler<T>?
                        get() = asDynamic().onChange
                        set(value) {
                            asDynamic().onChange = value
                        }
                    """

        result
    }

    return ConversionResult(name, body)
}

private fun convertIntrinsicTypes(
    name: String,
    source: String,
    convert: (String) -> String,
): ConversionResult {
    val content = source.substringAfter("{\n")
        .trimIndent()
        .removeSuffix(";")
        .splitToSequence(";\n")
        .map(convert)
        .joinToString(
            separator = "\n\n",
            prefix = "object $name {\n",
            postfix = "\n}\n"
        )

    val body = sequenceOf(TYPE_CONTAINER_IMPORTS)
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
        .replaceFirst("<", "<")
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

    var elementType = source
        .substringAfter(": React.SVGProps<")
        .substringBefore(">")

    if (elementType.startsWith("SVGFE") || elementType == "SVGFilterElement")
        elementType = "SVGElement /* $elementType */"

    val type = "IntrinsicType<SVGAttributes<$elementType>>"

    return """
        inline val $name: $type
            get() = "$name".unsafeCast<$type>()
    """.trimIndent()
}
