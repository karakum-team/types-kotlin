package actions.artifact

external interface PatchArtifactSizeSuccessResponse {
    var containerId: Number
    var size: Number
    var signedContent: String
    var type: String
    var name: String
    var url: String
    var uploadUrl: String
}
