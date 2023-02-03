package actions.artifact

external interface ListArtifactsResponse {
    var count: Number
    var value: ArtifactResponse[]
}
