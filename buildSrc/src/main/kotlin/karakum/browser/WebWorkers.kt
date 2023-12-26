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

private val WORKER_TYPES = listOf(
    "DedicatedWorkerGlobalScope",
    "WorkerGlobalScope",
    "WorkerLocation",
    "WorkerNavigator",
)

private val SERVICE_WORKER_TYPES = listOf(
    "Client",
    "Clients",
    "ClientQueryOptions",

    "ServiceWorkerGlobalScope",

    "WindowClient",
)

private val PUSH_TYPES = listOf(
    "PushMessageData",
    "PushMessageDataInit",
)

private val PKG_MAP = mapOf(
    "PushMessageDataInit" to "web.push",
    "FrameType" to "web.serviceworker",
)

internal fun serviceWorkersDeclarations(
    definitionsFile: File,
): Sequence<ConversionResult> {
    val content = serviceWorkersContent(definitionsFile)
    return workersDeclarations(content)
}

private fun workersDeclarations(
    content: String,
): Sequence<ConversionResult> {
    val interfaces = Regex("""interface .+? \{[\s\S]+?\n}""")
        .findAll(content)
        .map { it.value }
        .mapNotNull { source ->
            val name = source
                .substringAfter(" ")
                .substringBefore(" ")

            val predefinedPkg = when (name) {
                in WORKER_TYPES -> "web.workers"

                in PUSH_TYPES -> "web.push"
                in SERVICE_WORKER_TYPES -> "web.serviceworker"

                else -> return@mapNotNull null
            }

            convertInterface(
                source = source,
                getStaticSource = { getStaticSource(it, content) },
                predefinedPkg = predefinedPkg,
            )
        }

    val types = convertTypes(
        content = content,
        getPkg = PKG_MAP::get,
    ).filter { it.name in PKG_MAP.keys }

    return interfaces + types
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
