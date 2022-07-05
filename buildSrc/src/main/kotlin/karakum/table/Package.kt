package karakum.table

internal enum class Package(
    id: String,
) {
    TABLE_CORE("tanstack.table.core"),
    REACT_TABLE("tanstack.react.table"),

    ;

    val pkg = "package $id"
    val path = id.replace(".", "/")
}
