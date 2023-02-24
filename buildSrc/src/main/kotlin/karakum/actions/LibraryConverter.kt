package karakum.actions

import karakum.common.ConversionResult
import java.io.File

internal class LibraryConversionResult(
    val library: Library,
    val results: List<ConversionResult>,
    private val pathMap: Map<String, String>,
) {
    fun getPath(name: String): String? =
        when (val path = pathMap[name]) {
            "lib/auth",
            "lib/proxy",
            -> path

            else -> null
        }
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

    val pathMap = mutableMapOf<String, String>()

    var results = files.asSequence()
        .flatMap { file ->
            val filePath = file.toRelativeString(definitionsDir)
                .removeSuffix(".d.ts")

            convert(file.readText())
                .onEach { pathMap[it.name] = filePath }
        }
        .toList()
        .mergeDuplicated()
        .removeDuplicatedInterfaces()

    if (library.name == "cache")
        results += TransferProgressEvent()

    return LibraryConversionResult(
        library = library,
        results = results,
        pathMap = pathMap,
    )
}
