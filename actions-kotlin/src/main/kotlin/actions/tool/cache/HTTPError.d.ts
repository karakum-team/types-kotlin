class HTTPError extends Error {
    readonly httpStatusCode: number | undefined;

    constructor(httpStatusCode: number | undefined);
}
