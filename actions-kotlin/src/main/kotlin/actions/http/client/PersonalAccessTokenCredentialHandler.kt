// Automatically generated - do not modify!

@file:JsModule("@actions/http-client")

package actions.http.client

import kotlin.js.Promise

external class PersonalAccessTokenCredentialHandler : RequestHandler {
    var token: String

    // constructor(token: string)
    fun prepareRequest(options: node.http.RequestOptions)
    fun canHandleAuthentication(): Boolean
    fun handleAuthentication(): Promise<HttpClientResponse>
}
