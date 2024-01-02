package karakum.browser

import java.io.File

object IDLRegistry {
    lateinit var rootDirectory: File

    private val idlData: List<String> by lazy {
        rootDirectory
            .listFiles { file -> file.extension == "idl" }!!
            .map { it.readText() }
    }

    private fun hasContent(
        memberContent: String,
    ): Set<String> =
        idlData.asSequence()
            .flatMap { content ->
                content
                    .splitToSequence("\ninterface ")
                    .drop(1)
                    .map { it.substringBefore("\n};") }
                    .filter { memberContent in it }
                    .map { it.substringBefore(" ") }
            }
            .toSet()

    private val typesWithEmptyConstructors: Set<String> by lazy {
        hasContent("  constructor();")
    }

    private val typesWithHtmlConstructors: Set<String> by lazy {
        hasContent("[HTMLConstructor] constructor();")
    }

    fun hasEmptyConstructor(type: String): Boolean =
        type in typesWithEmptyConstructors

    fun hasHtmlConstructor(type: String): Boolean =
        type in typesWithHtmlConstructors
}
