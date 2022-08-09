// Automatically generated - do not modify!

@file:JsModule("node:fs/promises")
@file:JsNonModule

package node.fs

/**
 * Changes the permissions on a symbolic link.
 *
 * This method is only implemented on macOS.
 * @deprecated Since v10.0.0
 * @return Fulfills with `undefined` upon success.
 */
external fun lchmod(
    path: PathLike,
    mode: Mode,
): kotlin.js.Promise<Unit>
