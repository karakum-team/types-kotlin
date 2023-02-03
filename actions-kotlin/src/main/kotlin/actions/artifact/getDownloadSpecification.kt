package actions.artifact

import js.core.ReadonlyArray

external fun getDownloadSpecification(
    artifactName: String,
    artifactEntries: ReadonlyArray<ContainerEntry>,
    downloadPath: String,
    includeRootDirectory: Boolean,
): DownloadSpecification
