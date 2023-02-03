package actions.http.client

import web.url.URL

external interface RequestInfo {
    var options: node.http.RequestOptions
    var parsedUrl: URL
    var httpModule: typeof http | typeof https
}
