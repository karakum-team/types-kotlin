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
            .forEach {
                val parentType = when (it.substringAfterLast(".")) {
                    "kind" -> "SyntaxKind"
                    "parent" -> "Node"
                    else -> null
                }

                map[it] = listOfNotNull(parentType)
            }
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

            val finalName = if ("." in name) name.replace(".", "_") else "${name}_"
            var declaration = "sealed interface $finalName"
            if (parentTypes.isNotEmpty())
                declaration += ": $parentTypes"
            declaration
        }

    val body = "sealed external interface Union {\n\n$types\n\n}"

    return ConversionResult("Union", body)
}
