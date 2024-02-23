// Automatically generated - do not modify!

package typescript

import js.array.ReadonlyArray

/**
 * A set of edits to make in response to a refactor action, plus an optional
 * location where renaming should be invoked from
 */
external sealed interface RefactorEditInfo {
var edits: ReadonlyArray<FileTextChanges>
var renameFilename: String?
var renameLocation: Int?
var commands: ReadonlyArray<CodeActionCommand>?
var notApplicableReason: String?
}
