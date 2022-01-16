// Automatically generated - do not modify!

@file:Suppress(
    "NAME_CONTAINS_ILLEGAL_CHARS",
)

package typescript

/**
 * Represents an immutable snapshot of a script at a specified time.Once acquired, the
 * snapshot is observably immutable. i.e. the same calls with the same parameters will return
 * the same values.
 */
// language=JavaScript
@JsName("""(/*union*/{Invoked: 1, TriggerCharacter: 2, TriggerForIncompleteCompletions: 3}/*union*/)""")
external enum class CompletionTriggerKind {
    /** Completion was triggered by typing an identifier, manual invocation (e.g Ctrl+Space) or via API. */
    Invoked,

    /** Completion was triggered by a trigger character. */
    TriggerCharacter,

    /** Completion was re-triggered as the current completion list is incomplete. */
    TriggerForIncompleteCompletions,

    ;
}
