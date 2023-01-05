package karakum.cesium

internal const val CONSTRUCTOR_OPTIONS: String = "ConstructorOptions"

internal class Constructor(
    override val source: Definition,
) : Member() {
    override val name: String
        get() = TODO()

    override val static: Boolean = false

    val parameters = source.body
        .splitToSequence(", ")
        .filter { it.isNotEmpty() }
        .map(::Parameter)
        .toList()

    val hasOptions: Boolean by lazy {
        val p = parameters.lastOrNull()
        p != null && p.name == "options" && p.type.endsWith(CONSTRUCTOR_OPTIONS)
    }

    val hiddenOptions: Boolean by lazy {
        hasHiddenOptions()
    }

    val hasParameters: Boolean by lazy {
        parameters.size > 1 || (parameters.size == 1 && !hiddenOptions)
    }

    override fun toCode(): String =
        parameters
            .dropLast(if (hiddenOptions) 1 else 0)
            .toCode()
            .takeIf { it.isNotEmpty() }
            ?.let { "constructor$it" }
            ?: ""

    fun toExtensionCode(): String {
        if (LAZY_MODE)
            return ""

        val type = parent.name
        if (type == "ModelFeature")
            return ""

        if (hiddenOptions) {
            if (parameters.size != 1)
                return ""

            // language=Kotlin
            return """
                inline fun $type(
                    block: $type.() -> Unit
                ): $type =
                    $type().apply(block)
            """.trimIndent()
        }

        if (!hasOptions) return ""

        val optionsType = "$type.$CONSTRUCTOR_OPTIONS"
        val params = parameters.dropLast(1)
            .joinToString("") { it.toCode() + ",\n" }
        val args = parameters.joinToString(", ") {
            var result = it.name
            if (result == "options")
                result += " = jso(block)"
            result
        }

        // language=Kotlin
        return """
            inline fun $type(
                $params block: $optionsType.() -> Unit,
            ): $type =
                $type($args)
        """.trimIndent()
    }

    private companion object {
        fun Constructor.hasHiddenOptions(): Boolean {
            parameters.lastOrNull()
                ?.takeIf { it.name == "options" }
                ?.takeIf { it.optional }
                ?.takeIf { it.type.endsWith(CONSTRUCTOR_OPTIONS) }
                ?: return false

            val klass = parent as Class

            val options = sequenceOf(klass, klass.companion)
                .filterNotNull()
                .flatMap { it.members.asSequence() }
                .filterIsInstance<SimpleType>()
                .single { it.name == CONSTRUCTOR_OPTIONS }

            val mutablePropertyNames = klass.members
                .asSequence()
                .filterIsInstance<Property>()
                .filterNot { it.readOnly }
                .map { it.name }
                .toSet()

            return options.parameterNames
                .all { it in mutablePropertyNames }
        }
    }
}
