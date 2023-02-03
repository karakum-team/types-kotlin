package actions.artifact

import js.core.ReadonlyArray

external interface QueryArtifactResponse {
    var count: Number
    var value: ReadonlyArray<ContainerEntry>
}
