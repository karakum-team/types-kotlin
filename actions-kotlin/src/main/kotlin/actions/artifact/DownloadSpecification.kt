package actions.artifact

import js.core.ReadonlyArray

external interface DownloadSpecification {
    var rootDownloadLocation: String
    var directoryStructure: ReadonlyArray<String>
    var emptyFilesToCreate: ReadonlyArray<String>
    var filesToDownload: ReadonlyArray<DownloadItem>
}
