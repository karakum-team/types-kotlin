package actions.artifact

import js.core.ReadonlyArray
import kotlin.js.Promise

external fun createEmptyFilesForArtifact(emptyFilesToCreate: ReadonlyArray<String>): Promise<void>
