// Automatically generated - do not modify!

package web.performance

import js.core.HighResTimeStamp
import js.core.ReadonlyArray

sealed external class PerformanceResourceTiming :
    PerformanceEntry {
    val connectEnd: HighResTimeStamp
    val connectStart: HighResTimeStamp
    val decodedBodySize: Number
    val domainLookupEnd: HighResTimeStamp
    val domainLookupStart: HighResTimeStamp
    val encodedBodySize: Number
    val fetchStart: HighResTimeStamp
    val initiatorType: String
    val nextHopProtocol: String
    val redirectEnd: HighResTimeStamp
    val redirectStart: HighResTimeStamp
    val requestStart: HighResTimeStamp
    val responseEnd: HighResTimeStamp
    val responseStart: HighResTimeStamp
    val secureConnectionStart: HighResTimeStamp
    val serverTiming: ReadonlyArray<PerformanceServerTiming>
    val transferSize: Number
    val workerStart: HighResTimeStamp
    fun toJSON(): Any
}
