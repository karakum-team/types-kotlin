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
@JsName("""(/*union*/{ES3: 0, ES5: 1, ES2015: 2, ES2016: 3, ES2017: 4, ES2018: 5, ES2019: 6, ES2020: 7, ES2021: 8, ESNext: 99, JSON: 100, Latest: 99}/*union*/)""")
external enum class ScriptTarget {
    ES3,
    ES5,
    ES2015,
    ES2016,
    ES2017,
    ES2018,
    ES2019,
    ES2020,
    ES2021,
    ESNext,
    JSON,
    Latest,

    ;
}
