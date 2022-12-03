package karakum.browser

import java.io.File

private val WORKER_TYPES = listOf(
    "DedicatedWorkerGlobalScope",
    "WorkerGlobalScope",
    "WorkerLocation",
    "WorkerNavigator",
)

internal fun webWorkersDeclarations(
    definitionsDir: File,
): Sequence<ConversionResult> {
    val content = webWorkersContent(definitionsDir)

    val interfaces = Regex("""interface .+? \{[\s\S]+?\n\}""")
        .findAll(content)
        .asSequence()
        .map { it.value }
        .filter {
            val name = it
                .substringAfter(" ")
                .substringBefore(" ")

            name in WORKER_TYPES
        }
        .mapNotNull {
            convertInterface(
                source = it,
                getStaticSource = { getStaticSource(it, content) },
                predefinedPkg = "web.workers",
            )
        }

    return interfaces
}

private fun webWorkersContent(
    definitionsDir: File,
): String =
    definitionsDir.resolve("lib.webworker.d.ts")
        .readText()
        .replace(", WindowOrWorkerGlobalScope", "")
        .splitUnion("string | string[]")
