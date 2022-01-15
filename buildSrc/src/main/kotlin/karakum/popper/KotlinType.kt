package karakum.popper

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

    "HTMLElement | string | null" to "org.w3c.dom.HTMLElement?",
    "boolean | RoundOffsets" to "Boolean /* boolean | RoundOffsets */",

    "() => void" to "() -> Unit",
    "() => Promise<Partial<State>>" to "() -> kotlin.js.Promise<State>",
    "(setOptionsAction: SetAction<Partial<OptionsGeneric<any>>>) => Promise<Partial<State>>" to
            "(SetAction<OptionsGeneric<*>>) -> kotlin.js.Promise<State>",

    "Element" to "org.w3c.dom.Element",
    "() => ClientRect | DOMRect" to "() -> org.w3c.dom.DOMRect /* () -> ClientRect */",

    "Partial<Options>" to "Options",

    "Array<string>" to "kotlinext.js.ReadonlyArray<String>",
    "Array<TModifier>" to "kotlinext.js.ReadonlyArray<TModifier>",
    "Array<Placement>" to "kotlinext.js.ReadonlyArray<popper.core.Placement>",
    "Array<Partial<Modifier<any, any>>>" to "kotlinext.js.ReadonlyArray<Modifier<*>>",
    "(arg0: Partial<State>) => void" to "UpdateCallback",

    "Name" to "ModifierName<Options>",
    "Obj" to "kotlinext.js.Record<String, *>",

    "Padding" to "popper.core.Padding",

    "(arg0: ModifierArguments<Options>) => State | void" to "(ModifierArguments<Options>) -> State?",
    "(arg0: ModifierArguments<Options>) => (() => void) | void" to "(ModifierArguments<Options>) -> (() -> Unit)?",
)

internal fun kotlinType(
    type: String,
    name: String,
): String {
    STANDARD_TYPE_MAP[type]
        ?.also { return it }

    when (type) {
        "Offset",
        "Boundary",
        "RootBoundary",
        "TetherOffset",
        -> return "$DYNAMIC /* $type */"
    }

    return type
}
