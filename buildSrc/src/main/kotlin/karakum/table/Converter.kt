package karakum.table

import java.io.File

internal data class ConversionResult(
    val name: String,
    val body: String,
)

private val EXCLUDED_ITEMS = setOf(
    "IfDefined",
    "memo",
    "Overwrite",

    "NoInfer",
    "NoInfer$1",

    "PartialKeys",
    "RequiredKeys",
    "UnionToIntersection",

    "Key",
    "Union",
    "Join",

    "isFunction",
    "makeStateUpdater",

    "AllowedIndexes",
    "ComputeRange",

    "Index40",
    "Index100",
    "IsAny",
    "IsKnown",
    "IsTuple",

    "DeepKeys",
    "DeepKeysPrefix",
    "DeepValue",

    "createColumnHelper",
    "ColumnHelper",
)

internal fun convertDefinitions(
    definitionFile: File,
): Sequence<ConversionResult> =
    definitionFile.readText()
        .substringBefore("\n\nexport {")
        .replace("\ninterface ", "\ndeclare interface ")
        .splitToSequence("\ndeclare ")
        .drop(1)
        .map { it.removeSuffix(";") }
        .filter { " = keyof typeof " !in it }
        .map { convertDefinition(it) }
        .filter { it.name !in EXCLUDED_ITEMS }

private fun convertDefinition(
    source: String,
): ConversionResult {
    val type = source.substringBefore(" ")
    val body = source.substringAfter(" ")

    return when (type) {
        "const" -> convertConst(body)
        "function" -> convertFunction(body)
        "type" -> convertType(body)
        "interface" -> convertInterface(body)
        else -> TODO()
    }
}

private fun convertConst(
    source: String,
): ConversionResult {
    val name = source.substringBefore(":")
    if (name == "createRow") {
        val newSource = source
            .replaceFirst(": ", "")
            .replace(" => ", ": ")

        return convertFunction(newSource)
    }

    val type = if (": {" in source) "object" else "val"

    val body = source.replace(": {", " {")
        .replace("\n    ", "\n    val ")
        .replace("<any>", "<*>")
        .replace("number", "Int")

    val content = "external $type $body"
    return ConversionResult(name, content)
}

private fun convertFunction(
    source: String,
): ConversionResult {
    val name = source
        .substringBefore(" ")
        .substringBefore(":")
        .substringBefore("<")
        .substringBefore("(")

    var body = source.removePrefix(name)
        .replaceFirst("(", " $name(")
        .replace(" extends unknown", "")
        .replace(" extends ", " : ")
        .replace("?: {\n    initialSync: boolean;\n}", ": dynamic = definedExternally /* { initialSync: boolean } */")
        .replace(
            "headerFamily?: 'center' | 'left' | 'right'",
            "headerFamily: String = definedExternally /* 'center' | 'left' | 'right' */"
        )
        .replace(": boolean | 'some' | 'all'", ": Any /* Boolean | 'some' | 'all' */")
        .replace("Record<string, boolean>", "ReadonlyRecord<String, Boolean>")
        .replace("Map<any, number>", "JsMap<Any, Int>")
        .replace(" => void", " -> Unit")
        .replace(" => ", " -> ")
        .replace(": string[]", ": ReadonlyArray<String>")
        .replace(" TNode[]", " ReadonlyArray<TNode>")
        .replace(": Column<TData, unknown>[]", ": ReadonlyArray<Column<TData, *>>")
        .replace(": HeaderGroup<TData>[]", ": ReadonlyArray<HeaderGroup<TData>>")
        .replace("undefined | [number, number]", "JsTuple2<Int, Int>?")
        .replace("?: Column<TData, unknown>", ": Column<TData, *> = definedExternally")
        .replace("?: Column<TData, TValue>", ": Column<TData, TValue> = definedExternally")
        .replace(": ColumnDef<TData>", ": ColumnDef<TData, *>")
        .replace(": TData | undefined", ": TData?")
        .replace("?: Row<TData>[] | undefined", ": ReadonlyArray<Row<TData>>? = definedExternally")
        .replace(": string", ": String")
        .replace(": number", ": Int")
        .replace(": boolean", ": Boolean")
        .replace("?: GroupingColumnMode", ": GroupingColumnMode = definedExternally")
        .replace("?: FilterFn<TData>", ": FilterFn<TData> = definedExternally")
        .replace("?: any", ": Any = definedExternally")
        .replace(": void", "")

    // TODO: use result interface instead
    if ("): {" in body)
        body = body.replace("): {", "): Any /* {") + " */"

    if (name == "createRow")
        body = body.replaceFirst("<TData>", "<TData : RowData>")

    return ConversionResult(name, "external fun " + body)
}

private fun convertType(
    source: String,
): ConversionResult =
    if (" = {\n" in source) {
        convertInterface(source)
    } else {
        convertTypealias(source)
    }

private fun convertTypealias(
    source: String,
): ConversionResult {
    val name = source
        .substringBefore(" ")
        .substringBefore(":")
        .substringBefore("<")
        .substringBefore("(")

    var declaration = source
        .replace(" = unknown>", ">")
        .substringBefore(" = ")
        .replace(" extends ", " : ")

    var body = source
        .replace(" = unknown>", ">")
        .substringAfter(" = ")
        .replace(" => ", " -> ")

    when (name) {
        "ResolvedAggregationFns",
        "ResolvedFilterFns",
        "ResolvedSortingFns",
        -> return ConversionResult(name, "external interface $name {\n/*\n$body\n*/\n}")
    }

    if (body == "{}")
        return ConversionResult(name, "external interface $declaration")

    if (body.startsWith("'") && !body.startsWith("'auto'") || body.startsWith("false | '"))
        return convertUnion(name, body)

    if (name == "RowData")
        return ConversionResult(name, "typealias $declaration = Any /* $body */")

    if (name == "Getter")
        body = "() -> TValue /* $body */"

    if (body.startsWith("PartialKeys<")) {
        body = body.removeSurrounding("PartialKeys<", ">")
        val parent = body.substringBefore(", ")
        val interfaceBody = body.substringAfter(", ")
            .splitToSequence(" | ")
            .map { it.removeSurrounding("'") }
            .flatMap {
                sequenceOf(
                    "@Deprecated(message = \"Excluded property\", level = DeprecationLevel.HIDDEN)",
                    "override var $it: dynamic",
                )
            }
            .joinToString("\n")

        return ConversionResult(name, "external interface $declaration : $parent {\n$interfaceBody\n}")
    }

    if (" | " in body) {
        when (name) {
            "ColumnDef",
            "ColumnIdentifiers",
            "AccessorColumnDef",
            -> {
                body = body
                    .splitToSequence(" | ")
                    .joinToString(",\n", "\n")
                    .replace(",\nStringHeaderIdentifier", "\n    /* StringHeaderIdentifier */")

                return ConversionResult(name, "external interface $declaration : $body")
            }
        }

        declaration = declaration
            .replace(": object>", ": Any>")

        if (name == "StringOrTemplateHeader")
            declaration = declaration.replace("<TData,", "<TData : RowData,")

        val typeParameters = declaration.removePrefix(name)
        val factoryType = declaration
            .replace(": RowData", "")
            .replace(": Any", "")
        val factories = body.splitToSequence(" | ")
            .filter { !it.startsWith("keyof ") }
            .map { it.removeSurrounding("(", ")") }
            .filter { !it.startsWith("BuiltIn") }
            .map { type ->
                when (type) {
                    "true" -> "Boolean /* $type */"
                    "'auto'" -> "String /* $type */"
                    else -> type
                        .replace("Record<", "ReadonlyRecord<")
                        .replace("string", "String")
                        .replace("number", "Number")
                        .replace("symbol", "Symbol")
                        .replace("boolean", "Boolean")
                        .replace("any", "Any?") // No element
                }
            }
            .joinToString("\n\n") { type ->
                val modifier = if (type.startsWith("(") || type.startsWith("AggregationFn<")) "noinline" else ""

                """
                inline fun $typeParameters $name(
                    $modifier source: $type,
                ): $factoryType =
                    source.unsafeCast<$factoryType>()
                """.trimIndent()
            }

        return ConversionResult(name, "sealed external interface $declaration /* $body */\n\n$factories")
    }

    if ("&" in body) {
        if (body.startsWith("ColumnDefBase<TData, TValue> & {\n") ||
            body.startsWith("ColumnDefBase<TData, TValue> & ColumnIdentifiers<TData, TValue> & {\n") ||
            "\n} & Partial<ColumnIdentifiers<TData, TValue>> & ColumnDefBase<TData, TValue> & {" in body
        ) {
            var members = body
                .removePrefix("ColumnDefBase<TData, TValue> & {\n")
                .removePrefix("ColumnDefBase<TData, TValue> & ColumnIdentifiers<TData, TValue> & {\n")
                .replace("\n} & Partial<ColumnIdentifiers<TData, TValue>> & ColumnDefBase<TData, TValue> & {", "")
                .removePrefix("{\n")
                .let { convertMembers(it) }

            val parentTypes = mutableListOf("ColumnDefBase<TData, TValue>")
            if ("ColumnIdentifiers<TData, TValue>" in body)
                parentTypes.add("ColumnIdentifiers<TData, TValue>")

            return ConversionResult(
                name,
                "external interface $declaration : ${parentTypes.joinToString(",")} {\n${members}\n}"
            )
        }

        declaration = declaration.replace(" : any", " : Any")

        if (name == "ColumnDef")
            declaration = declaration.replace("TValue : Any", "TValue")

        var interfaceBody = when (name) {
            "ColumnDefResolved",
            -> "ColumnDef<TData, TValue> /* $body */"

            else -> body
                .removeSurrounding("Partial<", ">")
                .splitToSequence(" & ")
                .map { it.removeSurrounding("Partial<", ">") }
                .joinToString(",\n") { parentType ->
                    when (parentType) {
                        "GroupingOptions",
                        "PaginationOptions",
                        -> parentType + "<TData>"

                        else -> parentType
                    }
                }
        }

        return ConversionResult(name, "external interface $declaration :\n$interfaceBody")
    }

    declaration = declaration
        .replace(": RowData>", "/* : RowData */>")
        .replace(": RowData, ", "/* : RowData */, ")

    body = body
        .replace("(...args: any) -> any", "Function<Unit>")
        .replace("Record<", "ReadonlyRecord<")
        .replace("string[]", "ReadonlyArray<String>")
        .replace("ColumnFilter[]", "ReadonlyArray<ColumnFilter>")
        .replace("ColumnSort[]", "ReadonlyArray<ColumnSort>")
        .replace("Row<TData>[]", "ReadonlyArray<Row<TData>>")
        .replace("column?: Column<TData, TValue>", "column: Column<TData, TValue>?")
        .replace("column?: Column<TData, unknown>", "column: Column<TData, *>?")
        .replace("string", "String")
        .replace("boolean", "Boolean")
        .replace("number", "Int")
        .replace(": any", ": Any")
        .replace(" -> any", " -> Any")
        .replace(" -> unknown", " -> Any")
        .replace("<any>", "<*>")
        .replace(" -> void", " -> Unit")

    return ConversionResult(name, "typealias $declaration = $body")
}

private fun convertInterface(
    source: String,
): ConversionResult {
    var declaration = source.substringBefore(" {")
        .replace(" = unknown>", ">")
        .removeSuffix(" =")
        .replace(" extends ", " : ")

    val name = declaration
        .substringBefore("<")
        .substringBefore(" ")

    when (name) {
        "TableOptions",
        -> declaration = declaration.replace(
            "PartialKeys<TableOptionsResolved<TData>, 'state' | 'onStateChange' | 'renderFallbackValue'>",
            "TableOptionsResolved<TData>",
        )

        "GroupingOptions",
        -> declaration = declaration
            .replaceFirst(name, "$name<TData : RowData>")
            .replaceFirst("GroupingOptionsBase,", "GroupingOptionsBase<TData>,")

        "GroupingOptionsBase",
        "PaginationOptions",
        "SortingOptionsBase",
        -> declaration += "<TData : RowData>"

        "SortingOptions",
        -> declaration = declaration
            .replace("SortingOptionsBase,", "SortingOptionsBase<TData>,")

        "FeatureOptions",
        -> declaration = declaration
            .replace("GroupingOptions,", "GroupingOptions<TData>,")
            .replace("PaginationOptions,", "PaginationOptions<TData>,")

        "HeaderContext",
        -> declaration = declaration.replace("<TData,", "<TData : RowData,")

        "InitialTableState",
        -> declaration = declaration.replace(
            "Partial<CompleteInitialTableState>",
            "CompleteInitialTableState"
        )
    }

    var members = convertMembers(source.substringAfter(" {"))
    if (name == "AccessorKeyColumnDefBase")
        members = members.replace("var id: String?", "    /* var id: String? */")

    val body = "{\n" + members + "\n}\n"
    return ConversionResult(name, "external interface $declaration$body")
}

private fun convertMembers(
    source: String,
): String {
    val content = source
        .removeSuffix("\n")
        .substringBefore(" & (keyof ")
        .substringBeforeLast("\n}")
        .trimIndent()

    if (content == "")
        return ""

    return content
        .splitToSequence("\n")
        .filter { !it.startsWith("_") }
        .map { it.removeSuffix(";") }
        .map { convertMember(it) }
        .joinToString("\n")
}

private fun convertMember(
    source: String,
): String {
    if (source.startsWith("("))
        return "    // TODO: support invoke\n    /* $source */"

    val optional = source.substringBefore(": ")
        .endsWith("?")

    val name = source.substringBefore(": ")
        .removeSuffix("?")

    var type = source.substringAfter(": ")
        .let { kotlinType(it, name) }

    if (optional) {
        type = if (type.startsWith("(")) {
            "($type)?"
        } else "$type?"
    }

    return "var $name: $type"
}
