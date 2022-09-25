// Automatically generated - do not modify!

package node.stream.web

import kotlinx.js.ArrayBufferView

sealed external interface ByteLengthQueuingStrategy : QueuingStrategy<ArrayBufferView> {
    val highWaterMark: Number
    val size: QueuingStrategySize<ArrayBufferView>
}
