package karakum.browser

import java.io.File
import java.net.URL

private val WEB_WORKER_URL =
    URL("https://raw.githubusercontent.com/microsoft/TypeScript-DOM-lib-generator/main/baselines/webworker.generated.d.ts")

internal val WEB_WORKER_CONTENT by lazy {
    WEB_WORKER_URL.openStream()
        .use { stream -> String(stream.readAllBytes()) }
        .applyTempEventPatches()
}

private val WORKER_TYPES = setOf(
    "DedicatedWorkerGlobalScope",
    "WorkerGlobalScope",
    "WorkerLocation",
    "WorkerNavigator",
)

private val SERVICE_WORKER_TYPES = setOf(
    "Client",
    "Clients",
    "ClientQueryOptions",

    "ServiceWorkerGlobalScope",

    "WindowClient",
)

private val PUSH_TYPES = setOf(
    "PushMessageData",
    "PushMessageDataInit",
)

private val RTC_TYPES = setOf(
    "RTCRtpScriptTransformer",
)

private val WEB_WORKER_TYPES = RTC_TYPES
    .plus("DedicatedWorkerGlobalScope")

private val PKG_MAP = mapOf(
    "PushMessageDataInit" to "web.push",
    "FrameType" to "web.serviceworker",
)

internal fun webWorkersDeclarations(): Sequence<ConversionResult> {
    return workersDeclarations(WEB_WORKER_CONTENT, typeFilter = { it in WEB_WORKER_TYPES })
}

internal fun serviceWorkersDeclarations(
    definitionsFile: File,
): Sequence<ConversionResult> {
    val content = serviceWorkersContent(definitionsFile)
    val interfaces = workersDeclarations(content, typeFilter = { it !in WEB_WORKER_TYPES })

    val types = convertTypes(
        content = content,
        getPkg = PKG_MAP::get,
    ).filter { it.name in PKG_MAP.keys }

    return interfaces + types
}

private fun workersDeclarations(
    content: String,
    typeFilter: (type: String) -> Boolean,
): Sequence<ConversionResult> =
    Regex("""interface .+? \{[\s\S]+?\n}""")
        .findAll(content)
        .map { it.value }
        .mapNotNull { source ->
            val name = source
                .substringAfter(" ")
                .substringBefore(" ")

            if (!typeFilter(name))
                return@mapNotNull null

            val predefinedPkg = when (name) {
                in WORKER_TYPES -> "web.workers"

                in PUSH_TYPES -> "web.push"
                in RTC_TYPES -> "web.rtc"
                in SERVICE_WORKER_TYPES -> "web.serviceworker"

                else -> return@mapNotNull null
            }

            convertInterface(
                source = source,
                getStaticSource = { getStaticSource(it, content) },
                predefinedPkg = predefinedPkg,
            )
        }

internal fun serviceWorkersContent(
    definitionsFile: File,
): String =
    definitionsFile
        .readText()
        .replace(", WindowOrWorkerGlobalScope", "")
        .replace(
            """ReadonlyArray<T["type"] extends "window" ? WindowClient : Client>""",
            "ReadonlyArray<Client /* | WindowClient */>"
        )
        .splitUnion("string | string[]")
        .splitUnion("string | URL")
        .splitUnion("Response | PromiseLike<Response>")
