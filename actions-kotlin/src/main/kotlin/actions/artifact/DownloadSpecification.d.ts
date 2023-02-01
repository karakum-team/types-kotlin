interface DownloadSpecification {
    rootDownloadLocation: string;
    directoryStructure: string[];
    emptyFilesToCreate: string[];
    filesToDownload: DownloadItem[];
}