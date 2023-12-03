// Automatically generated - do not modify!

@file:Suppress(
    "NOTHING_TO_INLINE",
)

package web.messaging

import js.buffer.ArrayBuffer

// OffscreenCanvas | ImageBitmap | MessagePort | ReadableStream | WritableStream | TransformStream | VideoFrame | ArrayBuffer    
external interface Transferable

inline fun ArrayBuffer.asTransferable(): Transferable =
    unsafeCast<Transferable>()

inline fun Transferable.asArrayBufferOrNull(): ArrayBuffer? =
    asDynamic() as? ArrayBuffer    
