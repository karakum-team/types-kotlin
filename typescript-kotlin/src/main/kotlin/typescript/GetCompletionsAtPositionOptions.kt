// Automatically generated - do not modify!

package typescript

/**
 * Represents an immutable snapshot of a script at a specified time.Once acquired, the
 * snapshot is observably immutable. i.e. the same calls with the same parameters will return
 * the same values.
 */
external interface GetCompletionsAtPositionOptions : UserPreferences {
    /*
    /**
     * If the editor is asking for completions because a certain character was typed
     * (as opposed to when the user explicitly requested them) this should be set.
     */
    triggerCharacter?: CompletionsTriggerCharacter;
    triggerKind?: CompletionTriggerKind;
    /** @deprecated Use includeCompletionsForModuleExports */
    includeExternalModuleExports?: boolean;
    /** @deprecated Use includeCompletionsWithInsertText */
    includeInsertTextCompletions?: boolean;
    */
}
