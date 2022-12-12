package karakum.browser

import java.io.File

private val ITERATOR_REGEX = Regex("""\ninterface (\w+) \{\n\s+\[Symbol.iterator\]\(\): IterableIterator<(\w+)>;""")

object IterableRegistry {
    private lateinit var map: Map<String, String>

    fun fill(
        definitionsDir: File,
    ) {
        val map = definitionsDir
            .listFiles { file -> file.name.endsWith(".d.ts") }!!
            .asSequence()
            .map { it.readText() }
            .map { it.replace("\r\n", "\n") }
            .flatMap { ITERATOR_REGEX.findAll(it) }
            .map { it.groupValues }
            .associate { it[1] to it[2] }
    }
}
