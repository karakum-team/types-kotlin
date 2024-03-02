// Automatically generated - do not modify!

package web.gpu

sealed external interface GPURenderPassLayout :
    GPUObjectDescriptorBase {
    var colorFormats: (GPUTextureFormat | null)[]
    var depthStencilFormat: GPUTextureFormat?
    var sampleCount: Number?
}
