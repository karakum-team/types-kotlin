// Automatically generated - do not modify!

@file:Suppress(
    "NAME_CONTAINS_ILLEGAL_CHARS",
)

package typescript

/**
 * Represents a set of signature help items, and the preferred item that should be selected.
 */
// language=JavaScript
@JsName("""(/*union*/{Comment: 'comment', Region: 'region', Code: 'code', Imports: 'imports'}/*union*/)""")
external enum class OutliningSpanKind {
    /** Single or multi-line comments */
    Comment,

    /** Sections marked by '// #region' and '// #endregion' comments */
    Region,

    /** Declarations and expressions */
    Code,

    /** Contiguous blocks of import declarations */
    Imports,

    ;
}
