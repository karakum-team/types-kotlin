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
        vertexCount: Int,
        instanceCount: Int,
        firstVertex: Number,
        firstInstance: Number,
    )

    override fun drawIndexed(
        indexCount: Int,
        instanceCount: Int,
        firstIndex: Int,
        baseVertex: Number,
        firstInstance: Number,
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
        index: Int,
        bindGroup: GPUBindGroup,
        dynamicOffsets: ReadonlyArray<Double>,
    )

    override fun setBindGroup(
        index: Int,
        bindGroup: GPUBindGroup,
        dynamicOffsetsData: Uint32Array,
        dynamicOffsetsDataStart: Number,
        dynamicOffsetsDataLength: Number,
    )

    override fun setIndexBuffer(
        buffer: GPUBuffer,
        indexFormat: GPUIndexFormat,
        offset: Number,
        size: Int,
    )

    override fun setPipeline(pipeline: GPURenderPipeline)
    override fun setVertexBuffer(
        slot: Number,
        buffer: GPUBuffer,
        offset: Number,
        size: Int,
    )

    fun finish(descriptor: GPURenderBundleDescriptor = definedExternally): GPURenderBundle
}
