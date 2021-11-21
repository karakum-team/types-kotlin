package com.github.turansky.popper

internal const val DYNAMIC = "dynamic"
internal const val UNIT = "Unit"

internal const val STRING = "String"

internal const val INT = "Int"
internal const val DOUBLE = "Double"

private val STANDARD_TYPE_MAP = mapOf(
    "any" to "Any",
    "object" to "Any",

    "boolean" to "Boolean",
    "string" to STRING,
    "number" to DOUBLE,

    "void" to UNIT,
    "null" to "Nothing?",

    "Element" to "org.w3c.dom.Element",
    "() => ClientRect | DOMRect" to "() -> org.w3c.dom.DOMRect /* () -> ClientRect */",

    "Partial<Options>" to "Options",

    "Array<string>" to "kotlinext.js.ReadonlyArray<String>",
    "Array<Partial<Modifier<any, any>>>" to "kotlinext.js.ReadonlyArray<Modifier<*>>",
    "(arg0: Partial<State>) => void" to "(State) -> Unit",

    "Name" to "ModifierName<Options>",
    "Obj" to "kotlinext.js.Record<String, *>",

    "(arg0: ModifierArguments<Options>) => State | void" to "(ModifierArguments<Options>) -> State?",
    "(arg0: ModifierArguments<Options>) => (() => void) | void" to "(ModifierArguments<Options>) -> (() -> Unit)?",
)

internal fun kotlinType(
    type: String,
    name: String,
): String {
    STANDARD_TYPE_MAP[type]
        ?.also { return it }

    return type
}
