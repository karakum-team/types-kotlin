// Automatically generated - do not modify!

@file:Suppress(
    "NAME_CONTAINS_ILLEGAL_CHARS",
)

package typescript

/**
 * A linked list of formatted diagnostic messages to be used as part of a multiline message.
 * It is built from the bottom up, leaving the head to be the "main" diagnostic.
 * While it seems that DiagnosticMessageChain is structurally similar to DiagnosticMessage,
 * the difference is that messages are all preformatted in DMC.
 */
// language=JavaScript
@JsName("""(/*union*/{None: 0, CommonJS: 1, AMD: 2, UMD: 3, System: 4, ES2015: 5, ES2020: 6, ES2022: 7, ESNext: 99, Node12: 100, NodeNext: 199}/*union*/)""")
external enum class ModuleKind {
    None,
    CommonJS,
    AMD,
    UMD,
    System,
    ES2015,
    ES2020,
    ES2022,
    ESNext,
    Node12,
    NodeNext,

    ;
}
