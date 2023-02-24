package karakum.actions

import karakum.common.ConversionResult
import java.io.File

internal class LibraryConversionResult(
    val library: Library,
    val results: List<ConversionResult>,
    private val pathMap: Map<String, String>,
) {
    fun getPath(name: String): String? = null
}

internal fun convertLibrary(
    definitionsDir: File,
): LibraryConversionResult {
    val library = Library(definitionsDir.name)
    val files = sequenceOf("lib", "lib/internal")
        .map { definitionsDir.resolve(it) }
        .filter { it.exists() }
        .mapNotNull { it.listFiles { file -> file.name.endsWith(".d.ts") } }
        .flatMap { it.asSequence() }
        .filter { it.name != "io-util.d.ts" }
        .toList()

    var results = files.asSequence()
        .flatMap { convert(it.readText()) }
        .toList()
        .mergeDuplicated()
        .removeDuplicatedInterfaces()

    if (library.name == "cache")
        results += TransferProgressEvent()

    return LibraryConversionResult(
        library = library,
        results = results,
        pathMap = emptyMap()
    )
}
