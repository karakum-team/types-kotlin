package karakum.table

internal fun String.applyPatches(): String {
    return replace(
        "\n        meta?: TableMeta<TData>;",
        "\n        meta?: TableMeta<TData>;" +
                "\n}" +
                "\n\n" +
                "export interface CoreOptionsResolved<TData extends RowData> extends CoreOptions<TData>",
    )
        .replace(
            "TableOptionsResolved<TData extends RowData> extends CoreOptions<TData>",
            "TableOptionsResolved<TData extends RowData> extends CoreOptionsResolved<TData>",
        )
}
