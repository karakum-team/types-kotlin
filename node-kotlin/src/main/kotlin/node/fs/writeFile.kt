// Automatically generated - do not modify!

package node.fs

import kotlinx.coroutines.await

suspend fun writeFile(
    file: Any, /* PathLike | FileHandle */
    data: Any, /* string | NodeJS.ArrayBufferView | Iterable<string | NodeJS.ArrayBufferView> | AsyncIterable<string | NodeJS.ArrayBufferView> | Stream */
    options: Any?,
    /*         | (ObjectEncodingOptions & {
              mode?: Mode | undefined;
              flag?: OpenMode | undefined;
          } & Abortable)
        | BufferEncoding
        */
) {
    writeFileAsync(
        file = file,
        data = data,
        options = options,
    ).await()
}
