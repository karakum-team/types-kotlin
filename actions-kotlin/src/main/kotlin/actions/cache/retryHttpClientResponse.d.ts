function retryHttpClientResponse(
    name: string,
    method: () => Promise<HttpClientResponse>,
    maxAttempts?: number,
    delay?: number,
): Promise<HttpClientResponse>;
