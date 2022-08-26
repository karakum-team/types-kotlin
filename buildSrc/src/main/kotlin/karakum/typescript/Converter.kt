package karakum.typescript

import karakum.common.UnionConstant
import karakum.common.unionBody
import karakum.common.unionBodyByConstants
import java.io.File

internal data class ConversionResult(
    val name: String,
    val body: String,
    val pkg: Package = Package.TYPESCRIPT,
)

internal fun convertDefinitions(
    definitionsFile: File,
): Sequence<ConversionResult> {
    val typeConverter = GlobalTypeConverter()

    val result = definitionsFile.readText()
        .injectUnions()
        .replace("/** @type {Id} /", "// @type {Id}")
        .splitToSequence("declare namespace ts {\n")
        .drop(1)
        .map { it.substringBefore("\n}\n") }
        .map { it.trimIndent() }
        .map { it.replace("\nexport {}", "") }
        .plus(CONFIG_PROVIDER_SOURCE)
        .plus(OPTIONS_PROVIDER_SOURCE)
        .plus(RELATION_CACHE_SIZES_SOURCE)
        .flatMap { convertDefinitions(it, typeConverter) }
        .filter { it.name != "Iterator" }
        .plus(arrayHelpers())
        .plus(ConversionResult(NodeFormat.name, NodeFormat.body))
        .plus(ConversionResult(ResolutionMode.name, ResolutionMode.body))
        .plus(UNIONS.map { ConversionResult(it.name, it.body) })
        .plus(union())
        .groupBy { it.pkg to it.name }
        .values
        .map { it.merge() }
        .toList()

    // typeConverter.print()

    return result.asSequence()
}

private fun List<ConversionResult>.merge(): ConversionResult {
    if (size == 1)
        return single()

    val name = first().name
    val body = if (name[0].isLowerCase()) {
        joinToString("\n\n") { it.body }
    } else {
        mapIndexed { index: Int, item: ConversionResult ->
            var body = item.body
            if (index != lastIndex)
                body = body.substringBeforeLast("\n}")

            if (index != 0)
                body = body.substringAfter("{\n")

            body
        }.joinToString("\n")
    }

    return ConversionResult(name, body)
}

private const val DELIMITER = "<!--DELIMITER-->"

private val KEYWORDS = setOf(
    "let",
    "const",
    "function",
    "type",
    "enum",
    "interface",
    "class",
    "namespace",
)

private fun convertDefinitions(
    source: String,
    typeConverter: GlobalTypeConverter,
): Sequence<ConversionResult> {
    var content = source.replace("\n/**", "\n$DELIMITER/**")
    for (keyword in KEYWORDS) {
        content = content
            .replace("\nexport $keyword ", "\n${DELIMITER}$keyword ")
            .replace("\n$keyword ", "\n${DELIMITER}$keyword ")
    }

    var comment: String? = null
    val results = mutableListOf<ConversionResult>()

    content.splitToSequence(DELIMITER)
        .filter { it.isNotEmpty() }
        .map { it.removePrefix("export ") }
        .map { it.removeSuffix("\n") }
        .map { it.removeSuffix(";") }
        .forEach { part ->
            if (part.startsWith("/**")) {
                comment = part
            } else {
                val ignore = comment?.let {
                    it.startsWith("/** @deprecated ") || it.startsWith("/**\n * @deprecated ")
                } ?: false

                if (!ignore) {
                    results += convertDefinition(comment, part, typeConverter)
                }

                comment = null
            }
        }

    return results.asSequence()
        .flatMap(::addContractSupport)
}

private fun convertDefinition(
    comment: String?,
    source: String,
    typeConverter: GlobalTypeConverter,
): ConversionResult {
    val name = source.substringAfter(" ")
        .substringBefore(" ")
        .substringBefore("<")
        .substringBefore("(")
        .substringBefore(":")

    val type = source.substringBefore(" ")
    val shortSource = source.removePrefix("$type ")
    val content = when (type) {
        "let" -> convertLet(shortSource)
        "const" -> convertConst(name, shortSource)
        "function" -> convertFunction(name, shortSource)
        "type" -> convertType(name, shortSource, typeConverter)
        "enum" -> convertEnum(name, shortSource)
        "interface" -> convertInterface(name, shortSource, SimpleTypeConverter(name, typeConverter))
        "class" -> convertClass(shortSource)
        "namespace" -> convertNamespace(name, shortSource)

        else -> TODO("Unable to parse definition: $source")
    }

    val body = sequenceOf(comment, content)
        .filterNotNull()
        .joinToString("\n")

    return ConversionResult(name, body)
}

private fun convertLet(
    source: String,
): String {
    return "external val /* var */ $source"
}

private fun convertConst(
    name: String,
    source: String,
): String {
    if (" = " in source)
        return "const val $source"

    val body = source.substringAfter(": ")

    if (body.startsWith("(") || body.startsWith("<")) {
        val functionSource = when (name) {
            "createTempVariable" -> body.replace(") => Identifier", "): Identifier")
            else -> body.replace(") => ", "): ")
        }

        return convertFunction(
            name = name,
            source = name + functionSource,
        )
    }

    return "external val $name: ${kotlinType(body, name)}"
}

private val EXCLUDED_FUNCTIONS = setOf(
    "isIterationStatement",
)

private fun convertFunction(
    name: String,
    source: String,
): String {
    if (name in EXCLUDED_FUNCTIONS)
        return "/*\nexternal fun $source\n*/"

    val content = when (name) {
        "createIncrementalProgram",
        -> source.replace(
            "{ rootNames, options, configFileParsingDiagnostics, projectReferences, host, createProgram }",
            "options",
        )

        "readConfigFile",
        "parseConfigFileTextToJson",
        -> source.replace(CONFIG_PROVIDER_BODY, CONFIG_PROVIDER)

        "convertCompilerOptionsFromJson",
        -> source.replace(
            OPTIONS_PROVIDER_BODY.replace(": T", ": CompilerOptions"),
            "$OPTIONS_PROVIDER<CompilerOptions>",
        )

        "convertTypeAcquisitionFromJson",
        -> source.replace(
            OPTIONS_PROVIDER_BODY.replace(": T", ": TypeAcquisition"),
            "$OPTIONS_PROVIDER<TypeAcquisition>",
        )

        else -> source
    }

    val result = "external ${convertMethod(content)}"
        .replace(" = EmitAndSemanticDiagnosticsBuilderProgram", " /* = EmitAndSemanticDiagnosticsBuilderProgram */")

    return if (name.endsWith("CommentRange")) {
        result.replace(": number", ": Int")
            .replace(": boolean", ": Boolean")
            .replace(" => U", " -> U")
    } else result
}

// TEMP
private val IGNORED_TYPES = setOf(
    "EndOfFileToken",
)

private fun convertType(
    name: String,
    source: String,
    typeConverter: GlobalTypeConverter,
): String {
    val (declarationSource, body) = source.split(" = ")

    when (name) {
        "RefactorTriggerReason",
        "TypeOfTag",
        -> {
            val values = body
                .splitToSequence(" | ")
                .map { it.removeSurrounding("\"") }
                .toList()

            return unionBody(name, values)
        }
    }

    val declaration = declarationSource
        .replace("extends BuilderProgram", "/* : BuilderProgram */")
        .replace("extends Node", "/* : Node */")

    if (" | " !in body && "(" !in body && "{" !in body && name !in IGNORED_TYPES)
        return "typealias $declaration = $body"

    if (body.startsWith("(") && " & {" !in body) {
        val parameters = body
            .removePrefix("(")
            .substringBeforeLast(") => ")
            .splitToSequence(", ")
            .joinToString(",\n") {
                convertParameter(it, true)
            }
            // TODO: remove
            .replace("??", "?")

        val returnType = kotlinType(body.substringAfterLast(") => "), name)

        return "typealias $declaration = ($parameters) -> $returnType"
    }

    var baseType = when (name) {
        "ArrayBindingElement",
        "BindingPattern",
        "CallLikeExpression",
        "CaseOrDefaultClause",
        "ClassLikeDeclaration",
        "EntityName",
        "HasJSDoc",
        "JsxChild",
        "NamedExportBindings",
        "ObjectTypeDeclaration",
        "SignatureDeclaration",
        -> "Node"

        "BindingName",
        "PropertyName",
        -> "DeclarationName"

        "MemberName",
        -> "PrimaryExpression, DeclarationName"

        "AccessorDeclaration",
        "HasExpressionInitializer",
        "StringLiteralLike",
        -> "Declaration"

        "AssertionExpression",
        "JsxOpeningLikeElement",
        "TemplateLiteral",
        -> "Expression"

        "BreakOrContinueStatement",
        -> "Statement"

        "ModifierLike",
        -> "Node"

        "ParameterPropertyDeclaration",
        -> "ParameterDeclaration"

        "TypeOnlyAliasDeclaration",
        -> "ImportClause"

        "UnparsedNode",
        -> "UnparsedSection"

        "TemplateLiteralToken",
        -> "LiteralLikeNode"

        "JsxAttributeLike",
        "ObjectLiteralElementLike",
        -> "ObjectLiteralElement"

        "JsonObjectExpression",
        -> "Expression"

        "EntityNameExpression",
        "JsxTagNameExpression",
        -> "LeftHandSideExpression"

        "ConciseBody",
        -> "Node"

        "ModuleName",
        -> "Union.DeclarationStatement_name"

        "KeywordTypeSyntaxKind",
        "ModifierSyntaxKind",
            // TODO: check
        -> "SyntaxKind, KeywordSyntaxKind"

        "BinaryOperator",
        -> "SyntaxKind"

        else -> if (body.startsWith("SyntaxKind.")) {
            "SyntaxKind"
        } else null
    }

    if (hasUnionParent(name)) {
        baseType = listOfNotNull(baseType, "Union.${name}_")
            .joinToString(", ")
    }

    if ("{" !in body && "<" !in body && "[]" !in body && "\"" !in body)
        typeConverter.register(name, body)

    val parentDeclaration = if (baseType != null) " : $baseType" else ""
    var content = "sealed external interface $declaration$parentDeclaration /* $body */"
    if ("<T" in declaration)
        content = "@Suppress(\"UNUSED_TYPEALIAS_PARAMETER\")\n" + content

    return content
}

private fun convertEnum(
    name: String,
    source: String,
): String {
    val constants = source
        .substringAfter("{\n")
        .substringBeforeLast("\n}")
        .trimIndent()
        .splitToSequence(",\n")
        .map {
            val (cname, cvalue) = it.substringAfterLast("\n").split(" = ")
            val comment = it.substringBeforeLast("\n", "").ifEmpty { null }
            UnionConstant(
                kotlinName = cname,
                jsName = cname,
                value = cvalue,
                originalValue = true,
                comment = comment,
            )
        }
        .toList()

    val result = unionBodyByConstants(name, constants)
    if (name != "SyntaxKind" && name != "TypePredicateKind" && name != "InvalidatedProjectKind")
        return result

    return result.replaceFirst(" enum class ", " sealed interface ")
        .replaceFirst(";\n", "")
        .splitToSequence("\n")
        .joinToString("\n") {
            if (it.endsWith(",")) {
                val constName = it.removeSuffix(",")
                var declaration = "object $constName: $name"
                if (hasUnionParent("$name.$constName"))
                    declaration += ", Union.${name}_$constName"

                declaration
            } else it
        }
}

private fun convertInterface(
    name: String,
    source: String,
    typeConverter: SimpleTypeConverter,
): String {
    var declaration = source.substringBefore(" {\n")
        .replace(" extends ", " : ")
        .replace("<string", "<String")
        .replace(" = KeywordTypeSyntaxKind", "")
        .replace(" : Array<T>", " : ReadonlyArrayAdapter<T>")
        .replace(" : ReadonlyArray<T>", " : ReadonlyArrayAdapter<T>")

    declaration = when (name) {
        "NodeArray",
        "SortedReadonlyArray",
        -> declaration.replaceFirst("<T", "<out T")

        else -> declaration
    }

    declaration = declaration.replaceFirst("<TKind", "<out TKind")

    if (hasUnionParent(name))
        declaration += (if (":" in declaration) ", " else ": ") + "Union.${name}_"

    val bodySource = source
        .substringAfter("{\n")
        .substringBeforeLast("\n}", "")

    val members = convertMembers(name, bodySource, typeConverter)
    var body = when {
        " extends " in source
                || name == "FunctionTypeNode"
                || name == "ShorthandPropertyAssignment"
        -> fixOverrides(name, members)

        else -> members
    }

    when (name) {
        "PrintHandlers",
        "TransformationResult",
        -> body = body.replace(") => void)", ") -> Unit)")
    }

    return "external sealed interface $declaration {\n$body\n}"
}

private fun convertClass(
    source: String,
): String {
    return "external class $source"
}

private fun convertNamespace(
    name: String,
    source: String,
): String {
    val bodySource = source
        .substringAfter("{\n")
        .substringBeforeLast("\n}", "")
        .replace("function ", "")

    val typeConverter = SimpleTypeConverter(name, GlobalTypeConverter())
    val body = convertMembers(name, bodySource, typeConverter)
    return "external object $name {\n$body\n}"
}
