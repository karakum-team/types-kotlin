// Automatically generated - do not modify!

package typescript

/**
 * Node in a tree of nested declarations in a file.
 * The top node is always a script or module node.
 */
external interface CodeAction {
    /*
    /** Description of the code action to display in the UI of the editor */
    description: string;
    /** Text changes to apply to each file as part of the code action */
    changes: FileTextChanges[];
    /**
     * If the user accepts the code fix, the editor should send the action back in a `applyAction` request.
     * This allows the language service to have side effects (e.g. installing dependencies) upon a code fix.
     */
    commands?: CodeActionCommand[];
    */
}
