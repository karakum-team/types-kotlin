// Automatically generated - do not modify!

@file:JsModule("node:fs/promises")
@file:JsNonModule

package node.fs

/**
 * Changes the ownership on a symbolic link.
 * @since v10.0.0
 * @return Fulfills with `undefined` upon success.
 */
external fun lchown(
    path: PathLike,
    uid: Number,
    gid: Number,
): kotlin.js.Promise<Unit>
