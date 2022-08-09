// Automatically generated - do not modify!

@file:JsModule("node:fs/promises")
@file:JsNonModule

package node.fs

/**
 * Changes the permissions of a file.
 * @since v10.0.0
 * @return Fulfills with `undefined` upon success.
 */
external fun chmod(
    path: PathLike,
    mode: Mode,
): kotlin.js.Promise<Unit>
