package karakum.browser

import java.io.File

private sealed class MemberNumberData {
    abstract val className: String
}

private data class PropertyData(
    override val className: String,
    val propertyName: String,
    val propertyType: String,
) : MemberNumberData()

private data class ParameterData(
    override val className: String,
    val methodName: String,
    val parameterName: String,
    val parameterType: String,
) : MemberNumberData()

private data class MethodReturnData(
    override val className: String,
    val methodName: String,
    val returnType: String,
) : MemberNumberData()

private val NUMBER_TYPE_MAP = mapOf(
    "octet" to "Short /* unsigned byte */",

    "short" to "Short",
    "unsigned short" to "Short",

    "float" to "Float",

    "double" to "Double",
    "unrestricted double" to "Double",

    "long" to "Int",
    "unsigned long" to "Int",

    "long long" to "JsLong",
    "unsigned long long" to "JsLong",
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

    private val memberNumberData: List<MemberNumberData> by lazy {
        idlData.flatMap { content ->
            content
                .splitToSequence(
                    "\ninterface ",
                    "]interface ",
                    "] interface ",
                    "\npartial interface ",
                    "]partial interface ",
                    "\ndictionary ",
                    "\npartial dictionary ",
                )
                .drop(1)
                .map { it.substringBefore("\n};") }
                .flatMap { classBody ->
                    var className = classBody
                        .removePrefix("mixin ")
                        .substringBefore("\n")
                        .substringBefore(" ")

                    when (className) {
                        "BaseComputedKeyframe" -> className = "ComputedKeyframe"
                        "BaseKeyframe" -> className = "Keyframe"
                        "UnderlyingSource" -> className = "UnderlyingByteSource"
                    }

                    classBody
                        .substringAfter(" {\n")
                        .removeSuffix(";")
                        .replace(Regex(""";\s+//.+?\n"""), ";\n")
                        .splitToSequence("\n")
                        .filter { !it.trim().startsWith("// ") }
                        .joinToString("\n")
                        .splitToSequence(";\n")
                        .map { it.trim() }
                        .flatMap { line -> getMemberNumberData(className = className, line = line) }
                }
        }
    }

    private fun getMemberNumberData(
        className: String,
        line: String,
    ): Sequence<MemberNumberData> {
        if (line.startsWith("["))
            return getMemberNumberData(className, line.substringAfter("] ", ""))

        if ("  " in line) {
            return getMemberNumberData(className, line.replace(Regex("""\s+"""), " "))
        }

        if ("(" !in line || line.startsWith("(")) {
            val data = line
                .replace("[EnforceRange] ", "")
                .removePrefix("inherit ")
                .removePrefix("required ")
                .removePrefix("optional ")
                .removePrefix("readonly ")
                .removePrefix("attribute ")
                .substringBefore(" = ")

            val type = getNumberType(data.substringBeforeLast(" ").removeSuffix("?"))
                ?: return emptySequence()

            val name = data.substringAfterLast(" ")
            return sequenceOf(
                PropertyData(
                    className = className,
                    propertyName = name,
                    propertyType = type,
                )
            )
        }

        val source = line
            .substringAfter("(", "")
            .substringBeforeLast(")", "")

        val methodName = line
            .substringBefore("(")
            .trim()
            .substringAfterLast(" ")

        val parametersData = if (source.isNotEmpty()) {
            source
                .splitToSequence(",")
                .map { it.trim() }
                .map { it.substringBefore(" = ") }
                .map { it.substringAfter("] ") }
                .map { it.removePrefix("optional ") }
                .mapNotNull { psource ->
                    val type = getNumberType(psource.substringBeforeLast(" "))
                        ?: return@mapNotNull null

                    ParameterData(
                        className = className,
                        methodName = methodName,
                        parameterName = psource.substringAfterLast(" "),
                        parameterType = type,
                    )
                }
        } else emptySequence()

        val returnType = getNumberType(line.substringBefore(" $methodName"))
            ?: return parametersData

        return parametersData.plus(
            MethodReturnData(
                className = className,
                methodName = methodName,
                returnType = returnType,
            )
        )
    }

    private fun getNumberType(
        source: String,
    ): String? {
        val type = NUMBER_TYPE_MAP[source]
        if (type != null)
            return type

        if (!source.startsWith("("))
            return null

        return source
            .substringAfter("(")
            .substringBefore(")")
            .splitToSequence(" or ")
            .mapNotNull { getNumberType(it) }
            .firstOrNull()
    }

    private val propertyTypeMap: Map<Pair<String, String>, String> by lazy {
        memberNumberData.asSequence()
            .filterIsInstance<PropertyData>()
            .associate { (it.className to it.propertyName) to it.propertyType }
            .plus(
                sequenceOf(
                    ("RTCEncodedAudioFrame" to "timestamp") to "JsLong",
                    ("RTCEncodedVideoFrame" to "timestamp") to "JsLong",
                    ("SegmentData" to "index") to "Int",
                )
            )
    }

    private val parameterTypeMap: Map<Pair<String, String>, String> by lazy {
        memberNumberData.asSequence()
            .filterIsInstance<ParameterData>()
            .associate { (it.className to it.parameterName) to it.parameterType }
            .plus(
                sequenceOf(
                    // TODO: copy from `Document`?
                    ("DocumentOrShadowRoot" to "x") to "Double",
                    ("DocumentOrShadowRoot" to "y") to "Double",
                    ("HTMLCanvasElement" to "quality") to "Double",

                    ("DateTimeFormat" to "date") to "JsLong",
                    ("DateTimeFormat" to "endDate") to "JsLong",
                    ("DateTimeFormat" to "startDate") to "JsLong",
                    ("NumberFormat" to "end") to "Number",
                    ("NumberFormat" to "number") to "Number",
                    ("NumberFormat" to "start") to "Number",
                    ("NumberFormat" to "value") to "Number",
                    ("PluralRules" to "n") to "Int",
                    ("RelativeTimeFormat" to "value") to "Number",
                    ("Segments" to "codeUnitIndex") to "Int",
                )
            )
    }

    private val returnTypeMap: Map<Pair<String, String>, String> by lazy {
        memberNumberData.asSequence()
            .filterIsInstance<MethodReturnData>()
            .associate { (it.className to it.methodName) to it.returnType }
            .plus(("Collator" to "compare") to "Int")
    }

    fun hasEmptyConstructor(type: String): Boolean =
        type in typesWithEmptyConstructors

    fun hasHtmlConstructor(type: String): Boolean =
        type in typesWithHtmlConstructors

    fun getPropertyType(
        className: String,
        propertyName: String,
    ): String =
        propertyTypeMap.getValue(className to propertyName)

    fun getParameterType(
        className: String,
        parameterName: String,
    ): String =
        parameterTypeMap.getValue(className to parameterName)

    fun getReturnType(
        className: String,
        methodName: String,
    ): String =
        returnTypeMap.getValue(className to methodName)
}
