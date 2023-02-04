// Automatically generated - do not modify!

@file:JsModule("@actions/artifact")

package actions.artifact

external class StatusReporter {
    // private totalNumberOfFilesToProcess
    // private processedCount
    // private displayFrequencyInMilliseconds
    // private largeFiles
    // private totalFileStatus
    // constructor(displayFrequencyInMilliseconds: number)
    fun setTotalNumberOfFilesToProcess(fileTotal: Number)
    fun start()
    fun updateLargeFileStatus(
        fileName: String,
        chunkStartIndex: Number,
        chunkEndIndex: Number,
        totalUploadFileSize: Number,
    )

    fun stop()
    fun incrementProcessedCount()
    // private formatPercentage
}
