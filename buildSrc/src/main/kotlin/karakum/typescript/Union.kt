package karakum.typescript

private val TYPES = RAW_UNIONS.asSequence()
    .flatMap { (name, source) ->
        source.splitToSequence(" | ")
            .map { it to name }
    }
    .fold(mutableMapOf<String, List<String>>()) { map, (name, parentName) ->
        val data = map[name] ?: emptyList()
        map[name] = data + parentName
        map
    }
    .also { map ->
        RAW_UNIONS.keys.asSequence()
            .filter { "." in it }
            .forEach { map[it] = emptyList() }
    }

internal fun hasUnionParent(name: String): Boolean =
    TYPES.containsKey(name)

internal fun union(): ConversionResult {
    val types = TYPES.asSequence()
        .sortedBy { it.key }
        .joinToString("\n\n") { (name, parentNames) ->
            val parentTypes = parentNames
                .distinct() // For `FlowNode`
                .joinToString(", ") { it.replace(".", "_") }

            var declaration = "sealed interface ${name.replace(".", "_")}"
            if (parentTypes.isNotEmpty())
                declaration += ": $parentTypes"
            declaration
        }

    val body = "sealed external interface Union {\n\n$types\n\n}"

    return ConversionResult("Union", body)
}
