package karakum.actions

import java.io.File

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
        for ((name, body) in convert(file.readText())) {
            var f = dir.resolve(name + ".d.ts")

            var index = 2
            while (f.exists()) {
                f = dir.resolve(name + "_${index++}.d.ts")
            }

            check(!f.exists()) {
                "File $f already exists!"
            }

            f.writeText(body)
        }
    }
}
