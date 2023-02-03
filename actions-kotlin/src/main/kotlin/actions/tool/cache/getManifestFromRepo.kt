package actions.tool.cache

import kotlin.js.Promise

external fun getManifestFromRepo(
    owner: String,
    repo: String,
    auth: String = definedExternally,
    branch: String = definedExternally,
): Promise<IToolRelease[]>
