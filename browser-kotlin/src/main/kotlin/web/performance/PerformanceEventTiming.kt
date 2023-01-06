// Automatically generated - do not modify!

package web.performance

import js.core.HighResTimeStamp
import web.dom.Node

sealed external class PerformanceEventTiming :
    PerformanceEntry {
    val cancelable: Boolean
    val processingEnd: HighResTimeStamp
    val processingStart: HighResTimeStamp
    val target: Node?
    fun toJSON(): Any
}
