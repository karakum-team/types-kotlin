package karakum.node

import karakum.common.UnionConstant
import karakum.common.objectUnionBody
import java.io.File

private val CAMEL_REGEX = Regex("""([a-z])([A-Z])""")

internal const val EVENT: String = "Event"

internal fun Event(
    definitionsDir: File,
): ConversionResult {
    val eventNames = definitionsDir
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

    val body = objectUnionBody(
        name = EVENT,
        constants = eventNames.map { name ->
            val eventName = eventName(name)
            UnionConstant(
                kotlinName = eventName,
                jsName = eventName,
                value = name,
            )
        }
    )

    return ConversionResult(
        name = EVENT,
        body = body,
    )
}

internal fun eventName(
    name: String,
): String {
    if (name == "messageerror")
        return eventName("message_error")

    return name.replace(".", "__")
        .replace("OCSP", "OCSP_")
        .replace(CAMEL_REGEX, "$1_$2")
        .toUpperCase()
}
