package karakum.cesium

internal class Parameter(
    body: String,
) {
    val name: String = body.substringBefore(": ")
        .removePrefix("...")
        .removeSuffix("?")
        .let { if (it == "object") "obj" else it }

    private val vararg: Boolean by lazy { "..." in body.substringBefore("{") }

    val type: String by lazy {
        val source = body.substringAfter(": ")
            .let { if (vararg) it.removeSuffix("[]") else it }

        kotlinType(source, name)
    }

    val optional: Boolean by lazy { "?:" in body.substringBefore("{") }
    private val nullable: Boolean by lazy { optional && !type.startsWith("dynamic") }

    var supportDefault: Boolean = true

    fun toCode(): String =
        (if (vararg) "vararg " else "") +
                " $name: $type" +
                (if (nullable) "?" else "") +
                (if (optional && supportDefault) " = definedExternally" else "")
}

internal fun List<Parameter>.toCode(): String =
    when (size) {
        0 -> "()"
        1 -> "(${single().toCode()})"
        else -> {
            val params = joinToString(",\n") {
                it.toCode()
            }

            "(\n$params\n)"
        }
    }
