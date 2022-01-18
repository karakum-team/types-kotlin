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
