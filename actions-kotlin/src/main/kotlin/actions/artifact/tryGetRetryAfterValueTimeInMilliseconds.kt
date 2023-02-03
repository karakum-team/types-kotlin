package actions.artifact

import node.http.IncomingHttpHeaders

external fun tryGetRetryAfterValueTimeInMilliseconds(headers: IncomingHttpHeaders): Number?
