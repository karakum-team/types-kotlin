package karakum.webrtc

internal data class ConversionResult(
    val name: String,
    val body: String,
)

internal fun convertDefinitions(
    source: String,
): Sequence<ConversionResult> =
    source
        .splitToSequence("\ninterface ")
        .drop(1)
        .filter { it.startsWith("RTC") }
        .map { convertInterface(it) }
        .filter { !it.name.endsWith("EventMap") }

private fun convertInterface(
    source: String,
): ConversionResult {
    val name = source.substringBefore(" ")
        .substringBefore("<")
        .substringBefore("(")
        .substringBefore(":")

    var declaration = source.substringBefore(" {\n")
        .replace(" extends DOMException", " /* : DOMException */")
        .replace(" extends EventInit", " : web.events.EventInit")
        .replace(" extends Event", " : web.events.Event")
        .replace(" extends ", " : ")

    if (name.endsWith("Event") && !declaration.endsWith(" : web.events.Event"))
        declaration += " : web.events.Event"

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

    if (declaration.endsWith(" : web.events.Event"))
        body += "\ncompanion object"

    val type = when {
        name == "RTCError"
        -> "class"

        declaration.endsWith(" : web.events.Event")
        -> "class"

        declaration.endsWith(" : web.events.EventTarget")
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

        body += "\n\n"
        // language=Kotlin
        body += """
        companion object {
            val defaultIceServers: ReadonlyArray<RTCIceServer>

            // Extension: https://www.w3.org/TR/webrtc/#sec.cert-mgmt
            fun generateCertificate(
                keygenAlgorithm: String /* AlgorithmIdentifier */,
            ): kotlin.js.Promise<RTCCertificate>;
        }
        """.trimIndent()

    }

    return ConversionResult(
        name = name,
        body = "external $type $declaration {\n$body\n}",
    )
}
