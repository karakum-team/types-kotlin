package karakum.webrtc

import karakum.common.unionBody

internal data class ConversionResult(
    val name: String,
    val body: String,
)

internal fun convertDefinitions(
    source: String,
): Sequence<ConversionResult> {
    val interfaces = source
        .splitToSequence("\ninterface ")
        .drop(1)
        .map { convertInterface(it) }
        .filter { it.name != "Window" }

    val types = source
        .splitToSequence("\ntype ")
        .drop(1)
        .map { it.substringBefore(";\n") }
        .map { convertType(it) }

    return interfaces + types
}

private fun convertInterface(
    source: String,
): ConversionResult {
    val name = source.substringBefore(" ")
        .substringBefore("<")
        .substringBefore("(")
        .substringBefore(":")

    var declaration = source.substringBefore(" {\n")
        .replace(" extends DOMException", " /* : DOMException */")
        .replace(" extends EventInit", " : org.w3c.dom.EventInit")
        .replace(" extends Event", " : org.w3c.dom.events.Event")
        .replace(" extends ", " : ")

    if (name.endsWith("Event") && !declaration.endsWith(" : org.w3c.dom.events.Event"))
        declaration += " : org.w3c.dom.events.Event"

    val bodySource = source.substringAfter(" {\n")
        .let { if (it.startsWith("}")) "" else it }
        .substringBefore("\n}")
        .trimIndent()

    if (bodySource.startsWith("("))
        return ConversionResult(
            name = name,
            body = "typealias $name = " +
                    bodySource
                        .removeSuffix(";")
                        .replace(": DOMException", ": Throwable")
                        .replace("): void", ") -> Unit")
        )

    var body = convertMembers(
        name = name,
        source = bodySource,
    )

    if (declaration.endsWith(" : org.w3c.dom.events.Event"))
        body += "\ncompanion object"

    val type = when {
        name == "RTCError"
        -> "class"

        declaration.endsWith(" : org.w3c.dom.events.Event")
        -> "class"

        declaration.endsWith(" : org.w3c.dom.events.EventTarget")
        -> "class"

        else -> "sealed interface"
    }

    if (name == "RTCPeerConnection") {
        declaration = declaration.replace(
            " : ", """(
            configuration: RTCConfiguration = definedExternally,
            options: Any = definedExternally,
        ) : """.trimIndent()
        )


        // language=Kotlin
        body += """
        companion object {
            val defaultIceServers: ReadonlyArray<RTCIceServer>

            // Extension: https://www.w3.org/TR/webrtc/#sec.cert-mgmt
            fun generateCertificate(keygenAlgorithm: String): kotlin.js.Promise<RTCCertificate>;
        }
        """.trimIndent()

    }

    return ConversionResult(
        name = name,
        body = "external $type $declaration {\n$body\n}",
    )
}

private fun convertType(
    source: String,
): ConversionResult {
    if (" | '" in source) {
        val (name, body) = source
            .split(" =")

        val values = body.removePrefix(" ")
            .splitToSequence(" | ", "\n    | ")
            .filter { it.isNotEmpty() }
            .map { it.removeSurrounding("'") }
            .toList()

        return ConversionResult(
            name = name,
            body = unionBody(name, values),
        )
    }

    val (declaration, bodySource) = source
        .split(" = ")

    val name = declaration.substringBefore("<")
    val body = "typealias " +
            declaration.replace("<E extends Event>", "<E>") +
            " = " +
            bodySource
                .replace("this: RTCDataChannel, ", "")
                .replace("this: RTCDtlsTransport, ", "")
                .replace("this: RTCIceTransport, ", "")
                .replace("this: RTCPeerConnection, ", "")
                .replace("ev: Event)", "ev: org.w3c.dom.events.Event)")
                .replace(") => any) | null", ") -> Nothing?)?")

    return ConversionResult(
        name = name,
        body = body,
    )
}
