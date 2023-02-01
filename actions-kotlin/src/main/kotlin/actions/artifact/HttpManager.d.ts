class HttpManager {
    private clients;
    private userAgent;

    constructor(clientCount: number, userAgent: string);

    getClient(index: number): HttpClient;

    disposeAndReplaceClient(index: number): void;

    disposeAndReplaceAllClients(): void;
}
