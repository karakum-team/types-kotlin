// Automatically generated - do not modify!

package web.audio

import js.array.ReadonlyArray
import js.objects.JsPlainObject

@JsPlainObject
sealed external interface WaveShaperOptions :
    AudioNodeOptions {
    var curve: ReadonlyArray<Double> /* | Float32Array */?
    var oversample: OverSampleType?
}
