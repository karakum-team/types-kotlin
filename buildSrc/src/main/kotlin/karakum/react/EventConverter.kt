package karakum.react

import karakum.browser.EVENT_TYPE_MAP
internal fun convertNativeEvents(
    source: String,
): ConversionResult {
    val body = source.splitToSequence("\n")
        .filter { it.startsWith("type Native") }
        .joinToString("\n\n", postfix = "\n") { line ->
            val name = line.removePrefix("type ")
                .substringBefore(" = ")

            val alias = line.substringAfter(" = ")
                .removeSuffix(";")
                .let { EVENT_TYPE_MAP.getValue(it) }

            "typealias $name = $alias"
        }

    return ConversionResult("NativeEvents", body, Package.EVENTS)
}

private const val EVENT_HANDLER = "typealias EventHandler<E> = (event: E) -> Unit"

internal fun convertEventHandlers(
    source: String,
): ConversionResult {
    val handlers = source.splitToSequence("\n")
        .filter { it.startsWith("type ") && " = EventHandler<" in it }
        .joinToString("\n\n") { line ->
            line.replaceFirst("type ", "typealias ")
                .removeSuffix(";")
                .replace("<T = Element>", "<T>")
                .replace("SyntheticEvent<T>", "SyntheticEvent<T, *>")
                .replace("MouseEvent<T>", "MouseEvent<T, *>")
                .replace("UIEvent<T>", "UIEvent<T, *>")
        }

    val body = EVENT_HANDLER + "\n\n" + handlers

    return ConversionResult("EventHandlers", body, Package.EVENTS)
}

private const val DEFAULT_EVENT_IMPORTS = """
import org.w3c.dom.Element
import web.events.Event
import web.events.EventTarget
import react.dom.AbstractView
"""

internal fun convertEventInterface(
    name: String,
    source: String,
    typeConverter: TypeConverter,
): ConversionResult {
    val declaration = source.substringBefore(" {")
        .replaceFirst(" extends ", " : ")
        .replace(" = ", " : ")
        .replace(": object", ": Any")
        .replace(": any", ": Any")
        .replace("EventTarget & T", "T")
        .replace("SyntheticEvent<T>", "SyntheticEvent<T, Event>")
        .replace("E :", "out E :")
        .replace("C :", "out C :")
        .replace("T :", "out T :")

    var members = convertMembers(source, true, typeConverter)
    when (name) {
        "ChangeEvent",
        "FocusEvent",
        "InvalidEvent",
        -> members = members.replaceFirst("val target:", "override val target:")
    }

    val body = DEFAULT_EVENT_IMPORTS.removePrefix("\n") +
            "\nexternal interface $declaration {\n" +
            members +
            "\n}\n"

    return ConversionResult(name, body, Package.EVENTS)
}
