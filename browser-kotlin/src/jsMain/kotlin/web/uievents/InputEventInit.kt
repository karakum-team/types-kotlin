// Automatically generated - do not modify!

package web.uievents

import js.array.ReadonlyArray
import web.data.DataTransfer
import web.ranges.StaticRange

external interface InputEventInit :
    UIEventInit,
    InputEventOptions {
    override val data: String?
    override val dataTransfer: DataTransfer?
    override val inputType: String?
    override val isComposing: Boolean?
    override val targetRanges: ReadonlyArray<StaticRange>?
}
