class DownloadProgress {
    contentLength: number;
    segmentIndex: number;
    segmentSize: number;
    segmentOffset: number;
    receivedBytes: number;
    startTime: number;
    displayedComplete: boolean;
    timeoutHandle?: ReturnType<typeof setTimeout>;

    constructor(contentLength: number);

    /**
     * Progress to the next segment. Only call this method when the previous segment
     * is complete.
     *
     * @param segmentSize the length of the next segment
     */
    nextSegment(segmentSize: number): void;

    /**
     * Sets the number of bytes received for the current segment.
     *
     * @param receivedBytes the number of bytes received
     */
    setReceivedBytes(receivedBytes: number): void;

    /**
     * Returns the total number of bytes transferred.
     */
    getTransferredBytes(): number;

    /**
     * Returns true if the download is complete.
     */
    isDone(): boolean;

    /**
     * Prints the current download stats. Once the download completes, this will print one
     * last line and then stop.
     */
    display(): void;

    /**
     * Returns a function used to handle TransferProgressEvents.
     */
    onProgress(): (progress: TransferProgressEvent) => void;

    /**
     * Starts the timer that displays the stats.
     *
     * @param delayInMs the delay between each write
     */
    startDisplayTimer(delayInMs?: number): void;

    /**
     * Stops the timer that displays the stats. As this typically indicates the download
     * is complete, this will display one last line, unless the last line has already
     * been written.
     */
    stopDisplayTimer(): void;
}
