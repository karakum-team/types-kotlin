package karakum.browser

import java.io.File

internal data class ParameterData(
    val className: String,
    val methodName: String,
    val parameterName: String,
    val parameterType: String,
)

internal object IDLRegistry {
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

    /* private */ val parameterData: List<ParameterData> by lazy {
        idlData.flatMap { content ->
            content
                .splitToSequence(
                    "\ninterface ",
                    "]interface ",
                    "\npartial interface ",
                    "]partial interface "
                )
                .drop(1)
                .map { it.substringBefore("\n};") }
                .flatMap { classBody ->
                    val className = classBody
                        .removePrefix("mixin ")
                        .substringBefore("\n")
                        .substringBefore(" ")

                    classBody
                        .substringAfter(" {\n")
                        .removeSuffix(";")
                        .splitToSequence(";\n")
                        .map { it.trim() }
                        .flatMap { line -> getParameterData(className = className, line = line) }
                }
        }
    }

    private fun getParameterData(
        className: String,
        line: String,
    ): Sequence<ParameterData> {
        if (line.startsWith("["))
            return getParameterData(className, line.substringAfter("] ", ""))

        val source = line
            .substringAfter("(", "")
            .substringBeforeLast(")", "")
            .ifEmpty { return emptySequence() }

        val methodName = line
            .substringBefore("(")
            .trim()
            .substringAfterLast(" ")

        return source
            .splitToSequence(",")
            .map { it.trim() }
            .map { it.substringBefore(" = ") }
            .map { it.substringAfter("] ") }
            .map { it.removePrefix("optional ") }
            .mapNotNull { psource ->
                val type = when (psource.substringBeforeLast(" ")) {
                    "unsigned short",
                    -> "Short"

                    "float",
                    -> "Float"

                    "double",
                    "unrestricted double",
                    -> "Double"

                    "long",
                    "unsigned long",
                    -> "Int"

                    "long long",
                    "unsigned long long",
                    -> "JsLong"

                    else -> return@mapNotNull null
                }

                ParameterData(
                    className = className,
                    methodName = methodName,
                    parameterName = psource.substringAfterLast(" "),
                    parameterType = type,
                )
            }
    }

    private val parameterTypeMap: Map<Pair<String, String>, String> by lazy {
        parameterData.associate { (it.className to it.parameterName) to it.parameterType }
    }

    fun hasEmptyConstructor(type: String): Boolean =
        type in typesWithEmptyConstructors

    fun hasHtmlConstructor(type: String): Boolean =
        type in typesWithHtmlConstructors

    fun getParameterType(
        className: String,
        parameterName: String,
    ): String {
        val type = parameterTypeMap[className to parameterName]

        return if (type != null) {
            type
        } else {
            println("Missed number type for [$className.$parameterName]")
            "Number"
        }
    }
}
