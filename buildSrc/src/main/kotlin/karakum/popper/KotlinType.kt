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

    "HTMLElement | string | null" to "dom.html.HTMLElement?",
    "boolean | RoundOffsets" to "Boolean /* boolean | RoundOffsets */",

    "() => void" to "() -> Unit",
    "() => Promise<Partial<State>>" to "() -> Promise<State>",
    "(setOptionsAction: SetAction<Partial<OptionsGeneric<any>>>) => Promise<Partial<State>>" to
            "(SetAction<OptionsGeneric<*>>) -> Promise<State>",

    "Element" to "dom.Element",
    "() => ClientRect | DOMRect" to "() -> org.w3c.dom.DOMRect",

    "Partial<Options>" to "Options",

    "Array<string>" to "ReadonlyArray<String>",
    "Array<TModifier>" to "ReadonlyArray<TModifier>",
    "Array<Placement>" to "ReadonlyArray<popper.core.Placement>",
    "Array<Partial<Modifier<any, any>>>" to "ReadonlyArray<Modifier<*>>",
    "(arg0: Partial<State>) => void" to "UpdateCallback",

    "Name" to "ModifierName<Options>",
    "Obj" to "Record<String, *>",

    "Padding" to "popper.core.Padding",

    "(arg0: ModifierArguments<Options>) => State | void" to "(ModifierArguments<Options>) -> State?",
    "(arg0: ModifierArguments<Options>) => (() => void) | void" to "(ModifierArguments<Options>) -> (() -> Unit)?",
)

internal val SYNTHETIC_TYPE_MAP = mapOf(
    "Offset" to OFFSET_TYPE,
    "TetherOffset" to TETHER_OFFSET_TYPE,
)

internal fun kotlinType(
    type: String,
    name: String,
): String {
    STANDARD_TYPE_MAP[type]
        ?.also { return it }

    SYNTHETIC_TYPE_MAP[type]
        ?.also { return it }

    when (type) {
        "Boundary",
        "RootBoundary",
        -> return "$DYNAMIC /* $type */"
    }

    return type
}
