package karakum.browser

internal const val HAS_TARGETS = "HasTargets"

private val HAS_TARGETS_BODY: String = """
external interface $HAS_TARGETS<C : EventTarget> {
    val currentTarget: C
    val target: EventTarget
}
""".trimIndent()

internal fun HasTargets(): ConversionResult =
    ConversionResult(
        name = HAS_TARGETS,
        body = HAS_TARGETS_BODY,
        pkg = "web.events",
    )
