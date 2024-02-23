// Automatically generated - do not modify!

package web.uievents

import js.array.ReadonlyArray
import js.objects.JsPlainObject
import web.data.DataTransfer
import web.events.Event
import web.events.EventInit
import web.ranges.Range
import web.ranges.StaticRange

@JsPlainObject
external interface InputEventInit :
UIEventInit  {
val data: String?
val dataTransfer: DataTransfer?
val inputType: String?
val isComposing: Boolean?
val targetRanges: ReadonlyArray<StaticRange>?
}
