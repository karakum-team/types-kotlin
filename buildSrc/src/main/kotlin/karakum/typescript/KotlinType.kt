package karakum.typescript

internal const val DYNAMIC = "dynamic"
internal const val UNIT = "Unit"

internal const val STRING = "String"

private val STANDARD_TYPE_MAP = mapOf(
    "any" to "Any",
    "object" to "Any",
    "{}" to "Any",

    "boolean" to "Boolean",
    "string" to STRING,

    "never" to "Nothing",

    "number" to "Int",

    "void" to UNIT,
    "null" to "Nothing?",
    "undefined" to "Nothing?",

    "Date" to "kotlin.js.Date",

    "false" to "Boolean /* false */",
    "true" to "Boolean /* true */",

    "MapLike<string>" to "MapLike<String>",
    "MapLike<string[]>" to "MapLike<ReadonlyArray<String>>",

    "-1" to "Int /* -1 */",

    "() => T" to "() -> T",

    "(data: string) => string" to "(data: String) -> String",
    "(message: string) => void" to "(message: String) -> Unit",
    "(project: string) => CustomTransformers" to "(project: String) -> CustomTransformers",
    // "(value: V, key: K) => void" to "(value: V, key: K) -> Unit",
    "(pos: number) => number" to "(pos: Int) -> Int",
    "Iterator<[K, V]>" to "Iterator<$DYNAMIC /* [K, V] */>",
    "Iterator<[T, T]>" to "Iterator<$DYNAMIC /* [T, T] */>",

    "(node: Node) => boolean" to "(node: Node) -> Boolean",
    "(node: readonly Node[]) => T" to "(node: ReadonlyArray<Node>) -> T",

    "(sourceFile: SourceFile) => boolean" to "(sourceFile: SourceFile) -> Boolean",
    "AffectedFileResult<readonly Diagnostic[]>" to "AffectedFileResult<ReadonlyArray<Diagnostic>>",

    "ModuleKind.CommonJS | ModuleKind.ESNext | undefined" to "$DYNAMIC /* ModuleKind.CommonJS | ModuleKind.ESNext | undefined */",

    "typeof visitNodes" to "$DYNAMIC /* typeof visitNodes */",

    "AwaitKeywordToken" to "AwaitKeyword",
    "AssertsToken" to "AssertsKeyword",
)

internal fun kotlinType(
    type: String,
    name: String,
): String {
    STANDARD_TYPE_MAP[type]
        ?.also { return it }

    if (type.startsWith("readonly "))
        return kotlinType(type.removePrefix("readonly "), name)

    if (type.endsWith(" | undefined")) {
        var result = kotlinType(type.removeSuffix(" | undefined"), name)
        if (!result.startsWith(DYNAMIC)) result += "?"
        return result
    }

    if (type.startsWith("SignatureDeclaration & {"))
        return "SignatureDeclaration /* $type */"

    if (type.startsWith("(") && type.endsWith(" | undefined)[]"))
        return "ReadonlyArray<${kotlinType(type.removeSurrounding("(", " | undefined)[]"), name)}?>"

    if (" | " in type)
        return "$DYNAMIC /* $type */"

    if (type.startsWith("\""))
        return "$STRING /* $type */"

    if ("[\"" in type)
        return "$DYNAMIC /* $type */"

    if (type.endsWith("[]"))
        return "ReadonlyArray<${kotlinType(type.removeSuffix("[]"), name)}>"

    if (type.startsWith("Promise<")) {
        val parameter = kotlinType(type.removeSurrounding("Promise<", ">"), name)
        return "kotlin.js.Promise<$parameter>"
    }

    if (type.startsWith("("))
        return type.replace(" => void", " -> $UNIT")
            .replace(" => T", " -> T")
            .replace(" => Node", " -> Node")
            .replace(" => string", " -> String")
            .replace(" => boolean", " -> Boolean")
            .replace(" => element is T", " -> Boolean /* element is T */")
            .replace(" => tag is T", " -> Boolean /* tag is T */")
            .replace(" => node is T", " -> Boolean /* node is T */")
            .replace(": string", ": String")

    if (type.startsWith("node is "))
        return "Boolean /* $type */"

    return type
}
