package actions.artifact

import js.core.ReadonlyArray
import js.core.Void
import kotlin.js.Promise

external fun createDirectoriesForArtifact(directories: ReadonlyArray<String>): Promise<Void>
