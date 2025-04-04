// Automatically generated - do not modify!

package web.images

import js.promise.Promise
import seskar.js.JsAsync
import kotlin.js.JsName
import kotlin.js.definedExternally

/**
 * [MDN Reference](https://developer.mozilla.org/docs/Web/API/Window/createImageBitmap)
 */
@JsAsync
@Suppress("WRONG_EXTERNAL_DECLARATION")
external suspend fun createImageBitmap(
    image: ImageBitmapSource,
    options: ImageBitmapOptions? = definedExternally,
): ImageBitmap

@JsName("createImageBitmap")
external fun createImageBitmapAsync(
    image: ImageBitmapSource,
    options: ImageBitmapOptions? = definedExternally,
): Promise<ImageBitmap>

@JsAsync
@Suppress("WRONG_EXTERNAL_DECLARATION")
external suspend fun createImageBitmap(
    image: ImageBitmapSource,
    sx: Int,
    sy: Int,
    sw: Int,
    sh: Int,
    options: ImageBitmapOptions? = definedExternally,
): ImageBitmap

@JsName("createImageBitmap")
external fun createImageBitmapAsync(
    image: ImageBitmapSource,
    sx: Int,
    sy: Int,
    sw: Int,
    sh: Int,
    options: ImageBitmapOptions? = definedExternally,
): Promise<ImageBitmap>
