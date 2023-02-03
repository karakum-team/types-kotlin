package actions.cache

import kotlin.js.Promise

external fun unlinkFile(filePath: node.fs.PathLike): Promise<void>
