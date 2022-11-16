package karakum.common

internal enum class Suppress {
    EXTERNAL_TYPE_EXTENDS_NON_EXTERNAL_TYPE,
    DECLARATION_CANT_BE_INLINED,
    VAR_TYPE_MISMATCH_ON_OVERRIDE,
    NOTHING_TO_INLINE,

    NAME_CONTAINS_ILLEGAL_CHARS,
    NESTED_CLASS_IN_EXTERNAL_INTERFACE,

    INTERFACE_WITH_SUPERCLASS,
    FINAL_SUPERTYPE,

    PARAMETER_NAME_CHANGED_ON_OVERRIDE,
    DIFFERENT_NAMES_FOR_THE_SAME_PARAMETER_IN_SUPERTYPES,

    CANNOT_CHECK_FOR_EXTERNAL_INTERFACE,
    CANNOT_CHECK_FOR_ERASED,
    ERROR_IN_CONTRACT_DESCRIPTION,

    UNUSED_TYPEALIAS_PARAMETER,

    VIRTUAL_MEMBER_HIDDEN,

    // TODO: remove after unions
    USELESS_IS_CHECK,

    ;
}

internal fun fileSuppress(
    vararg suppresses: Suppress,
): String =
    suppresses.map { """"${it.name}"""" }
        .joinToString(",\n", "@file:Suppress(\n", ",\n)")

internal fun suppress(
    suppress: Suppress,
): String =
    """@Suppress("${suppress.name}")"""
