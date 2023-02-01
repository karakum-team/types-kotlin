interface TypedResponse<T> {
    statusCode: number;
    result: T | null;
    headers: http.IncomingHttpHeaders;
}