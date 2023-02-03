package actions.tool.cache

import node.http.OutgoingHttpHeaders
import kotlin.js.Promise

external fun downloadTool(
    url: String,
    dest: String = definedExternally,
    auth: String = definedExternally,
    headers: OutgoingHttpHeaders = definedExternally,
): Promise<String>
