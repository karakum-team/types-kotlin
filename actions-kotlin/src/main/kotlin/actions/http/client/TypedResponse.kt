package actions.http.client

external interface TypedResponse<T> {
    var statusCode: Number
    var result: T | null
    var headers: node.http.IncomingHttpHeaders
}
