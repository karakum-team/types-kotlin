package karakum.node

import karakum.common.UnionConstant
import karakum.common.objectUnionBody
import java.io.File

private val CAMEL_REGEX = Regex("""([a-z])([A-Z])""")

internal const val EVENT: String = "Event"

internal fun Event(
    definitionsDir: File,
): ConversionResult {
    val eventNames = getEventNames(definitionsDir)
        .filter { "." !in it }

    return Event(EVENT, eventNames)
}

internal fun inspectorEvents(
    definitionsDir: File,
): Sequence<ConversionResult> =
    getEventNames(definitionsDir)
        .filter { "." in it }
        .groupBy { it.substringBefore(".") }
        .map { (groupName, eventNames) ->
            Event(
                name = groupName + "Event",
                eventNames = eventNames,
            )
        }
        .asSequence()

private fun getEventNames(
    definitionsDir: File,
): List<String> =
    definitionsDir
        .listFiles { file -> file.name.endsWith(".d.ts") }!!
        .asSequence()
        .flatMap { it.readLines() }
        .map { it.trim() }
        .filter { it.startsWith("on(") }
        .map { it.removePrefix("on(event: ") }
        .filter { it.startsWith("'") }
        .map { it.removePrefix("'").substringBefore("'") }
        .distinct()
        .sorted()
        .toList()

private fun Event(
    name: String,
    eventNames: List<String>,
): ConversionResult {
    val body = objectUnionBody(
        name = name,
        constants = eventNames.map { value ->
            val eventName = eventName(value.substringAfter("."))
            UnionConstant(
                kotlinName = eventName,
                jsName = eventName,
                value = value,
            )
        }
    )

    return ConversionResult(
        name = name,
        body = body,
    )
}

internal fun eventName(
    name: String,
): String {
    if (name == "messageerror")
        return eventName("message_error")

    return name
        .replace("OCSP", "OCSP_")
        .replace(CAMEL_REGEX, "$1_$2")
        .toUpperCase()
}
