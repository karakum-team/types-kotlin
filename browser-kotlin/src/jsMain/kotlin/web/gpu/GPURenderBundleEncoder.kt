// Automatically generated - do not modify!

package web.gpu

import js.array.ReadonlyArray
import js.typedarrays.Uint32Array

sealed external class GPURenderBundleEncoder :
    GPUObjectBase,
    GPUProgrammablePassEncoder,
    GPURenderEncoderBase {
    var label: String
    fun draw(
        vertexCount: Number,
        instanceCount: Number = definedExternally,
        firstVertex: Number = definedExternally,
        firstInstance: Number = definedExternally,
    )

    fun drawIndexed(
        indexCount: Number,
        instanceCount: Number = definedExternally,
        firstIndex: Number = definedExternally,
        baseVertex: Number = definedExternally,
        firstInstance: Number = definedExternally,
    )

    fun drawIndexedIndirect(
        indirectBuffer: GPUBuffer,
        indirectOffset: Number,
    )

    fun drawIndirect(
        indirectBuffer: GPUBuffer,
        indirectOffset: Number,
    )

    fun insertDebugMarker(markerLabel: String)
    fun popDebugGroup()
    fun pushDebugGroup(groupLabel: String)
    fun setBindGroup(
        index: Number,
        bindGroup: GPUBindGroup,
        dynamicOffsets: ReadonlyArray<Double> = definedExternally,
    )

    fun setBindGroup(
        index: Number,
        bindGroup: GPUBindGroup,
        dynamicOffsetsData: Uint32Array,
        dynamicOffsetsDataStart: Number,
        dynamicOffsetsDataLength: Number,
    )

    fun setIndexBuffer(
        buffer: GPUBuffer,
        indexFormat: GPUIndexFormat,
        offset: Number = definedExternally,
        size: Number = definedExternally,
    )

    fun setPipeline(pipeline: GPURenderPipeline)
    fun setVertexBuffer(
        slot: Number,
        buffer: GPUBuffer,
        offset: Number = definedExternally,
        size: Number = definedExternally,
    )

    fun finish(descriptor: GPURenderBundleDescriptor = definedExternally): GPURenderBundle
}
