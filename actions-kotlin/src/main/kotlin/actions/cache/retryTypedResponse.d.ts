function retryTypedResponse<T>(
    name: string,
    method: () => Promise<ITypedResponseWithError<T>>,
    maxAttempts?: number,
    delay?: number,
): Promise<ITypedResponseWithError<T>>;
