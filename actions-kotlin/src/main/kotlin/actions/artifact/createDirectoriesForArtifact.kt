package actions.artifact

import js.core.ReadonlyArray
import kotlin.js.Promise

external fun createDirectoriesForArtifact(directories: ReadonlyArray<String>): Promise<void>
