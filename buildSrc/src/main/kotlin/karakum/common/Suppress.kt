package karakum.common

internal enum class Suppress {
    DECLARATION_CANT_BE_INLINED,
    VAR_TYPE_MISMATCH_ON_OVERRIDE,
    NOTHING_TO_INLINE,

    INTERFACE_WITH_SUPERCLASS,

    CANNOT_CHECK_FOR_EXTERNAL_INTERFACE,
    CANNOT_CHECK_FOR_ERASED,
    ERROR_IN_CONTRACT_DESCRIPTION,

    UNUSED_TYPEALIAS_PARAMETER,
    BOUNDS_NOT_ALLOWED_IF_BOUNDED_BY_TYPE_PARAMETER,

    VIRTUAL_MEMBER_HIDDEN,

    ABSTRACT_MEMBER_NOT_IMPLEMENTED,
    SEALED_INHERITOR_IN_DIFFERENT_PACKAGE,

    EXTERNAL_CLASS_CONSTRUCTOR_PROPERTY_PARAMETER,

    NON_EXTERNAL_DECLARATION_IN_INAPPROPRIATE_FILE,
    VAR_OVERRIDDEN_BY_VAL,

    NATIVE_INDEXER_KEY_SHOULD_BE_STRING_OR_NUMBER,

    NON_ABSTRACT_MEMBER_OF_EXTERNAL_INTERFACE,

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
