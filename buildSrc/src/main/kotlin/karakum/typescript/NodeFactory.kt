package karakum.typescript

private const val LINE =
    "createImportTypeNode(argument: TypeNode, assertions?: ImportTypeAssertionContainer, qualifier?: EntityName, typeArguments?: readonly TypeNode[], isTypeOf?: boolean): ImportTypeNode;"

private const val TEMP_TOKEN = "---111---222---111---"

internal fun String.addNodeFactoryWorkaround(): String {
    return replaceFirst(LINE, TEMP_TOKEN)
        .replaceFirst(LINE, "")
        .replaceFirst(TEMP_TOKEN, LINE)
}
