// Automatically generated - do not modify!

package web.gpu

import js.promise.Promise

sealed external class GPU {
    @JsName("requestAdapter")
    fun requestAdapterAsync(options: GPURequestAdapterOptions = definedExternally): Promise<GPUAdapter?>
    fun getPreferredCanvasFormat(): GPUTextureFormat
}
