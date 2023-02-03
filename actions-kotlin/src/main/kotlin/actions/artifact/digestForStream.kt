package actions.artifact

import kotlin.js.Promise

external fun digestForStream(stream: node.ReadableStream): Promise<StreamDigest>
