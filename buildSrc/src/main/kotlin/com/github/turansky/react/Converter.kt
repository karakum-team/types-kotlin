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

    return reactContent.splitToSequence("\ninterface ")
        .drop(1)
        .map { it.substringBefore("\n}\n") }
        .mapNotNull {
            convertInterface(
                name = it.substringBefore(" ")
                    .substringBefore("<"),
                source = it,
            )
        }
        .plus(convertNativeEvents(content))
}

private val MISSED_NATIVE_EVENTS = setOf(
    "AnimationEvent",
    "ClipboardEvent",
    "DragEvent",
    "TouchEvent",
    "PointerEvent",
    "TransitionEvent",
)

private fun convertNativeEvents(
    source: String,
): ConversionResult {
    val body = source.splitToSequence("\n")
        .filter { it.startsWith("type Native") }
        .joinToString("\n\n") {
            val name = it.removePrefix("type ")
                .substringBefore(" = ")

            val alias = it.substringAfter(" = ")
                .removeSuffix(";")
                .takeIf { it !in MISSED_NATIVE_EVENTS }
                ?: "Event"

            "typealias $name = org.w3c.dom.events.$alias"
        }


    return ConversionResult("NativeEvents", body)
}

private fun convertInterface(
    name: String,
    source: String,
): ConversionResult? =
    when {
        name.endsWith("Event") -> convertEventInterface(name, source)
        name.endsWith("Attributes") -> convertAttributesInterface(name, source)
        name == "ReactHTML" -> convertIntrinsicTypes(source)
        else -> null
    }

private fun convertEventInterface(
    name: String,
    source: String,
): ConversionResult {
    println(source)

    return ConversionResult(name, "external interface $name")
}

private fun convertAttributesInterface(
    name: String,
    source: String,
): ConversionResult {
    var declaration = source.substringBefore(" {")
        .replace(" extends ", " : ")

    if (name == "DOMAttributes")
        declaration += ": react.PropsWithChildren"

    return ConversionResult(name, "external interface $declaration")
}

private fun convertIntrinsicTypes(
    source: String,
): ConversionResult {
    val body = source.substringAfter("{\n")
        .trimIndent()
        .removeSuffix(";")
        .splitToSequence(";\n")
        .map { convertIntrinsicType(it) }
        .joinToString("\n\n")

    return ConversionResult("IntrinsicTypes", "import react.IntrinsicType\n\n" + body)
}

private fun convertIntrinsicType(
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

private fun props(propsName: String): String =
    "external interface $propsName: react.RProps"
