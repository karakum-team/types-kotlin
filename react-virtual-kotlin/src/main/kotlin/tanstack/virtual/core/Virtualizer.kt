// Automatically generated - do not modify!

package tanstack.virtual.core

external interface Virtualizer<TScrollElement = unknown, TItemElement = unknown>{
    var private unsubs: private unsubs
    var options: Required<VirtualizerOptions<TScrollElement, TItemElement>>
    var scrollElement: TScrollElement | null
    var private measurementsCache: private measurementsCache
    var private itemMeasurementsCache: private itemMeasurementsCache
    var private pendingMeasuredCacheIndexes: private pendingMeasuredCacheIndexes
    var private scrollRect: private scrollRect
    var private scrollOffset: private scrollOffset
    var private destinationOffset: private destinationOffset
    var private scrollCheckFrame: private scrollCheckFrame
    var constructor(opts: VirtualizerOptions<TScrollElement, TItemElement>)
    var setOptions: (opts: VirtualizerOptions<TScrollElement, TItemElement>) -> Unit
    var private notify: private notify
    var private cleanup: private cleanup
    var private getSize: private getSize
    var private getMeasurements: private getMeasurements
    var private calculateRange: private calculateRange
    var private getIndexes: private getIndexes
    var getVirtualItems: () -> VirtualItem<TItemElement>[]
    var scrollToOffset: (toOffset: Int, { align } ?: ScrollToOffsetOptions) -> Unit
    var scrollToIndex: (index: Int, { align, ...rest } ?: ScrollToIndexOptions) -> Unit
    var getTotalSize: () -> Int
    var private _scrollToOffset: private _scrollToOffset
    var measure: () -> Unit
}
