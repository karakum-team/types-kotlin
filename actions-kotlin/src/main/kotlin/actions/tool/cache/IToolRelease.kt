package actions.tool.cache

external interface IToolRelease {
    var version: String
    var stable: Boolean
    var release_url: String
    var files: IToolReleaseFile[]
}
