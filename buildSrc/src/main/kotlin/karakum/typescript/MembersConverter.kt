package karakum.typescript

private val IGNORED = setOf(
    "Iterator",
    "JSDocAugmentsTag",
    "JSDocImplementsTag",
)

private const val SIGNATURE_TO_SIGNATURE = """:
    | SignatureDeclaration & {
        typeArguments?: NodeArray<TypeNode>;
    }
    | undefined;"""

private const val FILE_SUGGESTIONS = """: {
    newFileName: string;
    files: string[];
};"""

private const val GET_MODE_FILE_PARAMETER = """{
        impliedNodeFormat?: ResolutionMode;
    },
    """

private val REPLACEMENTS = sequenceOf(
    SIGNATURE_TO_SIGNATURE,
    FILE_SUGGESTIONS,
    GET_MODE_FILE_PARAMETER,
).associateWith { from ->
    from.replace(":\n    | ", ": ")
        .replace("\n        ", " ")
        .replace("\n    ", " ")
        .replace(";\n", "; ")
}

internal fun String.applyFunctionParametersPathes(): String =
    REPLACEMENTS.entries.fold(this) { acc, (from, to) ->
        acc.replace(from, to)
    }

internal fun convertMembers(
    name: String,
    source: String,
    typeConverter: TypeConverter,
): String {
    if (source.isEmpty())
        return ""

    if (name in IGNORED) return "    /*\n$source\n    */"

    val thisReplacement = when (name) {
        "ESMap" -> "ESMap<K, V>"
        "ModeAwareCache" -> "ModeAwareCache<T>"
        "Set" -> "Set<T>"
        else -> "<this>"
    }

    return source.trimIndent()
        .replace(";\n * ", ";---\n * ")
        .replace(RELATION_CACHE_SIZES_BODY, RELATION_CACHE_SIZES)
        .applyFunctionParametersPathes()
        .replace(": this;", ": $thisReplacement;")
        .removeSuffix(";")
        .splitToSequence(";\n")
        .filter { "@deprecated" !in it }
        .filter { NODE_VISITOR_TYPE_PARAMETERS !in it }
        .map { convertMember(it, typeConverter) }
        .filter { it.isNotEmpty() }
        .joinToString("\n")
        .replace(";---\n * ", ";\n * ")
}

private fun convertMember(
    source: String,
    typeConverter: TypeConverter,
): String {
    if (source.startsWith("[") || source.startsWith("\" __sortedArrayBrand\""))
        return "    // $source"

    val comment = source.substringBeforeLast("\n", "")
        .ifEmpty { null }

    val body = source.substringAfterLast("\n")
        .replace("(...args: any[]) => void", "Function<$UNIT>")

    if (comment != null && "* @deprecated" in comment)
        return ""

    val content = if (isProperty(body)) {
        convertProperty(body, typeConverter)
    } else {
        convertMethod(body)
    }

    return sequenceOf(comment, content)
        .filterNotNull()
        .joinToString("\n")
}

private fun isProperty(
    source: String,
): Boolean =
    ("(" !in source) || (source.indexOf(":") < source.indexOf("("))

private fun convertProperty(
    source: String,
    typeConverter: TypeConverter,
): String {
    val modifier = if (source.startsWith("readonly ")) "val" else "var"
    val body = source.removePrefix("readonly ")

    val name = body.substringBefore(": ").removeSuffix("?")
    var type = typeConverter.convert(body.substringAfter(": "), name)

    if (body.startsWith("$name?"))
        type = type.addOptionality()

    return "$modifier $name: $type"
}

internal fun convertMethod(
    source: String,
): String {
    val name = source.substringBefore("(")
        .substringBefore("<")
        .removeSuffix("?")
        .ifEmpty { "invoke" }

    var typeParameters = source.substringBefore("(")
        .substringAfter("<", "")
        .let { if (it.isNotEmpty()) "<$it" else "" }
        .replace(" extends ", " : ")
        .replace(
            "AccessorDeclaration | MethodDeclaration | MethodSignature | PropertyDeclaration | PropertySignature | PropertyAssignment",
            "Any /* AccessorDeclaration | MethodDeclaration | MethodSignature | PropertyDeclaration | PropertySignature | PropertyAssignment */",
        )
        .replace(" | undefined", "?")

    var whereParameters = ""
    if (typeParameters == "<T : HasModifiers & HasDecorators>") {
        typeParameters = "<T>"
        whereParameters = " where T : HasModifiers,\n" +
                "T : HasDecorators"
    }

    val parametersSource = source
        .substringAfter("(")
        .substringBeforeLast("): ")

    val optional = source.startsWith("$name?")
    var parameters = when {
        parametersSource == "action: (value: V, key: K) => void" || parametersSource == "action: (value: T, key: T) => void"
        -> parametersSource.replace(" => void", " -> $UNIT")

        parametersSource == "cb: (elem: T, key: string, mode: ResolutionMode) => void"
        -> "cb: (elem: T, key: String, mode: ResolutionMode?) -> Unit"

        parametersSource.isNotEmpty()
        -> parametersSource
            .replace("Map<string, ExtendedConfigCacheEntry>", "Map<string__ExtendedConfigCacheEntry>")
            .replace("Visitor<NonNullable<TIn>, TVisited>", "Visitor<NonNullable<TIn>__TVisited>")
            .replace("Visitor<TIn, Node | undefined>", "Visitor<TIn__Node | undefined>")
            .splitToSequence(", ")
            .joinToString(",\n") {
                convertParameter(it, optional)
                    .replace("Map<string__ExtendedConfigCacheEntry>", "JsMap<String, ExtendedConfigCacheEntry>")
                    .replace("Visitor<NonNullable<TIn>__TVisited>", "Visitor<TIn & Any, TVisited>")
                    .replace("Visitor<TIn__Node | undefined>", "Visitor<TIn, Node?>")
            }

        else -> ""
    }

    val returnType = kotlinType(source.substringAfterLast("): "), name)

    if (optional) {
        if (name == "resolveTypeReferenceDirectiveReferences") {
            parameters = parameters.replace("<T>", "<Any /* T (FileReference | string) */>")
        }

        return "val $name: (($parameters) -> $returnType)?"
    }

    val isOperator = when (name) {
        "invoke" -> true
        "get" -> parameters.count { it == ':' } == 1
        "set" -> parameters.count { it == ':' } == 2
        else -> false
    }

    val annotation = if (name == "invoke") "@JsNative" else ""
    val keyword = if (isOperator) "operator fun" else "fun"

    val returnDeclaration = if (returnType != UNIT) {
        ": $returnType"
    } else ""

    return sequenceOf(
        annotation,
        "$keyword $typeParameters $name($parameters)$returnDeclaration$whereParameters"
    ).filter { it.isNotEmpty() }
        .joinToString("\n")
}

internal fun convertParameter(
    source: String,
    lambdaMode: Boolean,
): String {
    when (source) {
        "...values: T[]" -> return "vararg values: T"
        "...args: any[]" -> return "/* vararg */ args: $DYNAMIC"
    }

    val name = source
        .substringBefore(": ")
        .removeSuffix("?")

    val type = kotlinType(source.substringAfter(": "), name)
    val declaration = if (source.startsWith("$name?:")) {
        if (lambdaMode) {
            type.addOptionality()
        } else {
            "$type = definedExternally"
        }
    } else type

    return "$name: $declaration"
}
