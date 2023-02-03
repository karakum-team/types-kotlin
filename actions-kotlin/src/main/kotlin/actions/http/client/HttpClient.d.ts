class HttpClient {
    userAgent: string | undefined;
    handlers: ifm.RequestHandler[];
    requestOptions: ifm.RequestOptions | undefined;

    constructor(userAgent?: string, handlers?: ifm.RequestHandler[], requestOptions?: ifm.RequestOptions);

    options(requestUrl: string, additionalHeaders?: http.OutgoingHttpHeaders): Promise<HttpClientResponse>;

    get(requestUrl: string, additionalHeaders?: http.OutgoingHttpHeaders): Promise<HttpClientResponse>;

    del(requestUrl: string, additionalHeaders?: http.OutgoingHttpHeaders): Promise<HttpClientResponse>;

    post(requestUrl: string, data: string, additionalHeaders?: http.OutgoingHttpHeaders): Promise<HttpClientResponse>;

    patch(requestUrl: string, data: string, additionalHeaders?: http.OutgoingHttpHeaders): Promise<HttpClientResponse>;

    put(requestUrl: string, data: string, additionalHeaders?: http.OutgoingHttpHeaders): Promise<HttpClientResponse>;

    head(requestUrl: string, additionalHeaders?: http.OutgoingHttpHeaders): Promise<HttpClientResponse>;

    sendStream(verb: string, requestUrl: string, stream: NodeJS.ReadableStream, additionalHeaders?: http.OutgoingHttpHeaders): Promise<HttpClientResponse>;

    /**
     * Gets a typed object from an endpoint
     * Be aware that not found returns a null.  Other errors (4xx, 5xx) reject the promise
     */
    getJson<T>(requestUrl: string, additionalHeaders?: http.OutgoingHttpHeaders): Promise<ifm.TypedResponse<T>>;

    postJson<T>(requestUrl: string, obj: any, additionalHeaders?: http.OutgoingHttpHeaders): Promise<ifm.TypedResponse<T>>;

    putJson<T>(requestUrl: string, obj: any, additionalHeaders?: http.OutgoingHttpHeaders): Promise<ifm.TypedResponse<T>>;

    patchJson<T>(requestUrl: string, obj: any, additionalHeaders?: http.OutgoingHttpHeaders): Promise<ifm.TypedResponse<T>>;

    /**
     * Makes a raw http request.
     * All other methods such as get, post, patch, and request ultimately call this.
     * Prefer get, del, post and patch
     */
    request(verb: string, requestUrl: string, data: string | NodeJS.ReadableStream | null, headers?: http.OutgoingHttpHeaders): Promise<HttpClientResponse>;

    /**
     * Needs to be called if keepAlive is set to true in request options.
     */
    dispose(): void;

    /**
     * Raw request.
     * @param info
     * @param data
     */
    requestRaw(info: ifm.RequestInfo, data: string | NodeJS.ReadableStream | null): Promise<HttpClientResponse>;

    /**
     * Raw request with callback.
     * @param info
     * @param data
     * @param onResult
     */
    requestRawWithCallback(info: ifm.RequestInfo, data: string | NodeJS.ReadableStream | null, onResult: (err?: Error, res?: HttpClientResponse) => void): void;

    /**
     * Gets an http agent. This function is useful when you need an http agent that handles
     * routing through a proxy server - depending upon the url and proxy environment variables.
     * @param serverUrl  The server URL where the request will be sent. For example, https://api.github.com
     */
    getAgent(serverUrl: string): http.Agent;
}
