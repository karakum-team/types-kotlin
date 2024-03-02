// Automatically generated - do not modify!

package web.gpu

sealed external interface GPURenderEncoderBase {
    fun setPipeline(pipeline: GPURenderPipeline)
    fun setIndexBuffer(
        buffer: GPUBuffer,
        indexFormat: GPUIndexFormat,
        offset: Number = definedExternally,
        size: Int = definedExternally,
    )

    fun setVertexBuffer(
        slot: Number,
        buffer: GPUBuffer,
        offset: Number = definedExternally,
        size: Int = definedExternally,
    )

    fun draw(
        vertexCount: Int,
        instanceCount: Int = definedExternally,
        firstVertex: Number = definedExternally,
        firstInstance: Number = definedExternally,
    )

    fun drawIndexed(
        indexCount: Int,
        instanceCount: Int = definedExternally,
        firstIndex: Int = definedExternally,
        baseVertex: Number = definedExternally,
        firstInstance: Number = definedExternally,
    )

    fun drawIndirect(
        indirectBuffer: GPUBuffer,
        indirectOffset: Number,
    )

    fun drawIndexedIndirect(
        indirectBuffer: GPUBuffer,
        indirectOffset: Number,
    )
}
