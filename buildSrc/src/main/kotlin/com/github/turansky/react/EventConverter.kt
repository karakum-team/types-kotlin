package com.github.turansky.react

private val NATIVE_EVENT_REPLACEMENT = mapOf(
    "AnimationEvent" to "Event",
    "ClipboardEvent" to "Event",
    "DragEvent" to "MouseEvent",
    "TouchEvent" to "MouseEvent",
    "PointerEvent" to "MouseEvent",
    "TransitionEvent" to "Event",
)

internal fun convertNativeEvents(
    source: String,
): ConversionResult {
    val body = source.splitToSequence("\n")
        .filter { it.startsWith("type Native") }
        .joinToString("\n\n") { line ->
            val name = line.removePrefix("type ")
                .substringBefore(" = ")

            val alias = line.substringAfter(" = ")
                .removeSuffix(";")
                .let { NATIVE_EVENT_REPLACEMENT[it] ?: it }

            "typealias $name = org.w3c.dom.events.$alias"
        }


    return ConversionResult("NativeEvents", body)
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

    return ConversionResult("EventHandlers", body)
}

private const val DEFAULT_EVENT_IMPORTS = """
import org.w3c.dom.Element
import org.w3c.dom.events.Event
import org.w3c.dom.events.EventTarget
"""

internal fun convertEventInterface(
    name: String,
    source: String,
): ConversionResult {
    val declaration = source.substringBefore(" {")
        .replaceFirst(" extends ", " : ")
        .replace(" = ", " : ")
        .replace(": object", ": Any")
        .replace(": any", ": Any")
        .replace("EventTarget & T", "T")
        .replace("SyntheticEvent<T>", "SyntheticEvent<T, Event>")

    var members = convertMembers(source, true)
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

    return ConversionResult(name, body)
}
