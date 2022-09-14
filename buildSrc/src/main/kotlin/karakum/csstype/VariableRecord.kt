package karakum.csstype

internal const val VARIABLE_RECORD = "VariableRecord"

// language=Kotlin
private val BODY = """
sealed external interface $VARIABLE_RECORD

inline fun <T: Any> $VARIABLE_RECORD.get(
    name: $VARIABLE<T>,
): T? =
    asDynamic()[name]

inline fun <T: Any> $VARIABLE_RECORD.set(
    name: $VARIABLE<T>,
    value: T?,
) {
    asDynamic()[name] = value
}
""".trimIndent()

internal fun VariableRecord(): ConversionResult =
    ConversionResult(VARIABLE_RECORD, BODY)
