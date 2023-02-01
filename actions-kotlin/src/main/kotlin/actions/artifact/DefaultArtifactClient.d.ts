class DefaultArtifactClient implements ArtifactClient {
    /**
     * Constructs a DefaultArtifactClient
     */
    static create(): DefaultArtifactClient;

    /**
     * Uploads an artifact
     */
    uploadArtifact(name: string, files: string[], rootDirectory: string, options?: UploadOptions | undefined): Promise<UploadResponse>;

    downloadArtifact(name: string, path?: string | undefined, options?: DownloadOptions | undefined): Promise<DownloadResponse>;

    downloadAllArtifacts(path?: string | undefined): Promise<DownloadResponse[]>;
}
