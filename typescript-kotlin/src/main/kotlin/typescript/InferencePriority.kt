// Automatically generated - do not modify!

@file:Suppress(
    "NAME_CONTAINS_ILLEGAL_CHARS",
)

package typescript

/**
 * Type references (ObjectFlags.Reference). When a class or interface has type parameters or
 * a "this" type, references to the class or interface are made using type references. The
 * typeArguments property specifies the types to substitute for the type parameters of the
 * class or interface and optionally includes an extra element that specifies the type to
 * substitute for "this" in the resulting instantiation. When no extra argument is present,
 * the type reference itself is substituted for "this". The typeArguments property is undefined
 * if the class or interface has no type parameters and the reference isn't specifying an
 * explicit "this" argument.
 */
// language=JavaScript
@JsName("""(/*union*/{NakedTypeVariable: 1, SpeculativeTuple: 2, SubstituteSource: 4, HomomorphicMappedType: 8, PartialHomomorphicMappedType: 16, MappedTypeConstraint: 32, ContravariantConditional: 64, ReturnType: 128, LiteralKeyof: 256, NoConstraints: 512, AlwaysStrict: 1024, MaxValue: 2048, PriorityImpliesCombination: 416, Circularity: -1}/*union*/)""")
external enum class InferencePriority {
    NakedTypeVariable,
    SpeculativeTuple,
    SubstituteSource,
    HomomorphicMappedType,
    PartialHomomorphicMappedType,
    MappedTypeConstraint,
    ContravariantConditional,
    ReturnType,
    LiteralKeyof,
    NoConstraints,
    AlwaysStrict,
    MaxValue,
    PriorityImpliesCombination,
    Circularity,

    ;
}
