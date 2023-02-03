// Automatically generated - do not modify!

package actions.tool.cache

import js.core.ReadonlyArray
import kotlin.js.Promise

external fun getManifestFromRepo(
    owner: String,
    repo: String,
    auth: String = definedExternally,
    branch: String = definedExternally,
): Promise<ReadonlyArray<IToolRelease>>
