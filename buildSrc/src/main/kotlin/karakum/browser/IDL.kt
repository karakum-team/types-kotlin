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

internal val NUMBER_TYPE_MAP = mapOf(
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

    private val plainObjectInterfaces: Set<String> by lazy {
        idlData.asSequence()
            .flatMap { content ->
                content
                    .splitToSequence("\npartial dictionary ", "\ndictionary ")
                    .drop(1)
                    .map { it.substringBefore("\n") }
                    .map { it.substringBefore(" ") }
            }
            // TEMP
            .plus(
                sequenceOf(
                    "GPUImageCopyBuffer",
                    "GPUImageCopyTexture",
                    "GPUImageDataLayout",
                )
            )
            .toSet()
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
                    // FileReader
                    .map { it.removeSuffix(":") }
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
                    val className = classBody
                        .removePrefix("mixin ")
                        .substringBefore("\n")
                        .substringBefore(" ")

                    val classNames = when (className) {
                        "BaseComputedKeyframe" -> listOf("ComputedKeyframe")
                        "BaseKeyframe" -> listOf("Keyframe")
                        "UnderlyingSource" -> listOf(className, "UnderlyingByteSource")
                        "Document" -> listOf(className, "DocumentOrShadowRoot")

                        "HTMLCollection" -> listOf(className, "HTMLCollectionBase")
                        "NodeList" -> listOf(className, "NodeListOf")

                        // TEMP?
                        "MediaSessionSeekActionDetails" -> listOf(className, "MediaSessionActionDetails")
                        "MediaSessionSeekToActionDetails" -> listOf(className, "MediaSessionActionDetails")

                        else -> listOf(className)
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
                        .flatMap { line ->
                            classNames.flatMap { cn ->
                                getMemberNumberData(className = cn, line = line)
                            }
                        }
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
                // TODO: check
                .removePrefix("unrestricted ")
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
                    val type = getNumberType(psource.substringBeforeLast(" ").removeSuffix("?"))
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

        if (source.startsWith("GPU"))
            return source

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

                    // TEMP
                    ("GPUSupportedLimits" to "maxInterStageShaderComponents") to "Int",
                    ("GPUImageCopyTexture" to "mipLevel") to "GPUIntegerCoordinate",
                    ("GPUImageDataLayout" to "offset") to "GPUSize64",
                    ("GPUImageDataLayout" to "bytesPerRow") to "GPUSize32",
                    ("GPUImageDataLayout" to "rowsPerImage") to "GPUSize32",
                )
            )
    }

    private val parameterTypeMap: Map<Pair<String, String>, String> by lazy {
        memberNumberData.asSequence()
            .filterIsInstance<ParameterData>()
            .associate { (it.className to it.parameterName) to it.parameterType }
            .plus(
                sequenceOf(
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
    ): String {
        if (className.startsWith("GPU")) {
            return parameterTypeMap[className to parameterName] ?: run {
                when {
                    parameterName == "size" -> "Int"
                    parameterName == "slot" -> "Int"
                    parameterName == "index" -> "Int"
                    parameterName.endsWith("Index") -> "Int"
                    parameterName == "offset" -> "Int"
                    parameterName.endsWith("Offset") -> "Int"
                    parameterName.endsWith("Count") -> "Int"
                    parameterName.endsWith("Start") -> "Int"
                    parameterName.endsWith("Length") -> "Int"
                    parameterName.endsWith("Vertex") -> "Int"
                    parameterName.endsWith("Instance") -> "Int"

                    parameterName == "x" -> "Int"
                    parameterName == "y" -> "Int"
                    parameterName == "z" -> "Int"

                    else -> TODO()
                }
            }
        }

        return parameterTypeMap.getValue(className to parameterName)
    }

    fun getReturnType(
        className: String,
        methodName: String,
    ): String =
        returnTypeMap.getValue(className to methodName)

    fun isPlainObjectInterface(
        name: String,
    ): Boolean {
        if (name in plainObjectInterfaces)
            return true

        if (!name.endsWith("Options"))
            return false

        return when (name) {
            "LocaleOptions",
            "PushSubscriptionOptions",
                -> false

            else -> true
        }
    }
}
