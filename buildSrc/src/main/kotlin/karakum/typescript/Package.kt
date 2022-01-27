package karakum.typescript

internal enum class Package(
    id: String,
) {
    TYPESCRIPT("typescript"),
    TYPESCRIPT_RAW("typescript.raw"),

    ;

    val pkg = "package $id"
    val path = id.replace(".", "/")
}
