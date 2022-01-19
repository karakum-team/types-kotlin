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

    "GetEffectiveTypeRootsHost",
    "ModuleResolutionHost",
    "ParseConfigHost",
    "WatchCompilerHost",

    "LanguageServiceHost",
    "ProgramHost",
    "Push",
    // "Scanner",
    "WatchHost",
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
        .replace("(...args: any[]) => void", "Function<$UNIT>")

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

    if (body.startsWith("$name?"))
        type = type.addOptionality()

    return "$modifier $name: $type"
}

private fun convertMethod(
    source: String,
): String {
    val name = source.substringBefore("(")
        .removeSuffix("?")

    val parametersSource = source
        .substringAfter("(")
        .substringBeforeLast("): ")

    val optional = source.startsWith("$name?")
    val parameters = if (parametersSource.isNotEmpty()) {
        parametersSource
            .splitToSequence(", ")
            .joinToString(", ") {
                convertParameter(it, optional)
            }
    } else ""

    val returnType = kotlinType(source.substringAfterLast("): "), name)

    return if (optional) {
        "val $name: (($parameters) -> $returnType)?"
    } else {
        val returnDeclaration = if (returnType != UNIT) {
            ": $returnType"
        } else ""

        "fun $name($parameters)$returnDeclaration"
    }
}

private fun convertParameter(
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
