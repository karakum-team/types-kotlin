class StatusReporter {
    private totalNumberOfFilesToProcess;
    private processedCount;
    private displayFrequencyInMilliseconds;
    private largeFiles;
    private totalFileStatus;

    constructor(displayFrequencyInMilliseconds: number);

    setTotalNumberOfFilesToProcess(fileTotal: number): void;

    start(): void;

    updateLargeFileStatus(fileName: string, chunkStartIndex: number, chunkEndIndex: number, totalUploadFileSize: number): void;

    stop(): void;

    incrementProcessedCount(): void;

    private formatPercentage;
}
