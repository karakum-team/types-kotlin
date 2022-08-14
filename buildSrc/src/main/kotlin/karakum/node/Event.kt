package karakum.node

import karakum.common.UnionConstant
import karakum.common.objectUnionBody
import java.io.File

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
): String =
    name.replace(".", "__")
