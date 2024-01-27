package karakum.browser

private val TIMERS = setOf(
    "setTimeout",
    "clearTimeout",
    "setInterval",
    "clearInterval",
)

internal fun browserFunctions(
    content: String,
): Sequence<ConversionResult> =
    convertFunctions(
        content = content.replace(
            "\ndeclare function ",
            "\nfunction ",
        ),
        getPkg = ::getBrowserPkg,
    )
        .plus(
            ConversionResult(
                name = "Interval",
                body = "sealed external interface Interval",
                pkg = "web.timers",
            )
        )
        .plus(
            ConversionResult(
                name = "Timeout",
                body = "sealed external interface Timeout",
                pkg = "web.timers",
            )
        )

private fun getBrowserPkg(
    name: String,
): String? =
    when (name) {
        "structuredClone",
        -> "js.core"

        "atob",
        "btoa",
        -> "web.encoding"

        "fetch",
        -> "web.http"

        "createImageBitmap",
        -> "web.canvas"

        "matchMedia",
        -> "web.cssom"

        "getComputedStyle",
        -> "web.dom"

        "reportError",
        -> "web.errors"

        in TIMERS,
        -> "web.timers"

        "queueMicrotask",
        -> "web.scheduling"

        "alert",
        "confirm",
        "prompt",
        -> "web.prompts"

        else -> null
    }

internal fun convertFunctions(
    content: String,
    getPkg: (name: String) -> String?,
): Sequence<ConversionResult> =
    Regex("""\nfunction (.+);""")
        .findAll(content)
        .map { it.groupValues[1] }
        .mapNotNull { source ->
            val result = convertFunctionResult(source, getPkg)
            result?.copy(body = result.body.withComment(content, "function $source;"))
        }
        .groupBy { it.name }
        .values
        .asSequence()
        .map { items ->
            items.singleOrNull()
                ?: run {
                    val first = items.first()
                    ConversionResult(
                        name = first.name,
                        body = items.joinToString("\n\n") { it.body },
                        pkg = first.pkg,
                    )
                }
        }

private fun convertFunctionResult(
    source: String,
    getPkg: (name: String) -> String?,
): ConversionResult? {
    val name = source.substringBefore("(")
        .substringBefore("<")

    val pkg = getPkg(name) ?: return null

    var bodySource = source
        .substringAfter("(")
        .let { "($it" }

    if (name in TIMERS) {
        val idType = name
            .removePrefix("set")
            .removePrefix("clear")

        bodySource = bodySource
            .replace("timeout?: number, ...arguments: any[]", "timeout: Int = definedExternally")
            .replace("id: number | undefined", "id: $idType?")
            .replace("): number", "): $idType")
    }

    bodySource = bodySource
        // reportError
        .replace("(e: any", "(error: JsError")
        // alert
        .replace("message?: any", "message: String")
        .replace("message?: ", "message: ")
        .replace("_default?: ", "default?: ")
        .replace("?: Imports", ": Imports = definedExternally")
        .replace("elt: Element", "element: Element")
        .replace("pseudoElt?: ", "pseudoElement?: ")

        .replace(": boolean", ": Boolean")
        .replace("?: string | null", "?: string")
        // TEMP for getComputedStyle
        .replace("pseudoElement?: string", "pseudoElement: String? = definedExternally")
        .replace("init?: RequestInit", "init: RequestInit? = definedExternally")
        .replace("options?: ImageBitmapOptions", "options: ImageBitmapOptions? = definedExternally")
        .replace("options?: StructuredSerializeOptions", "options: StructuredSerializeOptions? = definedExternally")
        .replace("?: string", ": String = definedExternally")
        .replace(": string", ": String")
        .replace(": number", ": Int")
        .replace("): void", ")")
        .replace(" | null", "?")
        .replace(", ", ",\n")
        .replace("(", "(\n")
        .replace(")", "\n)")

    if (name == "matchMedia")
        bodySource = bodySource.applyMediaQueryFunctionPatch()

    val finalName = when (name) {
        "fetch" -> "${name}Async"
        else -> name
    }

    val typeParameters = source.substringBefore("(")
        .removePrefix(name)
        .replace(" = any", "")

    val jsName = if (finalName != name) {
        """@JsName("$name")"""
    } else null

    var body = sequenceOf(
        jsName,
        "external fun $typeParameters $finalName $bodySource",
    ).filterNotNull()
        .joinToString("\n")

    if (name in TIMERS && name.startsWith("set")) {
        val idType = name.removePrefix("set")

        body += "\n\n"

        // language=kotlin
        body += """
        fun $name(
            timeout: Duration,
            handler: TimerHandler,
        ): $idType =
            $name(
                handler = handler,
                timeout = timeout.toInt(MILLISECONDS)
            )
        """.trimIndent()
    }

    return ConversionResult(
        name = finalName,
        body = body,
        pkg = pkg
    )
}
