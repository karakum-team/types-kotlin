package actions.tool.cache

import kotlin.js.Promise

external fun downloadTool(
    url: String,
    dest: String = definedExternally,
    auth: String = definedExternally,
    headers: OutgoingHttpHeaders = definedExternally,
): Promise<string>
