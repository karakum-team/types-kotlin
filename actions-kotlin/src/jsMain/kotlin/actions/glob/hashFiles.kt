// Automatically generated - do not modify!

package actions.glob

import js.promise.Promise
import js.promise.await
import js.collections.ReadonlyMap
import js.core.BigInt
import js.core.JsLong
import js.objects.Record
import js.array.ReadonlyArray
import js.core.Void
import js.errors.JsError
import node.buffer.Buffer
import node.http.IncomingHttpHeaders
import node.http.OutgoingHttpHeaders
import web.url.URL

import actions.http.client.HttpClient
import actions.http.client.HttpClientResponse

import seskar.js.JsIntValue
import seskar.js.JsVirtual
import seskar.js.JsValue

suspend fun hashFiles(
globber: Globber,
currentWorkspace: String,
) : String =
 hashFilesAsync(
globber = globber,
currentWorkspace = currentWorkspace,
).await()

suspend fun hashFiles(
globber: Globber,
currentWorkspace: String,
verbose: Boolean,
) : String =
 hashFilesAsync(
globber = globber,
currentWorkspace = currentWorkspace,
verbose = verbose,
).await()

suspend fun hashFiles(
patterns: String,
) : String =
 hashFilesAsync(
patterns = patterns,
).await()

suspend fun hashFiles(
patterns: String,
currentWorkspace: String,
) : String =
 hashFilesAsync(
patterns = patterns,
currentWorkspace = currentWorkspace,
).await()

suspend fun hashFiles(
patterns: String,
currentWorkspace: String,
options: HashFileOptions,
) : String =
 hashFilesAsync(
patterns = patterns,
currentWorkspace = currentWorkspace,
options = options,
).await()

suspend fun hashFiles(
patterns: String,
currentWorkspace: String,
options: HashFileOptions,
verbose: Boolean,
) : String =
 hashFilesAsync(
patterns = patterns,
currentWorkspace = currentWorkspace,
options = options,
verbose = verbose,
).await()
