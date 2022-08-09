// Automatically generated - do not modify!

@file:JsModule("node:fs/promises")
@file:JsNonModule

package node.fs

/**
 * Truncates (shortens or extends the length) of the content at `path` to `len`bytes.
 * @since v10.0.0
 * @param [len=0]
 * @return Fulfills with `undefined` upon success.
 */
external fun truncate(
    path: PathLike,
    len: Number = definedExternally,
): kotlin.js.Promise<Unit>
