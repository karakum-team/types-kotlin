package karakum.actions

import java.io.File

private class Library(
    val name: String,
) {
    val moduleId: String = "@actions/$name"
    val path: String = "actions/" + name.replace("-", "/")
}

fun generateKotlinDeclarations(
    definitionsDir: File,
    sourceDir: File,
) {
    definitionsDir.listFiles()!!
        .filter { it.isDirectory }
        .forEach { dir ->
            generate(
                definitionsDir = dir,
                sourceDir = sourceDir,
            )
        }
}

private fun generate(
    definitionsDir: File,
    sourceDir: File,
) {
    val library = Library(definitionsDir.name)
    val files = sequenceOf("lib", "lib/internal")
        .map { definitionsDir.resolve(it) }
        .filter { it.exists() }
        .mapNotNull { it.listFiles { file -> file.name.endsWith(".d.ts") } }
        .flatMap { it.asSequence() }
        .toList()

    val dir = sourceDir.resolve(library.path)
        .also { it.mkdirs() }

    for (file in files) {
        val name = file.name
        val content = file.readText()

        dir.resolve(name)
            .writeText(content)
    }
}
