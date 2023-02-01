interface RequestInfo {
    options: http.RequestOptions;
    parsedUrl: URL;
    httpModule: typeof http | typeof https;
}