package karakum.typescript

private val REQUIRED = setOf(
    "ApplicableRefactorInfo",
    "Bundle",
    "CodeAction",
    "CompletionEntryDataAutoImport",
    "CustomTransformers",
    "DocumentSpan",
    "EmitHelperBase",
    "GetCompletionsAtPositionOptions",
    "Identifier",
    "SourceFile",
    "SourceMapSpan",
    "TupleType",

    "CancellationToken",
    "Collection",
    "CoreTransformationContext",
    "CustomTransformer",
    "FileWatcher",
    "HostCancellationToken",
    "IncompleteCompletionsCache",
    "ModuleResolutionCache",
    "PackageJsonInfoCache",
    "ParseConfigFileHost",
    "ReadonlyCollection",
    "ResolvedProjectReference",
    "Watch",

    "BuilderProgram",
    "Classifier",
    "DocumentRegistry",
    "EmitAndSemanticDiagnosticsBuilderProgram",
    "FormatDiagnosticsHost",
    "InvalidatedProjectBase",
    "NonRelativeModuleNameResolutionCache",
    "PerDirectoryResolutionCache",
    "PerModuleNameCache",
    "ReadBuildProgramHost",
    "ScriptReferenceHost",
    "SourceFileLike",
    "WatchCompilerHostOfConfigFile",
    "WatchOfFilesAndCompilerOptions",

    "UpdateBundleProject",
    "UpdateOutputFileStampsProject",
)

internal fun convertMembers(
    name: String,
    source: String,
): String {
    if (source.isEmpty())
        return ""

    if (("(" in source || " & {" in source) && name !in REQUIRED)
        return "    /*\n$source\n    */"

    return source.trimIndent()
        .removeSuffix(";")
        .splitToSequence(";\n")
        .map { convertMember(it) }
        .joinToString("\n")
}

private fun convertMember(
    source: String,
): String {
    if (source.startsWith("[") || source.startsWith("\" __sortedArrayBrand\""))
        return "    // $source"

    val comment = source.substringBeforeLast("\n", "")
        .ifEmpty { null }

    val body = source.substringAfterLast("\n")
    val content = if (isProperty(body)) {
        convertProperty(body)
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
): String {
    val modifier = if (source.startsWith("readonly ")) "val" else "var"
    val body = source.removePrefix("readonly ")

    val name = body.substringBefore(": ").removeSuffix("?")
    var type = kotlinType(body.substringAfter(": "), name)

    val optional = body.startsWith("${name}?")
    if (optional && type != DYNAMIC && !type.startsWith("$DYNAMIC ") && type != "Nothing?") {
        type = if (" /*" in type) type.replace(" /*", "? /*") else "$type?"
    }

    return "$modifier $name: $type"
}

private fun convertMethod(
    source: String,
): String {
    val name = source.substringBefore("(")

    val parametersSource = source
        .substringAfter("(")
        .substringBeforeLast("): ")

    val parameters = if (parametersSource.isNotEmpty()) {
        parametersSource
            .splitToSequence(", ")
            .joinToString(", ") {
                convertParameter(it)
            }
    } else ""

    val returnType = kotlinType(source.substringAfterLast("): "), name)
    val returnDeclaration = if (returnType != UNIT) {
        ": $returnType"
    } else ""

    return "fun $name($parameters)$returnDeclaration"
}

private fun convertParameter(
    source: String,
): String {
    val name = source
        .substringBefore(": ")
        .removeSuffix("?")

    val type = kotlinType(source.substringAfter(": "), name)
    var result = "$name: $type"
    if (source.startsWith("$name?:"))
        result += " = definedExternally"

    return result
}
