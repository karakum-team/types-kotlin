package karakum.csstype

internal const val VARIABLE_RECORD = "VariableRecord"

// language=Kotlin
private val BODY = """
sealed external interface $VARIABLE_RECORD {
    @nativeGetter
    @Suppress("DEPRECATION")
    operator fun <T : Any> get(
        name: $VARIABLE<T>,
    ): T?

    @nativeSetter
    @Suppress("DEPRECATION")
    operator fun <T : Any> set(
        name: $VARIABLE<T>,
        value: T?,
    )
}
""".trimIndent()

internal fun VariableRecord(): ConversionResult =
    ConversionResult(VARIABLE_RECORD, BODY)
