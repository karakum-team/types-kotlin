// Automatically generated - do not modify!

package web.gpu

import js.array.ReadonlyArray
import js.promise.Promise

sealed external class GPUAdapter {
    val features: GPUSupportedFeatures
    val limits: GPUSupportedLimits
    val isFallbackAdapter: Boolean

    @JsName("requestDevice")
    fun requestDeviceAsync(descriptor: GPUDeviceDescriptor = definedExternally): Promise<GPUDevice>

    @JsName("requestAdapterInfo")
    fun requestAdapterInfoAsync(unmaskHints: ReadonlyArray<String> = definedExternally): Promise<GPUAdapterInfo>
}
