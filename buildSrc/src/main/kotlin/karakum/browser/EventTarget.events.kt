package karakum.browser

import karakum.common.snakeToCamel
import karakum.events.EventDataRegistry

internal fun List<ConversionResult>.withEventInstances(
    knownEventTypes: Set<String>,
): List<ConversionResult> {
    val declarations = toList()

    val events = declarations.mapNotNull {
        val name = it.name
        val dataList = EventDataRegistry.getDataList(name)
            ?.filter { it.type in knownEventTypes }
            ?.ifEmpty { null }
            ?: return@mapNotNull null

        val body = dataList.joinToString("\n\n") { data ->
            val memberName = EVENT_CORRECTION_MAP
                .getOrDefault(data.type, data.type)
                .snakeToCamel()

            val targetType = EventDataRegistry.getTarget(
                currentTarget = name,
                eventType = data.type,
                defaultTarget = "C",
            )

            val eventClass = data.`interface`
            val eventType = when (eventClass) {
                "MessageEvent" -> "$eventClass<Any?>"
                else -> eventClass
            }

            val eventTypeDeclaration = """$eventClass.$memberName()"""
            val currentTargetBound = when (name) {
                "IDBRequest" -> "$name<*>"
                else -> name
            }

            """
            inline val <C: $currentTargetBound> C.${memberName}Event: $EVENT_INSTANCE<$eventType, C, $targetType>
                get() = $EVENT_INSTANCE(this, $eventTypeDeclaration)
            """.trimIndent()
        }

        ConversionResult(
            name = "$name.events",
            body = body,
            pkg = it.pkg,
        )
    }

    return declarations + events
}
