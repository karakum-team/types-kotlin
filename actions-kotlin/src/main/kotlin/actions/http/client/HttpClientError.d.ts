class HttpClientError extends Error {
    constructor(message: string, statusCode: number);

    statusCode: number;
    result?: any;
}
