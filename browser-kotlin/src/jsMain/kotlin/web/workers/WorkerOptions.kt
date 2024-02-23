// Automatically generated - do not modify!

package web.workers

import js.objects.JsPlainObject
import web.http.Request
import web.http.RequestCredentials
import web.workers.Worker
import web.workers.WorkerType

@JsPlainObject
sealed external interface WorkerOptions {
var credentials: RequestCredentials?
var name: String?
var type: WorkerType?
}
