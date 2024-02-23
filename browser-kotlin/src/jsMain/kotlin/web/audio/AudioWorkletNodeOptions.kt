// Automatically generated - do not modify!

package web.audio

import js.array.ReadonlyArray
import js.objects.JsPlainObject
import js.objects.ReadonlyRecord
import js.objects.Record
import web.dom.Node
import web.worklets.Worklet

@JsPlainObject
sealed external interface AudioWorkletNodeOptions :
AudioNodeOptions {
var numberOfInputs: Int?
var numberOfOutputs: Int?
var outputChannelCount: ReadonlyArray<Number>?
var parameterData: ReadonlyRecord<String, Double>?
var processorOptions: Any?
}
