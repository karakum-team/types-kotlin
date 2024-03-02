// Automatically generated - do not modify!

package web.gpu

import js.array.ReadonlyArray
import js.typedarrays.Uint32Array

sealed external class GPURenderBundleEncoder :
    GPUObjectBase,
    GPUProgrammablePassEncoder,
    GPURenderEncoderBase {
    override var label: String
    override fun draw(
        vertexCount: Number,
        instanceCount: Number = definedExternally,
        firstVertex: Number = definedExternally,
        firstInstance: Number = definedExternally,
    )

    override fun drawIndexed(
        indexCount: Number,
        instanceCount: Number = definedExternally,
        firstIndex: Number = definedExternally,
        baseVertex: Number = definedExternally,
        firstInstance: Number = definedExternally,
    )

    override fun drawIndexedIndirect(
        indirectBuffer: GPUBuffer,
        indirectOffset: Number,
    )

    override fun drawIndirect(
        indirectBuffer: GPUBuffer,
        indirectOffset: Number,
    )

    override fun insertDebugMarker(markerLabel: String)
    override fun popDebugGroup()
    override fun pushDebugGroup(groupLabel: String)
    override fun setBindGroup(
        index: Number,
        bindGroup: GPUBindGroup,
        dynamicOffsets: ReadonlyArray<Double> = definedExternally,
    )

    override fun setBindGroup(
        index: Number,
        bindGroup: GPUBindGroup,
        dynamicOffsetsData: Uint32Array,
        dynamicOffsetsDataStart: Number,
        dynamicOffsetsDataLength: Number,
    )

    override fun setIndexBuffer(
        buffer: GPUBuffer,
        indexFormat: GPUIndexFormat,
        offset: Number = definedExternally,
        size: Number = definedExternally,
    )

    override fun setPipeline(pipeline: GPURenderPipeline)
    override fun setVertexBuffer(
        slot: Number,
        buffer: GPUBuffer,
        offset: Number = definedExternally,
        size: Number = definedExternally,
    )

    fun finish(descriptor: GPURenderBundleDescriptor = definedExternally): GPURenderBundle
}
