package karakum.typescript

import karakum.common.UnionConstant
import karakum.common.unionBody
import karakum.common.unionBodyByConstants
import java.io.File

internal data class ConversionResult(
    val name: String,
    val body: String,
)

internal fun convertDefinitions(
    definitionsFile: File,
): Sequence<ConversionResult> =
    definitionsFile.readText()
        .splitToSequence("declare namespace ts {\n")
        .drop(1)
        .map { it.substringBefore("\n}\n") }
        .map { it.trimIndent() }
        .map { it.replace("\nexport {}", "") }
        .plus(RELATION_CACHE_SIZES_SOURCE)
        .flatMap { convertDefinitions(it) }

private const val DELIMITER = "<!--DELIMITER-->"

private val CONVERTER_MAP = mapOf(
    "let" to ::convertLet,
    "const" to ::convertConst,
    "function" to ::convertFunction,
    "type" to ::convertType,
    "enum" to ::convertEnum,
    "interface" to ::convertInterface,
    "class" to ::convertClass,
    "namespace" to ::convertNamespace,
)

private val KEYWORDS = CONVERTER_MAP.keys

private fun convertDefinitions(
    source: String,
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
                    results += convertDefinition(comment, part)
                }

                comment = null
            }
        }

    return results.asSequence()
}

private fun convertDefinition(
    comment: String?,
    source: String,
): ConversionResult {
    val name = source.substringAfter(" ")
        .substringBefore(" ")
        .substringBefore("<")
        .substringBefore("(")
        .substringBefore(":")

    val type = source.substringBefore(" ")
    val content = CONVERTER_MAP.getValue(type)(name, source.removePrefix("$type "))

    val body = sequenceOf(comment, content)
        .filterNotNull()
        .joinToString("\n")

    return ConversionResult(name, body)
}

private fun convertLet(
    name: String,
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
    "readConfigFile",
    "parseConfigFileTextToJson",
    "convertCompilerOptionsFromJson",
    "convertTypeAcquisitionFromJson",
    "createIncrementalProgram",
)

private fun convertFunction(
    name: String,
    source: String,
): String {
    if (name in EXCLUDED_FUNCTIONS)
        return "/*\nexternal fun $source\n*/"

    val result = "external ${convertMethod(source)}"
        .replace(" = EmitAndSemanticDiagnosticsBuilderProgram", " /* = EmitAndSemanticDiagnosticsBuilderProgram */")

    return if (name.endsWith("CommentRange")) {
        result.replace(": number", ": Int")
            .replace(": boolean", ": Boolean")
            .replace(" => U", " -> U")
    } else result
}

// TEMP
private val IGNORED_TYPES = setOf(
    "BinaryOperatorToken",
    "EndOfFileToken",
    "ModifiersArray",
)

private fun convertType(
    name: String,
    source: String,
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

    val unionType = when (name) {
        "ArrayBindingElement",
        "BindingPattern",
        "CaseOrDefaultClause",
        "ClassLikeDeclaration",
        "HasJSDoc",
        "JsxChild",
        "JsxOpeningLikeElement",
        "ObjectTypeDeclaration",
        "SignatureDeclaration",
        -> "Node"

        "JsxAttributeLike",
        "ObjectLiteralElementLike",
        -> "ObjectLiteralElement"

        "JsonObjectExpression",
        -> "Expression"

        "EntityNameExpression",
        "JsxTagNameExpression",
        -> "LeftHandSideExpression"

        else -> if (body.startsWith("SyntaxKind.")) {
            "SyntaxKind"
        } else "Any"
    }

    var content = "typealias $declaration = $unionType /* $body */"
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
                "object " + it.removeSuffix(",") + ": $name"
            } else it
        }
}

private fun convertInterface(
    name: String,
    source: String,
): String {
    var declaration = source.substringBefore(" {\n")
        .replace(" extends ", " : ")
        .replace("<string", "<String")
        .replace(" = KeywordTypeSyntaxKind", "")

    declaration = when (name) {
        "KeywordToken",
        -> declaration.replaceFirst("> : ", "> /* : ") + " */"

        "NodeArray",
        "SortedReadonlyArray",
        -> declaration.replaceFirst("<T", "<out T")

        else -> declaration
    }

    declaration = declaration.replaceFirst("<TKind", "<out TKind")

    val bodySource = source
        .substringAfter("{\n")
        .substringBeforeLast("\n}", "")

    val members = convertMembers(name, bodySource)
    var body = if (" extends " in source) {
        fixOverrides(name, members)
    } else members

    when (name) {
        "PrintHandlers",
        "TransformationResult",
        -> body = body.replace(") => void)", ") -> Unit)")
    }

    return "external interface $declaration {\n$body\n}"
}

private fun convertClass(
    name: String,
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

    val body = convertMembers(name, bodySource)
    return "external object $name {\n$body\n}"
}
