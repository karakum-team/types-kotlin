package karakum.browser

internal data class EventProperty(
    val name: String,
    val type: String,
)

private val IMPORT_MAP = mapOf(
    "HighResTimeStamp" to "kotlinx.js.HighResTimeStamp",
)

internal fun event(
    name: String,
    vararg properties: EventProperty,
): ConversionResult {
    val imports = properties
        .map { it.type }
        .mapNotNull(IMPORT_MAP::get)
        .plus("org.w3c.dom.EventInit")
        .joinToString("\n") {
            "import $it"
        }

    val initName = "${name}Init"
    val initProperties = properties.joinToString("\n") { (name, type) ->
        "var $name: $type?"
    }

    val initBody = """
        external interface $initName : EventInit {
            $initProperties
        }
    """.trimIndent()

    val regularProperties = properties.joinToString("\n") { (name, type) ->
        "val $name: $type"
    }

    val body = """
        external class $name(
            type: String,
            init: $initName = definedExternally,
        ) : Event {
            $regularProperties

            companion object
        }
    """.trimIndent()

    return ConversionResult(
        name = name,
        body = sequenceOf(imports, initBody, body)
            .joinToString("\n"),
    )
}
