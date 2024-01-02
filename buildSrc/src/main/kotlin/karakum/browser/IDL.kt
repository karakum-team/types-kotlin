package karakum.browser

import java.io.File

object IDLRegistry {
    lateinit var rootDirectory: File

    private val typesWithEmptyConstructors: Set<String> by lazy {
        rootDirectory
            .listFiles { file -> file.extension == "idl" }!!
            .asSequence()
            .flatMap { file ->
                file.readText()
                    .splitToSequence("\ninterface ")
                    .drop(1)
                    .map { it.substringBefore("\n};") }
                    .filter { "constructor();" in it }
                    .map { it.substringBefore(" ") }
            }
            .toSet()
    }

    fun hasEmptyConstructor(type: String): Boolean =
        type in typesWithEmptyConstructors
}
