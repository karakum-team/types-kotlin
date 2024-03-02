// Automatically generated - do not modify!

package web.gpu

import js.array.ReadonlyArray
import js.typedarrays.Uint32Array

sealed external class GPUComputePassEncoder :
    GPUObjectBase,
    GPUProgrammablePassEncoder {
    var label: String
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

    fun pushDebugGroup(groupLabel: String)
    fun popDebugGroup()
    fun insertDebugMarker(markerLabel: String)
    fun setPipeline(pipeline: GPUComputePipeline)
    fun dispatchWorkgroups(
        x: Number,
        y: Number = definedExternally,
        z: Number = definedExternally,
    )

    fun dispatchWorkgroupsIndirect(
        indirectBuffer: GPUBuffer,
        indirectOffset: Number,
    )

    fun end()
}
