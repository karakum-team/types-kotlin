package actions.artifact

import js.core.ReadonlyArray
import js.core.Void
import kotlin.js.Promise

external fun createEmptyFilesForArtifact(emptyFilesToCreate: ReadonlyArray<String>): Promise<Void>
