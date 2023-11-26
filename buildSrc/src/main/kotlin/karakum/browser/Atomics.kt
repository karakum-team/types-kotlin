package karakum.browser

import java.io.File

internal fun atomicsDeclarations(
    definitionsDir: File,
): ConversionResult {
    val source = atomicsContent(definitionsDir)

    val body = """
    external object Atomics {
        $source
    }    
    """.trimIndent()

    return ConversionResult(
        name = "Atomics",
        body = body,
        pkg = "js.atomics",
    )
}

private fun atomicsContent(
    definitionsDir: File,
): String =
    definitionsDir.listFiles()!!
        .filter { it.name.endsWith(".sharedmemory.d.ts") }
        .sortedBy { file -> file.name }
        .map {
            it.readText()
                .replace("\r\n", "\n")
                .substringAfter("\ninterface Atomics {\n")
                .substringBefore("\n}")
                .trimIndent()
        }
        .joinToString("\n")
