package karakum.table

internal fun String.applyPatches(): String {
    return applyUnionPatches()
        .replace(
            "\n    meta?: TableMeta<TData>;",
            "\n    meta?: TableMeta<TData>;" +
                    "\n}" +
                    "\n\n" +
                    "export interface CoreOptionsResolved<TData extends RowData> extends CoreOptions<TData> {",
        )
        .let {
            val replacement = it
                .substringAfter("TableOptionsResolved<TData extends RowData> extends ")
                .substringBefore(" {\n")

            it.replaceFirst(
                "PartialKeys<TableOptionsResolved<TData>, 'state' | 'onStateChange' | 'renderFallbackValue'>",
                replacement,
            )
        }
        .replace(
            "TableOptionsResolved<TData extends RowData> extends CoreOptions<TData>",
            "TableOptionsResolved<TData extends RowData> extends CoreOptionsResolved<TData>",
        )
}

internal fun String.applyUnionPatches(): String =
    replaceFirst(
        "export type AccessorColumnDef<TData extends RowData, TValue = unknown> = AccessorKeyColumnDef<TData, TValue> | AccessorFnColumnDef<TData, TValue>;\n",
        ""
    )
        .replaceFirst(
            " | AccessorColumnDef<TData, TValue>;",
            " | AccessorKeyColumnDef<TData, TValue> | AccessorFnColumnDef<TData, TValue>;",
        )
        .applyUnionPatch("ColumnDef")

internal fun String.applyUnionPatch(
    interfaceName: String,
): String {
    val start = "type $interfaceName<"
    val body = substringAfter(start, "")
        .substringBefore(";\n", "")
        .substringAfter("> = ")

    var result = replaceFirst(start, "interface $interfaceName<")
        .replaceFirst(" = $body", " {\n}")

    val typeParameters = substringAfter(start, "")
        .substringBefore("> = ")
        .replaceFirst(" extends RowData", "")
        .replaceFirst(" = unknown", "")

    val declaration = "$interfaceName<$typeParameters>"

    body.splitToSequence(" | ")
        .map { it.substringBefore("<") }
        .forEach { childType ->
            val lineStart = "type $childType<"
            val line = lineStart + result
                .substringAfter(lineStart, "")
                .substringBefore(";\n")

            val newLine = line.replaceFirst("> = ", "> = $declaration & ")
            result = result.replaceFirst(line, newLine)
        }

    return result
}
