class BasicCredentialHandler implements ifm.RequestHandler {
    username: string;
    password: string;

    constructor(username: string, password: string);

    prepareRequest(options: http.RequestOptions): void;

    canHandleAuthentication(): boolean;

    handleAuthentication(): Promise<HttpClientResponse>;
}
