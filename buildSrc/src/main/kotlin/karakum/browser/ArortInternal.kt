package karakum.browser

// language=kotlin
private val AWAIT_INTERNAL_BODY: String = """
internal fun patchAbortOptions(
    options: Any?,
    controller: Any,
): Any = TODO("--placeholder--")

@Suppress("RedundantSuppressModifier")
internal suspend fun awaitPromiseLike(
    promise: Any?,
    controller: Any?,
): Any = TODO("--placeholder--")
""".trimIndent()

internal fun abortInternal(): ConversionResult =
    ConversionResult(
        name = "await",
        body = AWAIT_INTERNAL_BODY,
        pkg = "web.abort.internal",
    )
