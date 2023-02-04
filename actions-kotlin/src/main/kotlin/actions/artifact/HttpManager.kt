// Automatically generated - do not modify!

@file:JsModule("@actions/artifact")

package actions.artifact

import actions.http.client.HttpClient

external class HttpManager {
    // constructor(clientCount: number, userAgent: string)
    fun getClient(index: Number): HttpClient
    fun disposeAndReplaceClient(index: Number)
    fun disposeAndReplaceAllClients()
}
