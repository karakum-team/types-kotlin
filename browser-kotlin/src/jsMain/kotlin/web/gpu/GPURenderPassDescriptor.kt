// Automatically generated - do not modify!

package web.gpu

sealed external interface GPURenderPassDescriptor :
    GPUObjectDescriptorBase {
    var colorAttachments: (GPURenderPassColorAttachment | null)[]
    var depthStencilAttachment: GPURenderPassDepthStencilAttachment?
    var occlusionQuerySet: GPUQuerySet?
    var timestampWrites: GPURenderPassTimestampWrites?
}
