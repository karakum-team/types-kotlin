// Automatically generated - do not modify!

@file:JsModule("node:fs/promises")
@file:JsNonModule

package node.fs

/**
 * Removes files and directories (modeled on the standard POSIX `rm` utility).
 * @since v14.14.0
 * @return Fulfills with `undefined` upon success.
 */
external fun rm(
    path: PathLike,
    options: RmOptions = definedExternally,
): kotlin.js.Promise<Unit>
