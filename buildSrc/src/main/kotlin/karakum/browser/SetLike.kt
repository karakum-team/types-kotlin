package karakum.browser

private val MUTABLE_SET_LIKE_OVERRIDES = """
override val size: Int

override fun add(value: -T-)
override fun clear()
override fun delete(value: -T-): Boolean
override fun entries(): IterableIterator<JsTuple2<-T-, -T->>
override fun forEach(action: (value: -T-) -> Unit)
override fun has(value: -T-): Boolean
override fun keys(): IterableIterator<-T->
override fun values(): IterableIterator<-T->
""".trimIndent()

internal fun mutableSetLikeOverrides(
    typeParameter: String,
): String =
    MUTABLE_SET_LIKE_OVERRIDES
        .replace("-T-", typeParameter)
