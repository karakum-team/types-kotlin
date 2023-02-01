class HttpClientResponse {
    constructor(message: http.IncomingMessage);

    message: http.IncomingMessage;

    readBody(): Promise<string>;
}
