package karakum.browser

import java.io.File

private val EXCLUDED = setOf(
    "ClassAccessorDecoratorResult",
    "ClassAccessorDecoratorTarget",
)

private val TYPE_PARAMETER_MAP = mapOf(
    "This = unknown" to "This: Any",
    "Value = unknown" to "Value: Any",
    "Value extends (this: This, ...args: any) => any = (this: This, ...args: any) => any" to "Value: Function<*>",
    "Class extends abstract new (...args: any) => any = abstract new (...args: any) => any" to "Class: JsClass<*>",
)

internal fun decoratorsDeclarations(
    definitionsDir: File,
): Sequence<ConversionResult> {
    val content = decoratorsContent(definitionsDir)

    return content.splitToSequence("\n/**\n")
        .drop(1)
        .map { "/**\n$it".trim() }
        .filter { "\ninterface " in it }
        .mapNotNull { decoratorInterface(it) }
}

private fun decoratorInterface(
    source: String,
): ConversionResult? {
    val (comment, bodySource) = source.split("\ninterface ")
    val name = bodySource
        .substringBefore(" ")
        .substringBefore("<")

    if (name in EXCLUDED)
        return null

    val typeParameters = bodySource
        .substringAfter("<\n    ")
        .substringBefore(",\n>")
        .splitToSequence(",\n    ")
        .map { TYPE_PARAMETER_MAP.getValue(it) }
        .joinToString(", ", "<", ">")

    val members = bodySource
        .substringAfter("{\n")
        .substringBefore("\n}")
        .replace(
            "    readonly access: {\n",
            "    val access: Access<This, Value>" +
                    "\n\n" +
                    "interface Access$typeParameters {",
        )
        .replace("    readonly ", "    val ")
        .replace(": boolean;\n", ": Boolean\n")
        .replace(": string | symbol;\n", ": PropertyKey\n")
        .replace(": string | undefined;\n", ": String?\n")
        .replace(Regex("""(\n\s+)(\w+\()"""), "$1fun $2")
        .replace("): void;", ")")
        .replace(": (this: This) => void", ": (/* this: This */) -> Unit")
        .replace(": (this: Class) => void", ": (/* this: Class */) -> Unit")

    val body = comment +
            "\nsealed external interface $name$typeParameters {\n" +
            members +
            "\n}"

    return ConversionResult(
        name = name,
        body = body,
        pkg = "js.decorators",
    )
}

private fun decoratorsContent(
    definitionsDir: File,
): String =
    definitionsDir
        .resolve("lib.decorators.d.ts")
        .readText()
        .replace("\r\n", "\n")
        .substringAfter("""/// <reference no-default-lib="true"/>""")
