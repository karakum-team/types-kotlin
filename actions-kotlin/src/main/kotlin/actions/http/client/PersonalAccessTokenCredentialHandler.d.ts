class PersonalAccessTokenCredentialHandler implements ifm.RequestHandler {
    token: string;

    constructor(token: string);

    prepareRequest(options: http.RequestOptions): void;

    canHandleAuthentication(): boolean;

    handleAuthentication(): Promise<HttpClientResponse>;
}
